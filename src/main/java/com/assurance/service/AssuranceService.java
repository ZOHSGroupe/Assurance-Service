package com.assurance.service;

import com.assurance.dto.AssuranceCreateDTO;
import com.assurance.dto.AssuranceDTO;
import com.assurance.dto.AssuranceUpdateDTO;
import com.assurance.entity.Assurance;
import com.assurance.exception.AssuranceAlreadyExistsException;
import com.assurance.exception.AssuranceNotFoundException;
import com.assurance.repository.AssuranceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AssuranceService {

    private final AssuranceRepository assuranceRepository;
    private final ModelMapper modelMapper;



    public Optional<AssuranceCreateDTO> saveAssurance(AssuranceCreateDTO assuranceCreateDTO) {
            String viheculeId = assuranceCreateDTO.getViheculeId();

            // Check if an assurance already exists for the specified viheculeId
            if (!assuranceRepository.findByViheculeId(viheculeId).isEmpty()) {
                throw new AssuranceAlreadyExistsException("Assurance already exists for viheculeId " + viheculeId);
            }

            Assurance assurance = modelMapper.map(assuranceCreateDTO, Assurance.class);
            // You can perform additional business logic here before saving
            assurance = assuranceRepository.save(assurance);

            AssuranceCreateDTO createdAssuranceDTO = modelMapper.map(assurance, AssuranceCreateDTO.class);
            return Optional.of(createdAssuranceDTO);
    }

    public List<AssuranceDTO> getAllAssurances() {
        List<Assurance> assurances = assuranceRepository.findAll();
        return assurances.stream()
                .map(assurance -> modelMapper.map(assurance, AssuranceDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<AssuranceDTO> getAssuranceById(String id) {
        return assuranceRepository.findById(id)
                .map(assurance -> modelMapper.map(assurance, AssuranceDTO.class));
    }

    public boolean deleteAssuranceById(String id) {
        Optional<Assurance> existingAssurance = assuranceRepository.findById(id);

        if (existingAssurance.isPresent()) {
            try {
                assuranceRepository.deleteById(id);
                return true; // Deletion successful
            } catch (Exception e) {
                e.printStackTrace(); // Log the exception
                return false; // Deletion failed
            }
        } else {
            return false; // Assurance not found
        }
    }


    public AssuranceDTO updateAssurance(String id, AssuranceUpdateDTO updateAssuranceDTO) {
        Optional<Assurance> existingAssuranceOptional = assuranceRepository.findById(id);

        if (existingAssuranceOptional.isPresent()) {
            Assurance existingAssurance = existingAssuranceOptional.get();

            // Update fields if provided in the UpdateAssuranceDTO
            if (updateAssuranceDTO.getType() != null) {
                existingAssurance.setType(updateAssuranceDTO.getType());
            }

            if (updateAssuranceDTO.getStatus() != null) {
                existingAssurance.setStatus(updateAssuranceDTO.getStatus());
            }
            if (updateAssuranceDTO.getPrice() != null) {
                existingAssurance.setPrice(updateAssuranceDTO.getPrice());
            }

            // You can perform additional business logic here before updating
            existingAssurance = assuranceRepository.save(existingAssurance);
            return modelMapper.map(existingAssurance, AssuranceDTO.class);
        } else {
            // Handle not found case
            throw new AssuranceNotFoundException("Assurance not found for id: " + id);
        }
    }


    public List<AssuranceDTO> getAssurancesByViheculeId(String viheculeId) {
        List<Assurance> assurances = assuranceRepository.findByViheculeId(viheculeId);
        return assurances.stream()
                .map(assurance -> modelMapper.map(assurance, AssuranceDTO.class))
                .collect(Collectors.toList());
    }

    public boolean deleteAssurancesByViheculeId(String viheculeId) {
        List<Assurance> assurancesToDelete = assuranceRepository.findByViheculeId(viheculeId);
        if(assurancesToDelete.isEmpty()) return false;
        assurancesToDelete.forEach(assurance -> assuranceRepository.deleteById(assurance.getId()));
        return true;
    }
    public boolean hasAssuranceForVihecule(String viheculeId) {
        List<Assurance> existingAssurance = assuranceRepository.findByViheculeId(viheculeId);
        return !existingAssurance.isEmpty();
    }

    // Add other methods as needed, e.g., findByType, findByStatus, etc.
}

package com.client.entity;

import com.client.entity.enumerate.FuelType;
import com.client.entity.enumerate.Status;
import com.client.entity.enumerate.TypeVihecule;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vihecule{
    @Id
    String id;
    String marque;
    String genre;
    //@Enumerated(value = EnumType.STRING)
    //TypeVihecule typeVihecule;
    String typeVihecule;
    //@Enumerated(value = EnumType.STRING)
    //FuelType fuelType;
    String fuelType;
    String vehicleIdentificationNumber;//numero_chassis
    Long cylinderCount;
    String taxIdentificationNumber;//fiscalNumber
    Long taxHorsepower;//fiscalPower
    String licensePlateNumber;//matricule
    Double emptyWeight;
    Double grossVehicleWeightRating;//Poids Total Autorisé en Charge
    Double currentCarValue;
    @Temporal(TemporalType.DATE)
    Date manufacturingDate;

    //@Enumerated(value = EnumType.STRING)
    //Status status;
    String status;
    @Temporal(TemporalType.DATE)
    Date dateCreation;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Client client;

    @OneToOne(mappedBy = "vihecule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Assurance assurance;


    @OneToOne(mappedBy = "vihecule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Contrat contrat;


    @PrePersist
    protected void onCreate() {
        // Set the default value for the 'date' field to the current date
        this.dateCreation = new Date();
    }
}
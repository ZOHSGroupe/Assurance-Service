package com.client.entity;

import com.client.entity.enumerate.Status;
import com.client.entity.enumerate.TypeAssurance;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Assurance {
    @Id
    String id;
    //@Enumerated(value = EnumType.STRING)
    //TypeAssurance type;
    String type;
    @Temporal(TemporalType.DATE)
    Date dateCraete;
    //@Enumerated(value = EnumType.STRING)
    //Status status;
    String status;
    @OneToOne
    Vihecule vihecule;
    String price;
    @PrePersist
    protected void onCreate() {
        // Set the default value for the 'date' field to the current date
        this.dateCraete = new Date();
    }
}

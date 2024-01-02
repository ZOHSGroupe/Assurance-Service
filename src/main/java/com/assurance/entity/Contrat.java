package com.client.entity;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;

import java.util.Date;
import java.util.List;

@Entity
public class Contrat {
    @Id
    String id;
    String price;
    @Temporal(TemporalType.DATE)
    Date dateStart;//date creation contrat == date creation this block
    @Temporal(TemporalType.DATE)
    Date dateFin;
    @OneToOne
    Vihecule vihecule;
}

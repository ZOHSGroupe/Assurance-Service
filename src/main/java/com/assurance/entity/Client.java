package com.client.entity;

import com.client.entity.enumerate.Gender;
import com.client.entity.enumerate.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Client{
    @Id
    String id;
    String firstName;
    String lastName;
    String nationalId;
    String email;
    String city;
    @Temporal(TemporalType.DATE)
    Date birthDate;
    String nationality;
    //@Enumerated(value = EnumType.STRING)
    //Gender gender;
    String gender;
    private Date createDate;
    String address;
    //@Enumerated(value = EnumType.STRING)
    //Status status;
    String status;

    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    List<Vihecule> viheculeList;
    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    List<Notification> notificationList;
    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    List<DriverLicense> driverLicenseList;



}

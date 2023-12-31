package com.client.entity;

import com.client.entity.enumerate.NotificationStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Notification {
    @Id
    String id;
    String message;
    @Temporal(TemporalType.DATE)
    private Date dateNotification;
    //@Enumerated(value = EnumType.STRING)
    //NotificationStatus status;
    String status;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Client client;
    @PrePersist
    protected void onCreate() {
        // Set the default value for the 'date' field to the current date
        this.dateNotification = new Date();
    }
}

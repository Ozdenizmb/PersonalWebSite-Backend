package com.baranozdeniz.personalwebsite.model;

import com.baranozdeniz.personalwebsite.model.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "user_data")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "biography")
    private String biography;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "profession")
    private String profession;
    @Column(name = "birthday")
    private LocalDate birthday;
    @Column(name = "role")
    private String role;

}

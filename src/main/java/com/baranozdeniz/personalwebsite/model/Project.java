package com.baranozdeniz.personalwebsite.model;

import com.baranozdeniz.personalwebsite.model.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "project_data")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Project extends BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "url")
    private String url;
    @Column(name = "technologies")
    private String technologies;
    @Column(name = "image_url")
    private String imageUrl;

}

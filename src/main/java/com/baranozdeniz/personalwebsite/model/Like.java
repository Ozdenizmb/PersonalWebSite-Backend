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
@Table(name = "like_data", schema = "util_sch")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Like extends BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "project_id")
    private UUID projectId;

}

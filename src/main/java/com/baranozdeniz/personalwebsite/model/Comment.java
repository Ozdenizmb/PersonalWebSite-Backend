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
@Table(name = "comment_data", schema = "util_sch")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "project_id")
    private UUID projectId;
    @Column(name = "text")
    private String text;

}

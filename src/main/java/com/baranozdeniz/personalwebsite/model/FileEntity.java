package com.baranozdeniz.personalwebsite.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "asset_data", schema = "util_sch")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "cdn_path")
    private String cdnPath;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

}

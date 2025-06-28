package com.project.Farmer.Support.System.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.project.Farmer.Support.System.Service.FarmerService;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Data
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class CropType {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String cropType;
    private String season;
    private String description;
    @OneToOne(mappedBy = "cropType",fetch=FetchType.EAGER)
    private Farmer farmer;
}

package com.project.Farmer.Support.System.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class Fertilizers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fertilizerID;
    private String season;
    private String fertilizerName;
    private Long costOfFertilizer;
    @ManyToOne
    @JoinColumn(name="FK_Farmer",referencedColumnName = "farmerId")
    @ToString.Exclude
    private Farmer farmer;
}

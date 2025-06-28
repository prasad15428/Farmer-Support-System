package com.project.Farmer.Support.System.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,property="farmerId")
public class Farmer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long farmerId;
    String name;
    String location;
    long contactNum;

    @OneToOne(cascade = CascadeType.ALL)
    private CropType cropType ;

    @OneToMany(mappedBy="farmer",cascade=CascadeType.ALL)
    @ToString.Exclude
    private List<Fertilizers> fertilizers=new ArrayList<>();

    public void setFertilizers(List<Fertilizers> fertilizers){
        this.fertilizers=fertilizers;
            if(fertilizers !=null){
                for(Fertilizers f: fertilizers){
                    f.setFarmer(this);
                }
            }
        }
    }

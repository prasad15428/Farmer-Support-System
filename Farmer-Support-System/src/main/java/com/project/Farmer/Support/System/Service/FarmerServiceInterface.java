package com.project.Farmer.Support.System.Service;

import com.project.Farmer.Support.System.Entity.CropType;
import com.project.Farmer.Support.System.Entity.Fertilizers;

import java.util.Optional;

public interface FarmerServiceInterface {
    public CropType saveCropDetails(CropType cropType);

    public Optional<CropType> getCropDetailByID(Long id);

    public Fertilizers saveFertilizersDetails(Fertilizers fertilizers);

    public Optional<Fertilizers> getFertilizerDetails(Long id);
}

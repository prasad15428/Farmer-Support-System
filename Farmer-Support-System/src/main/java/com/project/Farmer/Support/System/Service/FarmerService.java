package com.project.Farmer.Support.System.Service;

import com.project.Farmer.Support.System.DTO.FarmerDTO;
import com.project.Farmer.Support.System.Entity.CropType;
import com.project.Farmer.Support.System.Entity.Fertilizers;
import com.project.Farmer.Support.System.Repository.CropTypeRepository;
import com.project.Farmer.Support.System.Repository.FarmerRepository;
import com.project.Farmer.Support.System.Repository.FertilizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.Farmer.Support.System.Entity.Farmer;

import java.util.List;
import java.util.Optional;

@Service
public class FarmerService implements  FarmerServiceInterface{

    FarmerService(){}
    @Autowired
    private FarmerRepository farmerRepository;
    private FarmerDTO farmerDTO;
    @Autowired
    private CropTypeRepository cropTypeRepository;
    private FertilizerRepository fertilizerRepository;
    FarmerService(FertilizerRepository fertilizerRepository){
        this.fertilizerRepository=fertilizerRepository;
    }

    public Farmer saveFarmerDetails(Farmer farmer){
        return farmerRepository.save(farmer);
    }
    public List<Farmer> saveMultipleFarmerDetails(List<Farmer> farmer){
        return farmerRepository.saveAll(farmer);
    }
    public List<Farmer> getFarmers(){
        return farmerRepository.findAll();
    }
    public Optional<Farmer> getSingleFarmer(Long id){
                return farmerRepository.findById(id);
    }

    @Override
    public CropType saveCropDetails(CropType cropType) {
        return  cropTypeRepository.save(cropType);
    }

    @Override
    public Optional<CropType> getCropDetailByID(Long id) {
        return cropTypeRepository.findById(id);
    }

    @Override
    public Fertilizers saveFertilizersDetails(Fertilizers fertilizers) {
        return fertilizerRepository.save(fertilizers);
    }

    @Override
    public Optional<Fertilizers> getFertilizerDetails(Long  id) {
       Optional<Fertilizers> fertilizers= fertilizerRepository.findById(id);
       return fertilizers;
    }
}

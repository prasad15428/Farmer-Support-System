package com.project.Farmer.Support.System.Service;

import com.project.Farmer.Support.System.Controller.FarmerController;
import com.project.Farmer.Support.System.DTO.FarmerDTO;
import com.project.Farmer.Support.System.Entity.CropType;
import com.project.Farmer.Support.System.Entity.Fertilizers;
import com.project.Farmer.Support.System.Repository.CropTypeRepository;
import com.project.Farmer.Support.System.Repository.FarmerRepository;
import com.project.Farmer.Support.System.Repository.FertilizerRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.Farmer.Support.System.Entity.Farmer;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;

@Service
public class FarmerService implements  FarmerServiceInterface{
private static final Logger logger=LoggerFactory.getLogger(FarmerService.class);


    FarmerService(){}
    @Autowired
    private FarmerRepository farmerRepository;
    private FarmerDTO farmerDTO;
    @Autowired
    private CropTypeRepository cropTypeRepository;
    private FertilizerRepository fertilizerRepository;
    @Autowired
    FarmerService(FertilizerRepository fertilizerRepository){
        this.fertilizerRepository=fertilizerRepository;
    }

    public Farmer saveFarmerDetail(Farmer farmer){
        return farmerRepository.save(farmer);
    }
    public List<Farmer> saveMultipleFarmerDetails(List<Farmer> farmer){
        return farmerRepository.saveAll(farmer);
    }
    public List<Farmer> getFarmers(){
        return  farmerRepository.findAll().stream()
                .sorted(Comparator.comparing(Farmer::getName).reversed())
                .toList();
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

    @Override
    public List<String> getNamesOfFarmers(char ch) {
      List<String> farmerNames=farmerRepository.getFarmerNames();
       List<String> list=farmerNames.stream()
                .filter(name -> name.toLowerCase().startsWith(String.valueOf(ch).toLowerCase()))
                .toList();
        logger.info("Filtered farmer names starting with '{}': {}", ch, list);
        return list;
    }
    public Farmer findByName(String name){
        return farmerRepository.findByName(name);
    }
    public List<Fertilizers> CalculateTotalAmount(Long farmerId){
      List<Fertilizers> fertilizers=  fertilizerRepository.findByFarmer_FarmerId(farmerId);
     return null ;
    }
}

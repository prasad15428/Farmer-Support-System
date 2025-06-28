package com.project.Farmer.Support.System.Controller;

import com.project.Farmer.Support.System.DTO.FarmerDTO;
import com.project.Farmer.Support.System.Entity.CropType;
import com.project.Farmer.Support.System.Entity.Farmer;
import com.project.Farmer.Support.System.Entity.Fertilizers;
import com.project.Farmer.Support.System.Exception.*;
import com.project.Farmer.Support.System.Exception.AppError;
import com.project.Farmer.Support.System.Service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


@RestController
@RequestMapping("/farmerV1")
public class FarmerController {
    private  static final Logger logger= LoggerFactory.getLogger(FarmerController.class);
    @Autowired
    private FarmerService farmerService;

    @PostMapping("/sendDetailsV1")
    public ResponseEntity<Farmer> sendDetails(@RequestBody Farmer farmer){
        logger.info("Found Farmer details 888562{}",farmer.toString());
        Farmer farmer1=farmerService.saveFarmerDetails(farmer);
        logger.info("Found Farmer details{}",farmer1);
        return new  ResponseEntity<>(farmer1,HttpStatus.CREATED);
    }
    @PostMapping("/sendMultipleFarmers")
    public ResponseEntity<List<Farmer>> sendMultiFar(@RequestBody List<Farmer> farmerList){
        List<Farmer> farmer=farmerService.saveMultipleFarmerDetails(farmerList);
        return new  ResponseEntity<List<Farmer>>(farmer,HttpStatus.CREATED);
    }
    @GetMapping("/getFarmers")
    public List<Farmer> getFarmer(){
       return farmerService.getFarmers();
    }
    @GetMapping("/getCropDetailsByID/{id}")
    public ResponseEntity<CropType> getByID(@PathVariable Long id){
            CropType cropType= farmerService.getCropDetailByID(id)
        .orElseThrow(() -> new CropDetailsNotFoundInDataBase("Crop not found with id "+id));
        return ResponseEntity.ok(cropType);
    }
    @GetMapping("/getByID/{id}")
    public ResponseEntity<Farmer> getFarmerByID(@PathVariable Long id){
       Farmer farmer= farmerService.getSingleFarmer(id)
        .orElseThrow(()->new FarmerNotFoundException("Farmer ID DID not found  "+id ));
       return  ResponseEntity.ok(farmer);
    }
    @PostMapping("/sendCropType")
    public ResponseEntity<CropType> sendCropDetails(@RequestBody CropType cropType){
        CropType cropType1= farmerService.saveCropDetails(cropType) ;
        return new ResponseEntity<CropType>(cropType1,HttpStatus.CREATED);
    }
    @PostMapping("/sendFertilizerDetails")
    public ResponseEntity<Fertilizers> postFerDetails(@RequestBody Fertilizers fertilizers){
        Fertilizers fertilizers1=farmerService.saveFertilizersDetails(fertilizers);
        logger.info("Crop details received to Controller class{}",fertilizers);
        return new ResponseEntity<>(fertilizers1,HttpStatus.CREATED);
    }
    @GetMapping("/getFertilizersDetails/{id}")
    public ResponseEntity<Fertilizers> getDetails(@PathVariable Long id){
        Fertilizers fertilizers= farmerService.getFertilizerDetails(id)
                .orElseThrow(()->new FertilizersNotFound("By your id "+id+"Fertilizers not founded"));
        return ResponseEntity.ok(fertilizers);
    }
    @ExceptionHandler(FarmerNotFoundException.class)
    public ResponseEntity<AppError> handleNotFound(FarmerNotFoundException ex){
        AppError farmerAppError=new AppError(HttpStatus.CONFLICT,"Provided "+" "+"Not Found in Data Base", LocalDateTime.now());
        return  new ResponseEntity<>(farmerAppError, farmerAppError.getStatus());
    }
    @ExceptionHandler(CropDetailsNotFoundInDataBase.class)
    public ResponseEntity<AppError> handleCropNotFound(CropDetailsNotFoundInDataBase ex){
        AppError appError=new AppError(HttpStatus.CONFLICT,ex.getMessage(),LocalDateTime.now());
        return new ResponseEntity<>(appError,appError.getStatus());
    }
    @ExceptionHandler(FertilizersNotFound.class)
    public ResponseEntity<AppError> handleFertilizerException(FertilizersNotFound ex){
        AppError appError=new AppError(HttpStatus.CONFLICT,ex.getMessage(),LocalDateTime.now());
        return  new ResponseEntity<>(appError,appError.getStatus());
    }

}

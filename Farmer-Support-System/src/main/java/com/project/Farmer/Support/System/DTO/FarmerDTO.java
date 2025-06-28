package com.project.Farmer.Support.System.DTO;

import com.project.Farmer.Support.System.Entity.Farmer;
import lombok.Data;

import java.util.List;

@Data
public class FarmerDTO {
    Long id;
    String name;
    long contactNum;
  //  String location;
  //  String typeofPaddy;
    public  FarmerDTO todo(Farmer farmer){
        FarmerDTO farmerDTO=new FarmerDTO();
        farmerDTO.setId(farmer.getFarmerId());
        farmerDTO.setName(farmer.getName());
        farmerDTO.setContactNum(getContactNum());
        return  farmerDTO;
    }
    public static Farmer toEntity(Farmer farmer){
        Farmer farmer1=new Farmer();
      //  farmer1.setId(farmer.getId());
        farmer1.setName(farmer.getName());
        farmer1.setContactNum(farmer.getContactNum());
        return  farmer1;
    }
    public List<FarmerDTO> covertToDTOToList(List<Farmer> farmers){
       return farmers.stream()
                .map(this::todo)
                .toList();
    }
}

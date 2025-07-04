package com.project.Farmer.Support.System.Repository;

import com.project.Farmer.Support.System.DTO.FarmerDTO;
import com.project.Farmer.Support.System.Entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer,Long> {
}

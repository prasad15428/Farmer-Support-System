package com.project.Farmer.Support.System.Repository;

import com.project.Farmer.Support.System.Entity.CropType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CropTypeRepository extends JpaRepository<CropType,Long> {

//     List<CropType> findByFK_FARMER(Long id);
}

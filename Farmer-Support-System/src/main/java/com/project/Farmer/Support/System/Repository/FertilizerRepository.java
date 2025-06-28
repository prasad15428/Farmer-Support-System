package com.project.Farmer.Support.System.Repository;

import com.project.Farmer.Support.System.Entity.Fertilizers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FertilizerRepository extends JpaRepository<Fertilizers,Long> {
}

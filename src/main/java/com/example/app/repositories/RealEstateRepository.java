package com.example.app.repositories;

import com.example.app.entities.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealEstateRepository extends JpaRepository<RealEstate, Integer> {


    RealEstate save(RealEstate realEstate);

}

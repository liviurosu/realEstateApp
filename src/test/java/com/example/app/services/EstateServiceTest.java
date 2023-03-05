package com.example.app.services;

import com.example.app.entities.RealEstate;
import com.example.app.exceptions.PriceException;
import com.example.app.models.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

//@SpringBootTest
public class EstateServiceTest {


    RealEstateService realEstateService;

    @BeforeEach
    public void beforeEach(){
        realEstateService = new RealEstateService();

    }

    @Test
    public void shouldThrowAnExceptionPriceGT350Studio(){
        RealEstate realEstate = new RealEstate();
        realEstate.setRealEstateDate(LocalDate.now());
        realEstate.setParking(true);
        realEstate.setType(Type.STUDIO);
        realEstate.setOwner("Test");
        realEstate.setMonthlyRate(400.0);

        assertThrows(PriceException.class, () -> realEstateService.addRealEstate(realEstate));

    }

    @Test
    public void shouldThrowAnExceptionFieldsNull(){
        RealEstate realEstate = new RealEstate();
//        realEstate.setRealEstateDate(LocalDate.now());
//        realEstate.setParking(true);
//        realEstate.setType(Type.STUDIO);
//        realEstate.setOwner("Test");
//        realEstate.setMonthlyRate(400.0);

        assertThrows(PriceException.class, () -> realEstateService.addRealEstate(realEstate));

    }
}

package com.example.app;

import com.example.app.entities.RealEstate;
import com.example.app.exceptions.PriceException;
import com.example.app.models.Type;
import com.example.app.repositories.RealEstateRepository;
import com.example.app.services.RealEstateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class RealEstateAppTests {


    //RealEstate realEstate;

    @Mock
    private RealEstateRepository realEstateRepository;
    @InjectMocks
    private RealEstateService realEstateService;

    @BeforeEach
    public void beforeEach() {
        //realEstateService = new RealEstateService();

//        RealEstate realEstate = new RealEstate();
//        realEstate.setRealEstateDate(LocalDate.now());
//        realEstate.setParking(true);
//        realEstate.setType(Type.STUDIO);
//        realEstate.setOwner("Test");
//        realEstate.setMonthlyRate(400.0);
    }
//
//	@BeforeEach
//	public void setRealEstate(){
//
//
//
//		//return realEstate;
//	}


    @Test
    @DisplayName("Check what happens when you are trying to insert a studio with a price greater or equals with 350 and studio")
    void testGreaterThan350AndStudio() {

        RealEstate realEstate = new RealEstate();
        realEstate.setRealEstateDate(LocalDate.now());
        realEstate.setParking(true);
        realEstate.setType(Type.STUDIO);
        realEstate.setOwner("Test");
        realEstate.setMonthlyRate(400.0);

        assertThrows(PriceException.class, () -> realEstateService.addRealEstate(realEstate));
    }

    @Test
    @DisplayName("Check what happens when you are trying to insert a studio with a price greater or equals with 350 and studio")
    void testGreaterThan450AndTwoRooms() {

        RealEstate realEstate = new RealEstate();
        realEstate.setRealEstateDate(LocalDate.now());
        realEstate.setParking(true);
        realEstate.setOwner("Test");
        realEstate.setMonthlyRate(460.0);
        realEstate.setType(Type.TWO_ROOMS);

        assertThrows(PriceException.class, () -> realEstateService.addRealEstate(realEstate));
    }

    @Test
    void test1() {

        RealEstate realEstate = new RealEstate();
        realEstate.setRealEstateDate(LocalDate.now());
        realEstate.setParking(true);
        realEstate.setOwner("Test");
        realEstate.setMonthlyRate(450.0);
        realEstate.setType(Type.TWO_ROOMS);

        when(realEstateRepository.save(realEstate)).thenReturn(realEstate);
        var result = realEstateService.addRealEstate(realEstate);
        assertEquals(realEstate, result);
        //realEstateService.addRealEstate(realEstate);

    }


}

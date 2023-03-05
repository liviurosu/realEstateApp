package com.example.app.services;

import com.example.app.entities.RealEstate;
import com.example.app.exceptions.PriceException;
import com.example.app.models.Type;
import com.example.app.repositories.RealEstateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EstateServiceItegrationTest {

    @Mock
    private RealEstateRepository realEstateRepository;
    //@InjectMocks
    @InjectMocks
    private RealEstateService realEstateService;

    @Test
    public void test1(){
        // RealEstateRepository realEstateRepository1 = mock(RealEstateRepository.class);
        // RealEstateService realEstateService1 = new RealEstateService(realEstateRepository1);

        RealEstate realEstate = new RealEstate();
        realEstate.setRealEstateDate(LocalDate.now());
        realEstate.setParking(true);
        realEstate.setType(Type.STUDIO);
        realEstate.setOwner("Test");
        realEstate.setMonthlyRate(400.0);


        assertThrows(PriceException.class, () -> when(realEstateService.addRealEstate(realEstate))
                .thenThrow(PriceException.class));
    }
    @Test
    public void test2(){
        // RealEstateRepository realEstateRepository1 = mock(RealEstateRepository.class);
        // RealEstateService realEstateService1 = new RealEstateService(realEstateRepository1);

        RealEstate realEstate = new RealEstate();
        realEstate.setRealEstateDate(LocalDate.now());
        realEstate.setParking(true);
        realEstate.setType(Type.STUDIO);
        realEstate.setOwner("Test");
        realEstate.setMonthlyRate(200.0);

        when(realEstateRepository.save(realEstate)).thenReturn(realEstate);
        assertEquals(realEstateService.addRealEstate(realEstate), realEstate);

    }

    @Test
    public void test3(){

        RealEstate realEstate1 = new RealEstate();
        realEstate1.setRealEstateDate(LocalDate.now());
        realEstate1.setParking(true);
        realEstate1.setType(Type.STUDIO);
        realEstate1.setOwner("Test");
        realEstate1.setMonthlyRate(200.0);

        RealEstate realEstate2 = new RealEstate();
        realEstate2.setRealEstateDate(LocalDate.now());
        realEstate2.setParking(true);
        realEstate2.setType(Type.STUDIO);
        realEstate2.setOwner("Test");
        realEstate2.setMonthlyRate(200.0);

        RealEstate realEstate3 = new RealEstate();
        realEstate3.setRealEstateDate(LocalDate.now());
        realEstate3.setParking(true);
        realEstate3.setType(Type.STUDIO);
        realEstate3.setOwner("Test");
        realEstate3.setMonthlyRate(200.0);

        RealEstate realEstate4 = new RealEstate();
        realEstate4.setRealEstateDate(LocalDate.now());
        realEstate4.setParking(true);
        realEstate4.setType(Type.STUDIO);
        realEstate4.setOwner("Test");
        realEstate4.setMonthlyRate(200.0);
        List<RealEstate> list = new ArrayList<>();
        list.add(realEstate1);
        list.add(realEstate2);
        list.add(realEstate3);
        list.add(realEstate4);

        when(realEstateRepository.saveAll(list)).thenReturn(list);
        var r = realEstateService.addRealEstates(list);

        assertEquals( r.size(), list.size());
    }


}

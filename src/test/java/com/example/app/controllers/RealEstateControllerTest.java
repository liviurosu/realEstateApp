package com.example.app.controllers;

import com.example.app.entities.RealEstate;
import com.example.app.models.Type;
import com.example.app.repositories.RealEstateRepository;
import com.example.app.services.RealEstateService;
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
class RealEstateControllerTest {
//    @Autowired
    @InjectMocks
    private RealEstateService realEstateService;
//
//   @MockBean
//    private RealEstateRepository realEstateRepository;
    @Mock
    private RealEstateRepository realEstateRepository;

//    @BeforeAll
//    public void beforeAll(){
//        realEstateService = new RealEstateService();
//    }


//    @Test
//    void getSorted() {
//        //List<RealEstate> realEstateList = realEstateRepository.findAll();
//        when(realEstateRepository.findAll()).equals(realEstateService.bla());
//        var r = realEstateService.bla();
//        //assertTrue(r.size() == realEstateList.size());
//    }

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
        //var r = realEstateService.findAll();
        // var r = realEstateRepository.saveAll(list);
        //when(realEstateService.getAll()).thenReturn(list);

        var r = realEstateService.addRealEstates(list);

       assertEquals( r.size(), list.size());
    }
}
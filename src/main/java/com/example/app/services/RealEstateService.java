package com.example.app.services;

import com.example.app.repositories.RealEstateRepository;
import com.example.app.entities.RealEstate;
import com.example.app.exceptions.PriceException;
import com.example.app.models.Type;
import com.example.app.utils.CustomComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RealEstateService {

    @Autowired
    private RealEstateRepository realEstateRepository;

    public RealEstate addRealEstate(RealEstate realEstate) throws PriceException {
        //validari pret
        //validari type
        //daca parking nu e specificat --->> pune false

        if (realEstate == null || realEstate.getMonthlyRate() == null || realEstate.getOwner() == null ||
                realEstate.getOwner() == null) {
            throw new PriceException(HttpStatus.BAD_REQUEST, "invalid fields", " ", " ");
        }

        if (realEstate.getMonthlyRate() > 350 && realEstate.getType().equals(Type.STUDIO)) {
            throw new PriceException(HttpStatus.BAD_REQUEST, "invalid price", " ", " ");
        }
        if (realEstate.getMonthlyRate() > 450 && realEstate.getType().equals(Type.TWO_ROOMS)) {
            throw new PriceException(HttpStatus.BAD_REQUEST, "invalid price", " ", " ");
        }
        if (realEstate.getMonthlyRate() > 455 && realEstate.getType().equals(Type.THREE_ROOMS)) {
            throw new PriceException(HttpStatus.BAD_REQUEST, "invalid price", " ", " ");
        }
        if (realEstate.getParking() == null) {
            realEstate.setParking(true);
        }
        realEstate.setRealEstateDate(LocalDate.now());

        return realEstateRepository.save(realEstate);
    }

    public List<RealEstate> getAll() {
        List<RealEstate> list = realEstateRepository.findAll();
        Collections.sort(list, new CustomComparator().reversed());
        return list;
    }

    public List<RealEstate> findAll() {
        List<RealEstate> list = realEstateRepository.findAll();
        return list;
    }


    public Map<Object, Long> getSorted() {
        List<RealEstate> list = realEstateRepository.findAll();
        list.stream().forEach(e -> System.out.println(e));
        Map<Object, Long> counterMap =
                list.stream()
                        .collect(Collectors.groupingBy(RealEstate::getType, Collectors.counting()));

        return counterMap;
    }

    @CacheEvict(cacheNames = "real_estate", key = "#id")
    public RealEstate delete(Integer id) {
        Optional<RealEstate> realEstate = realEstateRepository.findById(id);
        if (realEstate.get() != null) {
            realEstateRepository.delete(realEstate.get());
        }

        return realEstate.get();
    }

    public RealEstate findRealEstate(Integer id) {
        RealEstate realEstate = realEstateRepository.findById(id).get();
        if (realEstate == null) {
            throw new PriceException(HttpStatus.BAD_REQUEST, " not found the resource in the db", " ", "");
        }
        return realEstate;
    }

    @Cacheable(cacheNames = "real_estate")
    public List<RealEstate> bla() {
        List<RealEstate> realEstates = new ArrayList<>();
        realEstates = realEstateRepository.findAll();
        ArrayList<RealEstate> collect = realEstates.stream().
                filter(e -> e.getRealEstateDate().equals(LocalDate.of(2022, 12, 21)))
                .sorted(Comparator.comparing(RealEstate::getMonthlyRate).reversed())
                //.sorted((o1, o2) -> o1.getMonthlyRate().compareTo(o2.getMonthlyRate()))
                .limit(3)
                .collect(Collectors.toCollection(() -> new ArrayList<>()));

        collect.stream().forEach(System.out::println);
        return collect;
    }

    @CachePut(cacheNames = "real_estate", key = "#realestate.id")
    public RealEstate updateEntity(Integer id, RealEstate realEstate) {
        RealEstate realEstate1 = findRealEstate(id);
        if (realEstate.getOwner() != null && (!realEstate.getOwner().equals(realEstate1.getOwner()))) {
            realEstate1.setOwner(realEstate.getOwner());
        }
        if (realEstate.getRealEstateDate() != null && (!realEstate.getRealEstateDate().equals(realEstate1.getRealEstateDate()))) {
            realEstate1.setRealEstateDate(realEstate.getRealEstateDate());
        }
        if (realEstate.getType() != null && (!realEstate.getType().equals(realEstate1.getType()))) {
            realEstate1.setType(realEstate.getType());
        }

        realEstateRepository.save(realEstate1);
        return realEstate;
    }

    public List<RealEstate> addRealEstates(List<RealEstate> realEstateList) {
        return realEstateRepository.saveAll(realEstateList);
    }

}

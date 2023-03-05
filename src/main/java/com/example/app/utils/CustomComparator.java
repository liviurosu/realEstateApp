package com.example.app.utils;

import com.example.app.entities.RealEstate;

import java.util.Comparator;

public class CustomComparator implements Comparator<RealEstate> {
    @Override
    public int compare(RealEstate o1, RealEstate o2) {
        return o1.getMonthlyRate().compareTo(o2.getMonthlyRate());
    }
}

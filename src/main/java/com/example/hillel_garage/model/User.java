package com.example.hillel_garage.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class User {
    private String name;
    private int id;
    private List<Car> cars;
}

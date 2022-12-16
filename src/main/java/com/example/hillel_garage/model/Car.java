package com.example.hillel_garage.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Car {
    private int id;
    private String name;
}

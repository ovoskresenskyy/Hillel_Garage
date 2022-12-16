package com.example.hillel_garage.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private final String name;
    private int age;
    private int id;
}

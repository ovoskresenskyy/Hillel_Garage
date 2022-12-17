package com.example.hillel_garage.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private int id;
    private final String name;
    private int age;
}

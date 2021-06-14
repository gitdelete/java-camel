package com.aman.javacamel.domain;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class Employee implements Serializable {

    private String empId;

    private String empName;

    private String empCity;


}

package com.romansholokh.openstreetmap.springbootclient.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Address {
    private String city;
    private String street;
    private String building;
}

package com.romansholokh.openstreetmap.springbootclient.entity;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@ToString
@Table(name = "COORDINATES")
public class Coordinates {
    private int id;
    private String lat;
    private String lon;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId()
    {
        return id;
    }

    @Basic
    @Column(name = "latitude", nullable = false)
    public String getLat()
    {
        return lat;
    }

    @Basic
    @Column(name = "longitude", nullable = false)
    public String getLon()
    {
        return lon;
    }
}

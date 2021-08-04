package com.romansholokh.openstreetmap.springbootclient.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Address {
    @Expose(deserialize = false)
    private String city;
    @Expose(deserialize = false)
    private String street;
    @Expose(deserialize = false)
    private String building;
    @Expose(serialize = false)
    @SerializedName("display_name")
    private String fullAddress;
}

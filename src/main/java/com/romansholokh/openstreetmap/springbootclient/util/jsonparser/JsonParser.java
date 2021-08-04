package com.romansholokh.openstreetmap.springbootclient.util.jsonparser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.romansholokh.openstreetmap.springbootclient.entity.Coordinates;
import com.romansholokh.openstreetmap.springbootclient.pojo.Address;

import java.lang.reflect.Type;
import java.util.List;

public class JsonParser {

    public static Coordinates parseJsonToCoordinatesObject(String json) throws Exception {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Coordinates>>(){}.getType();
        List<Coordinates> list = gson.fromJson(json, type);

        return list.get(0);
    }

    public static Address parseJsonToAddressObject(String json) throws Exception {
        Gson gson = new Gson();

        return gson.fromJson(json, Address.class);
    }
}

package com.romansholokh.openstreetmap.springbootclient.util.jsonparser;

import com.romansholokh.openstreetmap.springbootclient.entity.Coordinates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserTest {

    @Test
    void testParseJsonToCoordinatesObject() throws Exception {
        String json = "[{\"place_id\":98690364,\"licence\":\"Data © OpenStreetMap contributors, ODbL 1.0. https://osm.org/copyright\",\"osm_type\":\"way\",\"osm_id\":31510288,\"boundingbox\":[\"51.508883\",\"51.5096454\",\"-0.1403344\",\"-0.1390605\"],\"lat\":\"51.5092768\",\"lon\":\"-0.13973813543557267\",\"display_name\":\"Royal Academy of Arts, Piccadilly, St. James's, City of Westminster, London, Greater London, England, W1J 0BD, United Kingdom\",\"class\":\"tourism\",\"type\":\"museum\",\"importance\":0.7644724074563636,\"icon\":\"https://nominatim.openstreetmap.org/ui/mapicons//tourist_museum.p.20.png\"}]";
        String wrongJson = "[{\"place_i8\",\"lon\":\"-0.13973813543557267\",\"display_name\":\"Royal Academy of Arts, Piccadilly, St. James's, City of Westminster, London, Greater Lo4724074563636,\"icon\":\"https://nominatim.openstreetmap.org/ui/mapicons//tourist_museum.p.20.png\"}]";
        String expectedLat = "51.5092768";
        String expectedLon = "-0.13973813543557267";

        Coordinates coordinates = JsonParser.parseJsonToCoordinatesObject(json);

        assertEquals(expectedLat, coordinates.getLat(), "Latitude does not match!");
        assertEquals(expectedLon, coordinates.getLon(), "Longitude does not match!");
    }
}
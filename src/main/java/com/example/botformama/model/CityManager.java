package com.example.botformama.model;

import com.example.botformama.entity.City;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Component
public class CityManager {
    private final ArrayList<City> cities = getCitiesFromJsonFile();

    private static ArrayList<City> getCitiesFromJsonFile() {
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new File("src/main/resources/cities.json");
        List<City> cityList;
        ArrayList<City> cityArrayList;
        try {
            //cities = mapper.readValue(jsonFile, new Typerefer);
            cityList = Arrays.asList(mapper.readValue(jsonFile,City[].class));
            cityArrayList = new ArrayList<>(cityList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (cityList.isEmpty()){
            System.out.println("City list is empty!");
        }
        return cityArrayList;
    }
}

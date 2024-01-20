package com.onlinebookstore.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;

public class ReadDataFromJson {
    private String json_file_path = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\JsonDataFiles\\RegisterPage.json";

    public HashMap<String, String> readDataFromJson() {
        HashMap<String, String> map = new HashMap<>();
        try {
            File file = new File(json_file_path);
            ObjectMapper mapper = new ObjectMapper();
            map = mapper.readValue(file, HashMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}

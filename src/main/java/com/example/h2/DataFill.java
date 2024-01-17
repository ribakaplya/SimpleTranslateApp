package com.example.h2;

import com.example.h2.entity.Data;
import com.example.h2.service.DataService;

import java.sql.SQLException;
import java.sql.Timestamp;

public class DataFill {
    public void createTable() {
        //TODO
    }

    public void addTestData() {
        DataService dataService = new DataService();

        Data data = new Data();
        data.setId(4L);
        data.setDateTime(new Timestamp(System.currentTimeMillis()));
        data.setRequestId("asd");
        data.setRequest("Тест3");
        data.setResponse("Test3");
        data.setRequestLang("ru");
        data.setResponseLang("en");
        data.setResponseIp("1.2.3.4");

        try {
            dataService.add(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

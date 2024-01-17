package com.example.h2.dao;

import com.example.h2.entity.Data;

import java.sql.SQLException;
import java.util.List;

public interface DataDAO {
    //create
    void add(Data data) throws SQLException;

    //read
    List<Data> getAll() throws SQLException;
    Data getByRequestId(String requestId) throws SQLException;

    //update
    void update(Data data) throws SQLException;

    //delete
    void remove(Data data) throws SQLException;
}

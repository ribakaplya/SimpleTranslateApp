package com.example.h2.service;

import com.example.h2.bl.Util;
import com.example.h2.dao.DataDAO;
import com.example.h2.entity.Data;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataService extends Util implements DataDAO {
    Connection connection = getConnection();

    @Override
    public void add(Data data) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO DATA (ID, DATETIME, REQUEST_ID, REQUEST, RESPONSE, " +
                "REQUEST_LANG, RESPONSE_LANG, RESPONSE_IP) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, data.getId());
            preparedStatement.setTimestamp(2, data.getDateTime());
            preparedStatement.setString(3, data.getRequestId());
            preparedStatement.setString(4, data.getRequest());
            preparedStatement.setString(5, data.getResponse());
            preparedStatement.setString(6, data.getRequestLang());
            preparedStatement.setString(7, data.getResponseLang());
            preparedStatement.setString(8, data.getResponseIp());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<Data> getAll() throws SQLException {
        List<Data> dataList = new ArrayList<>();

        String sql = "SELECT * FROM DATA ORDER BY DATETIME DESC";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Data data = new Data();
                data.setId(resultSet.getLong("ID"));
                data.setDateTime(resultSet.getTimestamp("DATETIME"));
                data.setRequestId(resultSet.getString("REQUEST_ID"));
                data.setRequest(resultSet.getString("REQUEST"));
                data.setResponse(resultSet.getString("RESPONSE"));
                data.setRequestLang(resultSet.getString("REQUEST_LANG"));
                data.setResponseLang(resultSet.getString("RESPONSE_LANG"));
                data.setResponseIp(resultSet.getString("RESPONSE_IP"));

                dataList.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return dataList;
    }

    @Override
    public Data getByRequestId(String requestId) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "SELECT * FROM DATA WHERE REQUESTID=?";

        Data data = new Data();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, requestId);

            ResultSet resultSet = preparedStatement.executeQuery();
            data.setId(resultSet.getLong("ID"));
            data.setDateTime(resultSet.getTimestamp("DATETIME"));
            data.setRequestId(resultSet.getString("REQUEST_ID"));
            data.setRequest(resultSet.getString("REQUEST"));
            data.setResponse(resultSet.getString("RESPONSE"));
            data.setRequestLang(resultSet.getString("REQUEST_LANG"));
            data.setResponseLang(resultSet.getString("RESPONSE_LANG"));
            data.setResponseIp(resultSet.getString("RESPONSE_IP"));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return data;
    }

    @Override
    public void update(Data data) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE DATA SET DATETIME=?, REQUESTID=?, REQUEST=?, RESPONSE=?, " +
                "REQUEST_LANG=?, RESPONSE_LANG=?, RESPONSE_IP=? WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setTimestamp(1, data.getDateTime());
            preparedStatement.setString(2, data.getRequestId());
            preparedStatement.setString(3, data.getRequest());
            preparedStatement.setString(4, data.getResponse());
            preparedStatement.setString(5, data.getRequestLang());
            preparedStatement.setString(6, data.getResponseLang());
            preparedStatement.setString(7, data.getResponseIp());
            preparedStatement.setLong(8, data.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void remove(Data data) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM DATA WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, data.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}

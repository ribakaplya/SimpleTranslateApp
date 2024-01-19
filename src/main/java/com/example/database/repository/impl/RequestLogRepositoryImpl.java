package com.example.database.repository.impl;

import com.example.database.connection.ConnectionUtil;
import com.example.database.repository.RequestLogRepository;
import com.example.database.model.RequestLogDto;
import com.example.database.query.RequestLogQueries;
import com.example.exceptions.RepositoryException;
import com.example.exceptions.TranslatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RequestLogRepositoryImpl implements RequestLogRepository {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private final ConnectionUtil connectionUtil;

	public RequestLogRepositoryImpl(ConnectionUtil connectionUtil) {
		this.connectionUtil = connectionUtil;
	}

	@Override
	public void add(RequestLogDto requestLogDto) {
		try (Connection connection = connectionUtil.getConnection()) {
			PreparedStatement addQuery = connection.prepareStatement(RequestLogQueries.ADD.getSql());

			addQuery.setLong(1, requestLogDto.getId());
			addQuery.setTimestamp(2, requestLogDto.getDateTime());
			addQuery.setString(3, requestLogDto.getRequestId());
			addQuery.setString(4, requestLogDto.getRequest());
			addQuery.setString(5, requestLogDto.getResponse());
			addQuery.setString(6, requestLogDto.getRequestLang());
			addQuery.setString(7, requestLogDto.getResponseLang());
			addQuery.setString(8, requestLogDto.getResponseIp());

			addQuery.executeUpdate();
		} catch (SQLException e) {
			log.error("Database error", e);
			throw new RepositoryException(e);
		}
	}

	@Override
	public List<RequestLogDto> getAll() throws SQLException {
		List<RequestLogDto> requestLogDtoList = new ArrayList<>();

		String sql = "SELECT * FROM DATA ORDER BY DATETIME DESC";

		Statement statement = null;
		try (Connection connection = connectionUtil.getConnection()) {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				RequestLogDto requestLogDto = new RequestLogDto();
				requestLogDto.setId(resultSet.getLong("ID"));
				requestLogDto.setDateTime(resultSet.getTimestamp("DATETIME"));
				requestLogDto.setRequestId(resultSet.getString("REQUEST_ID"));
				requestLogDto.setRequest(resultSet.getString("REQUEST"));
				requestLogDto.setResponse(resultSet.getString("RESPONSE"));
				requestLogDto.setRequestLang(resultSet.getString("REQUEST_LANG"));
				requestLogDto.setResponseLang(resultSet.getString("RESPONSE_LANG"));
				requestLogDto.setResponseIp(resultSet.getString("RESPONSE_IP"));

				requestLogDtoList.add(requestLogDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
		return requestLogDtoList;
	}

	@Override
	public RequestLogDto getByRequestId(String requestId) throws SQLException {
		PreparedStatement preparedStatement = null;

		String sql = "SELECT * FROM DATA WHERE REQUESTID=?";

		RequestLogDto requestLogDto = new RequestLogDto();
		try (Connection connection = connectionUtil.getConnection()) {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, requestId);

			ResultSet resultSet = preparedStatement.executeQuery();
			requestLogDto.setId(resultSet.getLong("ID"));
			requestLogDto.setDateTime(resultSet.getTimestamp("DATETIME"));
			requestLogDto.setRequestId(resultSet.getString("REQUEST_ID"));
			requestLogDto.setRequest(resultSet.getString("REQUEST"));
			requestLogDto.setResponse(resultSet.getString("RESPONSE"));
			requestLogDto.setRequestLang(resultSet.getString("REQUEST_LANG"));
			requestLogDto.setResponseLang(resultSet.getString("RESPONSE_LANG"));
			requestLogDto.setResponseIp(resultSet.getString("RESPONSE_IP"));

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}
		return requestLogDto;
	}

	@Override
	public void update(RequestLogDto requestLogDto) throws SQLException {
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE DATA SET DATETIME=?, REQUESTID=?, REQUEST=?, RESPONSE=?, " +
		             "REQUEST_LANG=?, RESPONSE_LANG=?, RESPONSE_IP=? WHERE ID=?";

		try (Connection connection = connectionUtil.getConnection()) {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setTimestamp(1, requestLogDto.getDateTime());
			preparedStatement.setString(2, requestLogDto.getRequestId());
			preparedStatement.setString(3, requestLogDto.getRequest());
			preparedStatement.setString(4, requestLogDto.getResponse());
			preparedStatement.setString(5, requestLogDto.getRequestLang());
			preparedStatement.setString(6, requestLogDto.getResponseLang());
			preparedStatement.setString(7, requestLogDto.getResponseIp());
			preparedStatement.setLong(8, requestLogDto.getId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}
	}

	@Override
	public void remove(RequestLogDto requestLogDto) throws SQLException {
		PreparedStatement preparedStatement = null;

		String sql = "DELETE FROM DATA WHERE ID=?";

		try (Connection connection = connectionUtil.getConnection()) {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setLong(1, requestLogDto.getId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}
	}
}

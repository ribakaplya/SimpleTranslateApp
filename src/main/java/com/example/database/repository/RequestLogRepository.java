package com.example.database.repository;

import com.example.database.model.RequestLogDto;

import java.sql.SQLException;
import java.util.List;

public interface RequestLogRepository {
	//create
	void add(RequestLogDto requestLogDto);

	//read
	List<RequestLogDto> getAll() throws SQLException;

	RequestLogDto getByRequestId(String requestId) throws SQLException;

	//update
	void update(RequestLogDto requestLogDto) throws SQLException;

	//delete
	void remove(RequestLogDto requestLogDto) throws SQLException;
}

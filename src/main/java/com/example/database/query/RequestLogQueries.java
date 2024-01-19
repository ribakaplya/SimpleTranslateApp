package com.example.database.query;

public enum RequestLogQueries {
	ADD("""
			INSERT INTO DATA (ID, DATETIME, REQUEST_ID, REQUEST, RESPONSE, REQUEST_LANG, RESPONSE_LANG, RESPONSE_IP)
			VALUES (?, ?, ?, ?, ?, ?, ?, ?)
			""");

	RequestLogQueries(String sql) {
		this.sql = sql;
	}

	private final String sql;

	public String getSql() {
		return sql;
	}
}

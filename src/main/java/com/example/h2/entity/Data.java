package com.example.h2.entity;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;

@Component
public class Data {

    private Long id;
    private Timestamp dateTime;
    private String requestId;
    private String request;
    private String response;
    private String requestLang;
    private String responseLang;
    private String responseIp;

    public Data() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getRequestLang() {
        return requestLang;
    }

    public void setRequestLang(String requestLang) {
        this.requestLang = requestLang;
    }

    public String getResponseLang() {
        return responseLang;
    }

    public void setResponseLang(String responseLang) {
        this.responseLang = responseLang;
    }

    public String getResponseIp() {
        return responseIp;
    }

    public void setResponseIp(String responseIp) {
        this.responseIp = responseIp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return Objects.equals(id, data.id) && Objects.equals(dateTime, data.dateTime) && Objects.equals(requestId, data.requestId) && Objects.equals(request, data.request) && Objects.equals(response, data.response) && Objects.equals(requestLang, data.requestLang) && Objects.equals(responseLang, data.responseLang) && Objects.equals(responseIp, data.responseIp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, requestId, request, response, requestLang, responseLang, responseIp);
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", requestId='" + requestId + '\'' +
                ", request='" + request + '\'' +
                ", response='" + response + '\'' +
                ", requestLang='" + requestLang + '\'' +
                ", responseLang='" + responseLang + '\'' +
                ", responseIp='" + responseIp + '\'' +
                '}';
    }
}

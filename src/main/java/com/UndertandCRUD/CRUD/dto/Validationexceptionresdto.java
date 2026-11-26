package com.UndertandCRUD.CRUD.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class Validationexceptionresdto {
    private LocalDateTime timestamp;
    private int statuscode;
    private String error;
    private String message;
    private String path;
    private Map<String, String> errormap;

    public Validationexceptionresdto(LocalDateTime timestamp, int statuscode, String error, String message, String path, Map<String, String> errormap) {
        this.timestamp = timestamp;
        this.statuscode = statuscode;
        this.error = error;
        this.message = message;
        this.path = path;
        this.errormap = errormap;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getErrormap() {
        return errormap;
    }

    public void setErrormap(Map<String, String> errormap) {
        this.errormap = errormap;
    }
}

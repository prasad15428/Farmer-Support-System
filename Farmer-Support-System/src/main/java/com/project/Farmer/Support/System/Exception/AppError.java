package com.project.Farmer.Support.System.Exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class AppError {
    private HttpStatus status;
    private String messages;
    private LocalDateTime localDateTime;

    public AppError(HttpStatus status, String messages, LocalDateTime localDateTime) {
        this.status = status;
        this.messages = messages;
        this.localDateTime = localDateTime;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}

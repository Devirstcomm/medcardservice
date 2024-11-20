package com.example.medcardservice.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class ErrorDetails {

    @Schema(description = "Статус HTTP-кода ошибки", example = "404")
    private int status;

    @Schema(
            description = "Сообщение об ошибке. Может возвращать различные сообщения в зависимости от типа ошибки.",
            example = "Запрашиваемый ресурс не найден"
    )
    private String message;

    @Schema(description = "Время возникновения ошибки в миллисекундах с начала эпохи",
            example = "1630542600000")
    private long timestamp;

    public ErrorDetails(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

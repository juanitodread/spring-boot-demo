package org.juanitodread.demo.dtos;

public class Health {
    private final int code;
    private final String status;
    private final String message;

    public Health(int code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

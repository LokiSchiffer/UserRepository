package com.example.UserRepository.logic.exceptions;

public class ErrorMessage {

    private int status;
    private String message;
    private String developerMessage;

    public ErrorMessage(final int status, final String message, final String developerMessage) {
        this.status = status;
        this.message = message;
        this.developerMessage = developerMessage;
    }

    //

    public int getStatus() {
        return status;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(final String developerMessage) {
        this.developerMessage = developerMessage;
    }

    //

    @Override
    public final String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ApiError [status=")
                .append(status)
                .append(", message=")
                .append(message)
                .append(", developerMessage=")
                .append(developerMessage)
                .append("]");
        return builder.toString();
    }
}

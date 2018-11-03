package com.solar.calm.service.dto;

import java.io.Serializable;

public class ErrorDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 5435812946670992015L;
    private String error;
    private String error_description;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    public ErrorDTO(String error, String error_description) {
        super();
        this.error = error;
        this.error_description = error_description;
    }

    public ErrorDTO() {
        super();
    }


}

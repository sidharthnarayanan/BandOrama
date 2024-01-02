package com.sidsolutions.bandorama;

public class ResponseStatus {
    boolean status = false;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ResponseStatus(boolean status) {
        this.status = status;
    }
}

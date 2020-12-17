package hr.fer.tel.SmartAgriculture.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PycomMetadataModel {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date time;

    public PycomMetadataModel() {
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time =     time;
    }
}

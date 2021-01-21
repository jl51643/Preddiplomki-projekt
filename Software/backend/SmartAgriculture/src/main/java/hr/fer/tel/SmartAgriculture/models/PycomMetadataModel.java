package hr.fer.tel.SmartAgriculture.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PycomMetadataModel {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
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

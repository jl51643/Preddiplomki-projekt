package hr.fer.tel.SmartAgriculture.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PycomPayloadFieldsModel {

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date time;

    private float airHumidity;

    private float soilHumidity;

    private float airTemperature;

    private float soilTemperature;

    public PycomPayloadFieldsModel() {
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public float getAirHumidity() {
        return airHumidity;
    }

    public void setAirHumidity(float airHumidity) {
        this.airHumidity = airHumidity;
    }

    public float getSoilHumidity() {
        return soilHumidity;
    }

    public void setSoilHumidity(float soilHumidity) {
        this.soilHumidity = soilHumidity;
    }

    public float getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(float airTemperature) {
        this.airTemperature = airTemperature;
    }

    public float getSoilTemperature() {
        return soilTemperature;
    }

    public void setSoilTemperature(float soilTemperature) {
        this.soilTemperature = soilTemperature;
    }
}

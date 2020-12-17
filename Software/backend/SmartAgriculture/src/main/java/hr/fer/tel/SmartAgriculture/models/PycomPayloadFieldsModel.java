package hr.fer.tel.SmartAgriculture.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class PycomPayloadFieldsModel {

    private float airHumidity;

    private float soilHumidity;

    @JsonProperty("airTemp")
    private float airTemperature;

    @JsonProperty("soilTemp")
    private float soilTemperature;

    public PycomPayloadFieldsModel() {
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

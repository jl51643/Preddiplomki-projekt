package hr.fer.tel.SmartAgriculture.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PycomPayloadFieldsModel {

    private Double airHumidity;

    private Double soilHumidity;

    @JsonProperty("airTemp")
    private Double airTemperature;

    @JsonProperty("soilTemp")
    private Double soilTemperature;

    private Double pressure;

    public PycomPayloadFieldsModel() {
    }

    public Double getAirHumidity() {
        return airHumidity;
    }

    public void setAirHumidity(Double airHumidity) {
        this.airHumidity = airHumidity;
    }

    public Double getSoilHumidity() {
        return soilHumidity;
    }

    public void setSoilHumidity(Double soilHumidity) {
        this.soilHumidity = soilHumidity;
    }

    public Double getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(Double airTemperature) {
        this.airTemperature = airTemperature;
    }

    public Double getSoilTemperature() {
        return soilTemperature;
    }

    public void setSoilTemperature(Double soilTemperature) {
        this.soilTemperature = soilTemperature;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }
}

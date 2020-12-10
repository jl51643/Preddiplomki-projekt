package hr.fer.tel.SmartAgriculture.models;

import hr.fer.tel.SmartAgriculture.entities.Measurement;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class MeasurementModel {

    private Long id;

    private String devId;

    private Date time;

    private float airHumidity;

    private float soilHumidity;

    private float airTemperature;

    private float soilTemperature;

    public MeasurementModel() {
    }

    public MeasurementModel(Measurement measurement) {
        this.id = measurement.getId();
        this.devId = measurement.getDevId();
        this.time = measurement.getTime();
        this.airHumidity = measurement.getAirHumidity();
        this.soilHumidity = measurement.getSoilHumidity();
        this.airTemperature = measurement.getAirTemperature();
        this.soilTemperature = measurement.getSoilTemperature();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
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

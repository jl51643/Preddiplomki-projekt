package hr.fer.tel.SmartAgriculture.models;

import hr.fer.tel.SmartAgriculture.entities.Measurement;

import java.util.Date;

public class MeasurementModel {

    private Long id;

    private DeviceModel device;

    private Date time;

    private float airHumidity;

    private float soilHumidity;

    private float airTemperature;

    private float soilTemperature;

    public MeasurementModel() {
    }

    public MeasurementModel(Measurement measurement) {
        this.id = measurement.getId();
        this.device = new DeviceModel(measurement.getDevice());
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

    public DeviceModel getDevice() {
        return device;
    }

    public void setDevice(DeviceModel device) {
        this.device = device;
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

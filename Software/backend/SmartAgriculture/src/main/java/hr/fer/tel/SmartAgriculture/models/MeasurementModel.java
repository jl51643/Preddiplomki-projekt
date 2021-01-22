package hr.fer.tel.SmartAgriculture.models;

import hr.fer.tel.SmartAgriculture.entities.Measurement;

import java.util.Date;

public class MeasurementModel {

    private Long id;

    private DeviceModel device;

    private Date time;

    private Double airHumidity;

    private Double soilHumidity;

    private Double airTemperature;

    private Double soilTemperature;

    private Double pressure;

    private Double luminosity;

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
        this.pressure = measurement.getPressure();
        this.luminosity = measurement.getLuminosity();
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

    public Double getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(Double luminosity) {
        this.luminosity = luminosity;
    }
}

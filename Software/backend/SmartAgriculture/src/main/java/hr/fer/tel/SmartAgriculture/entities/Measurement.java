package hr.fer.tel.SmartAgriculture.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "\"Measurements\"")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"dev_id\"")
    private String devId;

    private Date time;

    @Column(name = "\"air_humidity\"")
    private float airHumidity;

    @Column(name = "\"soil_humidity\"")
    private float soilHumidity;

    @Column(name = "\"air_temperature\"")
    private float airTemperature;

    @Column(name = "\"soil_temperature\"")
    private float soilTemperature;

    public Measurement() {
    }

    public Measurement(String devId, Date time, float airHumidity, float soilHumidity, float airTemperature, float soilTemperature) {
        this.devId = devId;
        this.time = time;
        this.airHumidity = airHumidity;
        this.soilHumidity = soilHumidity;
        this.airTemperature = airTemperature;
        this.soilTemperature = soilTemperature;
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

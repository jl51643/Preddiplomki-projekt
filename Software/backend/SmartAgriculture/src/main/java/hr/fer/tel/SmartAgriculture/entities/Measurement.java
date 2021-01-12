package hr.fer.tel.SmartAgriculture.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "\"Measurements\"")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "\"dev_id\"")
    private Device device;

    private Date time;

    @Column(name = "\"air_humidity\"")
    private Double airHumidity;

    @Column(name = "\"soil_humidity\"")
    private Double soilHumidity;

    @Column(name = "\"air_temperature\"")
    private Double airTemperature;

    @Column(name = "\"soil_temperature\"")
    private Double soilTemperature;

    @Column(precision = 8, scale = 2)
    private Double pressure;

    public Measurement() {
    }

    public Measurement(Device device, Date time, Double airHumidity, Double soilHumidity, Double airTemperature, Double soilTemperature, Double pressure) {
        this.device = device;
        this.time = time;
        this.airHumidity = airHumidity;
        this.soilHumidity = soilHumidity;
        this.airTemperature = airTemperature;
        this.soilTemperature = soilTemperature;
        this.pressure = pressure;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
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
}

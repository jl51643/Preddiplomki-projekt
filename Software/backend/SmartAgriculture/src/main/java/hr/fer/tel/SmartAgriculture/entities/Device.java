package hr.fer.tel.SmartAgriculture.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"Devices\"")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"devId\"")
    private String devId;

    @OneToMany(mappedBy = "device")
    private List<Measurement> measurements;

    @ManyToMany(mappedBy = "devices")
    private List<Culture> cultures = new ArrayList<>();

    public Device(){

    }

    public Device(String devId) {
        this.devId = devId;
    }

    public Device(Long id, String devId) {
        this.id = id;
        this.devId = devId;
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

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    public List<Culture> getCultures() {
        return cultures;
    }

    public void setCultures(List<Culture> cultures) {
        this.cultures = cultures;
    }
}
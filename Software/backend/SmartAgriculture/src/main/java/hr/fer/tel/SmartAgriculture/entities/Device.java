package hr.fer.tel.SmartAgriculture.entities;

import javax.persistence.*;

@Entity
@Table(name = "\"Devices\"")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"devId\"")
    private String devId;

    public Device(){

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
}
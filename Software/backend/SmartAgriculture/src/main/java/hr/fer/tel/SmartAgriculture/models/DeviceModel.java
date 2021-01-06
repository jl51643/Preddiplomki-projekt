package hr.fer.tel.SmartAgriculture.models;

import hr.fer.tel.SmartAgriculture.entities.Device;

public class DeviceModel {

    private Long id;
    private String devId;

    public DeviceModel(){

    }

    public DeviceModel(Device device) {
        this.id = device.getId();
        this.devId = device.getDevId();
    }

    public DeviceModel(Long id, String devId) {
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

    public Device toDevice(){
        Device device = new Device();
        device.setId(this.getId());
        device.setDevId(this.getDevId());
        return device;
    }
}

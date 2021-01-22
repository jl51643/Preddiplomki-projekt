package hr.fer.tel.SmartAgriculture.models;

import hr.fer.tel.SmartAgriculture.entities.Culture;

import java.util.List;
import java.util.stream.Collectors;

public class CultureModel {

    private Long cultureId;

    private String title;

    private List<DeviceModel> devices;

    private String description;


    public CultureModel() {
    }

    public CultureModel(Culture culture) {
        this.cultureId = culture.getCultureId();
        this.title = culture.getTitle();
        this.description = culture.getDescription();
        this.devices = culture.getDevices().stream().map(DeviceModel::new).collect(Collectors.toList());
    }

    public Long getCultureId() {
        return cultureId;
    }

    public void setCultureId(Long cultureId) {
        this.cultureId = cultureId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<DeviceModel> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceModel> devices) {
        this.devices = devices;
    }

}



package hr.fer.tel.SmartAgriculture.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import hr.fer.tel.SmartAgriculture.entities.Measurement;

public class PycomMeasurementModel {

    @JsonProperty("dev_id")
    private String devId;

    @JsonProperty("payload_fields")
    private PycomPayloadFieldsModel payloadFields;

    public PycomMeasurementModel() {
    }

    public String getDevId() {
        return this.devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public PycomPayloadFieldsModel getPayloadFields() {
        return this.payloadFields;
    }

    public void setPayloadFields(PycomPayloadFieldsModel payloadFields) {
        this.payloadFields = payloadFields;
    }

    public Measurement toMeasurement() {
        return new Measurement(this.devId,
                this.payloadFields.getTime(),
                this.payloadFields.getAirHumidity(),
                this.payloadFields.getSoilHumidity(),
                this.payloadFields.getAirTemperature(),
                this.payloadFields.getSoilTemperature());
    }
}

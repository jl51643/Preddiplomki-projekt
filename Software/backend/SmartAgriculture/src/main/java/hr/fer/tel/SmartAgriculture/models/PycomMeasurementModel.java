package hr.fer.tel.SmartAgriculture.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import hr.fer.tel.SmartAgriculture.entities.Measurement;

public class PycomMeasurementModel {

    @JsonProperty("dev_id")
    private String devId;

    @JsonProperty("payload_fields")
    private PycomPayloadFieldsModel payloadFields;

    private PycomMetadataModel metadata;

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

    public PycomMetadataModel getMetadata() {
        return metadata;
    }

    public void setMetadata(PycomMetadataModel metadata) {
        this.metadata = metadata;
    }

    public Measurement toMeasurement() {
        return new Measurement(this.devId,
                this.metadata.getTime(),
                this.payloadFields.getAirHumidity(),
                this.payloadFields.getSoilHumidity(),
                this.payloadFields.getAirTemperature(),
                this.payloadFields.getSoilTemperature());
    }
}

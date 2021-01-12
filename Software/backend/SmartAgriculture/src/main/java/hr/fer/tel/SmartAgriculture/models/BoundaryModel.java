package hr.fer.tel.SmartAgriculture.models;

import hr.fer.tel.SmartAgriculture.entities.Boundary;

import javax.persistence.Column;

public class BoundaryModel {

    private Long cultureId;

    private Double minAirTemperature;

    private Double maxAirTemperature;

    private Double minSoilTemperature;

    private Double maxSoilTemperature;

    private Double minAirHumidity;

    private Double maxAirHumidity;

    private Double minSoilHumidity;

    private Double maxSoilHumidity;

    private Double minPressure;

    private Double maxPressure;

    public BoundaryModel() {
    }

    public BoundaryModel(Boundary boundary) {
        this.cultureId = boundary.getCulture().getCultureId();
        this.minAirTemperature = boundary.getMinAirTemperature();
        this.maxAirTemperature = boundary.getMaxAirTemperature();
        this.minSoilTemperature = boundary.getMinSoilTemperature();
        this.maxSoilTemperature = boundary.getMaxSoilTemperature();
        this.minAirHumidity = boundary.getMinAirHumidity();
        this.maxAirHumidity = boundary.getMaxAirHumidity();
        this.minSoilHumidity = boundary.getMinSoilHumidity();
        this.maxSoilHumidity = boundary.getMaxSoilHumidity();
        this.minPressure = boundary.getMinPressure();
        this.maxPressure = boundary.getMaxPressure();
    }

    public Boundary toBoundary() {
        return new Boundary(null,
                null,
                null,
                minAirTemperature,
                maxAirTemperature,
                minSoilTemperature,
                maxSoilTemperature,
                minAirHumidity,
                maxAirHumidity,
                minSoilHumidity,
                maxSoilHumidity,
                minPressure,
                maxPressure);
    }

    public Long getCultureId() {
        return cultureId;
    }

    public void setCultureId(Long cultureId) {
        this.cultureId = cultureId;
    }

    public Double getMinAirTemperature() {
        return minAirTemperature;
    }

    public void setMinAirTemperature(Double minAirTemperature) {
        this.minAirTemperature = minAirTemperature;
    }

    public Double getMaxAirTemperature() {
        return maxAirTemperature;
    }

    public void setMaxAirTemperature(Double maxAirTemperature) {
        this.maxAirTemperature = maxAirTemperature;
    }

    public Double getMinSoilTemperature() {
        return minSoilTemperature;
    }

    public void setMinSoilTemperature(Double minSoilTemperature) {
        this.minSoilTemperature = minSoilTemperature;
    }

    public Double getMaxSoilTemperature() {
        return maxSoilTemperature;
    }

    public void setMaxSoilTemperature(Double maxSoilTemperature) {
        this.maxSoilTemperature = maxSoilTemperature;
    }

    public Double getMinAirHumidity() {
        return minAirHumidity;
    }

    public void setMinAirHumidity(Double minAirHumidity) {
        this.minAirHumidity = minAirHumidity;
    }

    public Double getMaxAirHumidity() {
        return maxAirHumidity;
    }

    public void setMaxAirHumidity(Double maxAirHumidity) {
        this.maxAirHumidity = maxAirHumidity;
    }

    public Double getMinSoilHumidity() {
        return minSoilHumidity;
    }

    public void setMinSoilHumidity(Double minSoilHumidity) {
        this.minSoilHumidity = minSoilHumidity;
    }

    public Double getMaxSoilHumidity() {
        return maxSoilHumidity;
    }

    public void setMaxSoilHumidity(Double maxSoilHumidity) {
        this.maxSoilHumidity = maxSoilHumidity;
    }

    public Double getMinPressure() {
        return minPressure;
    }

    public void setMinPressure(Double minPressure) {
        this.minPressure = minPressure;
    }

    public Double getMaxPressure() {
        return maxPressure;
    }

    public void setMaxPressure(Double maxPressure) {
        this.maxPressure = maxPressure;
    }
}

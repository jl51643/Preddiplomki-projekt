package hr.fer.tel.SmartAgriculture.entities;

import javax.persistence.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Entity
@Table(name = "\"Boundaries\"")
public class Boundary {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "\"user_id\"")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "\"culture_id\"")
    private Culture culture;

    @Column(name = "\"min_air_temperature\"")
    private Double minAirTemperature;

    @Column(name = "\"max_air_temperature\"")
    private Double maxAirTemperature;

    @Column(name = "\"min_soil_temperature\"")
    private Double minSoilTemperature;

    @Column(name = "\"max_soil_temperature\"")
    private Double maxSoilTemperature;

    @Column(name = "\"min_air_humidity\"")
    private Double minAirHumidity;

    @Column(name = "\"max_air_humidity\"")
    private Double maxAirHumidity;

    @Column(name = "\"min_soil_humidity\"")
    private Double minSoilHumidity;

    @Column(name = "\"max_soil_humidity\"")
    private Double maxSoilHumidity;

    @Column(name = "\"min_pressure\"")
    private Double minPressure;

    @Column(name = "\"max_pressure\"")
    private Double maxPressure;

    @Column(name = "\"min_luminosity\"")
    private Double minLuminosity;

    @Column(name = "\"max_luminosity\"")
    private Double maxLuminosity;

    public Boundary() {
    }

    public Boundary(Long id,
                    User user,
                    Culture culture,
                    Double minAirTemperature,
                    Double maxAirTemperature,
                    Double minSoilTemperature,
                    Double maxSoilTemperature,
                    Double minAirHumidity,
                    Double maxAirHumidity,
                    Double minSoilHumidity,
                    Double maxSoilHumidity,
                    Double minPressure,
                    Double maxPressure, Double minLuminosity, Double maxLuminosity) {
        this.id = id;
        this.user = user;
        this.culture = culture;
        this.minAirTemperature = minAirTemperature;
        this.maxAirTemperature = maxAirTemperature;
        this.minSoilTemperature = minSoilTemperature;
        this.maxSoilTemperature = maxSoilTemperature;
        this.minAirHumidity = minAirHumidity;
        this.maxAirHumidity = maxAirHumidity;
        this.minSoilHumidity = minSoilHumidity;
        this.maxSoilHumidity = maxSoilHumidity;
        this.minPressure = minPressure;
        this.maxPressure = maxPressure;
        this.minLuminosity = minLuminosity;
        this.maxLuminosity = maxLuminosity;
    }

    public static Function<Boundary, Double> getBoundaryValue(MeasurementType type, boolean isMax) {
        switch (type) {
            case PRESSURE:
                return isMax ? Boundary::getMaxPressure : Boundary::getMinPressure;
            case AIR_HUMIDITY:
                return isMax ? Boundary::getMaxAirHumidity : Boundary::getMinAirHumidity;
            case SOIL_HUMIDITY:
                return isMax ? Boundary::getMaxSoilHumidity : Boundary::getMinSoilHumidity;
            case AIR_TEMPERATURE:
                return isMax ? Boundary::getMaxAirTemperature : Boundary::getMinAirTemperature;
            case SOIL_TEMPERATURE:
                return isMax ? Boundary::getMaxSoilTemperature : Boundary::getMinSoilTemperature;
            case LUMINOSITY:
                return isMax ? Boundary::getMaxLuminosity : Boundary::getMinLuminosity;
        }

        return null;
    }

    public static BiConsumer<Boundary, Double> getBoundarySetter(MeasurementType type, boolean isMax) {
        switch (type) {
            case PRESSURE:
                return isMax ? Boundary::setMaxPressure : Boundary::setMinPressure;
            case AIR_HUMIDITY:
                return isMax ? Boundary::setMaxAirHumidity : Boundary::setMinAirHumidity;
            case SOIL_HUMIDITY:
                return isMax ? Boundary::setMaxSoilHumidity : Boundary::setMinSoilHumidity;
            case AIR_TEMPERATURE:
                return isMax ? Boundary::setMaxAirTemperature : Boundary::setMinAirTemperature;
            case SOIL_TEMPERATURE:
                return isMax ? Boundary::setMaxSoilTemperature : Boundary::setMinSoilTemperature;
            case LUMINOSITY:
                return isMax ? Boundary::setMaxLuminosity : Boundary::setMinLuminosity;
        }

        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Culture getCulture() {
        return culture;
    }

    public void setCulture(Culture culture) {
        this.culture = culture;
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

    public Double getMinLuminosity() {
        return minLuminosity;
    }

    public void setMinLuminosity(Double minLuminosity) {
        this.minLuminosity = minLuminosity;
    }

    public Double getMaxLuminosity() {
        return maxLuminosity;
    }

    public void setMaxLuminosity(Double maxLuminosity) {
        this.maxLuminosity = maxLuminosity;
    }
}

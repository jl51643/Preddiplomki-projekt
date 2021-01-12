package hr.fer.tel.SmartAgriculture.services;

import hr.fer.tel.SmartAgriculture.entities.*;
import hr.fer.tel.SmartAgriculture.models.PycomMeasurementModel;
import hr.fer.tel.SmartAgriculture.repositories.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class MeasurementService {

    private final UserRepository userRepository;
    private final MeasurementRepository measurementRepository;
    private final DeviceRepository deviceRepository;
    private final BoundaryRepository boundaryRepository;
    private final NotificationRepository notificationRepository;

    public MeasurementService(UserRepository userRepository, MeasurementRepository measurementRepository, DeviceRepository deviceRepository, BoundaryRepository boundaryRepository, NotificationRepository notificationRepository) {
        this.userRepository = userRepository;
        this.measurementRepository = measurementRepository;
        this.deviceRepository = deviceRepository;
        this.boundaryRepository = boundaryRepository;
        this.notificationRepository = notificationRepository;
    }

    public Long addPycomMeasurement(PycomMeasurementModel measurementModel) {
        Optional<Device> device = deviceRepository.getByDevId(measurementModel.getDevId());

        Device dev;
        if (device.isEmpty())
            dev = this.deviceRepository.save(new Device(measurementModel.getDevId()));
        else
            dev = device.get();

        Measurement measurement = new Measurement(dev,
                measurementModel.getMetadata().getTime(),
                measurementModel.getPayloadFields().getAirHumidity(),
                measurementModel.getPayloadFields().getSoilHumidity(),
                measurementModel.getPayloadFields().getAirTemperature(),
                measurementModel.getPayloadFields().getSoilTemperature(),
                measurementModel.getPayloadFields().getPressure());

        return addMeasurement(measurement);
    }

    public Long addMeasurement(Measurement measurement) {
        Measurement newMeasurement = this.measurementRepository.save(measurement);

        List<User> users = this.userRepository.findAll();
        List<Culture> cultures = newMeasurement.getDevice().getCultures();

        for (User user : users) {
            for (Culture culture : cultures) {
                Optional<Boundary> boundary = this.boundaryRepository.getByUserIdAndCultureCultureId(user.getId(), culture.getCultureId());

                if (boundary.isPresent()) {
                    Boundary b = boundary.get();

                    for (MeasurementType type : MeasurementType.values()) {
                        Double measurementValue = getMeasurementValue(type).apply(newMeasurement);

                        Double minValue = Boundary.getBoundaryValue(type, false).apply(b);
                        Double maxValue = Boundary.getBoundaryValue(type, true).apply(b);

                        if (measurementValue != null && maxValue != null && measurementValue > maxValue)
                            addNotification(newMeasurement.getTime(), user, culture, type, measurementValue, true);
                        else if (measurementValue != null && minValue != null && measurementValue < minValue)
                            addNotification(newMeasurement.getTime(), user, culture, type, measurementValue, false);
                    }
                }
            }
        }

        return newMeasurement.getId();
    }

    private Function<Measurement, Double> getMeasurementValue(MeasurementType type) {
        switch (type) {
            case PRESSURE:
                return Measurement::getPressure;
            case AIR_HUMIDITY:
                return Measurement::getAirHumidity;
            case SOIL_HUMIDITY:
                return Measurement::getSoilHumidity;
            case AIR_TEMPERTURE:
                return Measurement::getAirTemperature;
            case SOIL_TEMPERATURE:
                return Measurement::getSoilTemperature;
        }

        return null;
    }

    private void addNotification(Date time, User user, Culture culture, MeasurementType type, Double value, boolean isHigh) {
        Notification notification = new Notification(null, time, user, culture, type, value, isHigh, false);

        this.notificationRepository.save(notification);
    }

    public List<Measurement> getAll() {
        return this.measurementRepository.findAll();
    }
}

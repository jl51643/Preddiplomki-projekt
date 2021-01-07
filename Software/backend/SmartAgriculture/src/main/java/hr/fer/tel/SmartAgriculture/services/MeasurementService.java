package hr.fer.tel.SmartAgriculture.services;

import hr.fer.tel.SmartAgriculture.entities.Device;
import hr.fer.tel.SmartAgriculture.entities.Measurement;
import hr.fer.tel.SmartAgriculture.models.PycomMeasurementModel;
import hr.fer.tel.SmartAgriculture.repositories.DeviceRepository;
import hr.fer.tel.SmartAgriculture.repositories.MeasurementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final DeviceRepository deviceRepository;

    public MeasurementService(MeasurementRepository measurementRepository, DeviceRepository deviceRepository) {
        this.measurementRepository = measurementRepository;
        this.deviceRepository = deviceRepository;
    }

    public Long addMeasurement(PycomMeasurementModel measurementModel) {
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
                measurementModel.getPayloadFields().getSoilTemperature());

        return addMeasurement(measurement);
    }

    public Long addMeasurement(Measurement measurement) {
        Measurement newMeasurement = this.measurementRepository.save(measurement);

        return newMeasurement.getId();
    }

    public List<Measurement> getAll() {
        return this.measurementRepository.findAll();
    }
}

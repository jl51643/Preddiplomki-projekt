package hr.fer.tel.SmartAgriculture.services;

import hr.fer.tel.SmartAgriculture.entities.Measurement;
import hr.fer.tel.SmartAgriculture.models.PycomMeasurementModel;
import hr.fer.tel.SmartAgriculture.repositories.MeasurementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public Long addMeasurement(PycomMeasurementModel measurementModel) {
        return addMeasurement(measurementModel.toMeasurement());
    }

    public Long addMeasurement(Measurement measurement) {
        Measurement newMeasurement = this.measurementRepository.save(measurement);

        return newMeasurement.getId();
    }

    public List<Measurement> getAll() {
        return this.measurementRepository.findAll();
    }
}

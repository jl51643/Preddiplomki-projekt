package hr.fer.tel.SmartAgriculture.controllers;

import hr.fer.tel.SmartAgriculture.models.MeasurementModel;
import hr.fer.tel.SmartAgriculture.models.PycomMeasurementModel;
import hr.fer.tel.SmartAgriculture.services.MeasurementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/measurement")
public class MeasurementController {

    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping("/pycom/add")
    public ResponseEntity<?> pycomAddMeasurement(@RequestBody PycomMeasurementModel measurementModel) throws URISyntaxException {
        Long id = this.measurementService.addPycomMeasurement(measurementModel);
        return ResponseEntity.created(new URI("/api/measurement/" + id)).build();
    }

    @GetMapping("/all")
    public List<MeasurementModel> getAllMeasurements() {
        return this.measurementService
                .getAll()
                .stream()
                .map(MeasurementModel::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/last")
    public List<MeasurementModel> getLastMeasurements() {
        return this.measurementService
                .getAll()
                .stream()
                .sorted(Comparator.reverseOrder())
                .limit(10)
                .map(MeasurementModel::new)
                .collect(Collectors.toList());
    }
}

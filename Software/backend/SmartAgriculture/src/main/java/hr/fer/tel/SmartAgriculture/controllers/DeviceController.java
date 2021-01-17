package hr.fer.tel.SmartAgriculture.controllers;

import hr.fer.tel.SmartAgriculture.models.DeviceModel;
import hr.fer.tel.SmartAgriculture.services.DeviceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("")
    public List<DeviceModel> getAllDevices() {
        return this.deviceService.getAll().stream().map(DeviceModel::new).collect(Collectors.toList());
    }

}

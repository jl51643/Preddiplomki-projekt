package hr.fer.tel.SmartAgriculture.services;

import hr.fer.tel.SmartAgriculture.entities.Device;
import hr.fer.tel.SmartAgriculture.repositories.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<Device> getAll() {
        return deviceRepository.findAll();
    }
}

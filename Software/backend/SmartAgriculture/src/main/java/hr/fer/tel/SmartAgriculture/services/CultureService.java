package hr.fer.tel.SmartAgriculture.services;

import hr.fer.tel.SmartAgriculture.entities.Culture;
import hr.fer.tel.SmartAgriculture.entities.Device;
import hr.fer.tel.SmartAgriculture.models.CultureModel;
import hr.fer.tel.SmartAgriculture.models.DeviceModel;
import hr.fer.tel.SmartAgriculture.repositories.CultureRepository;
import hr.fer.tel.SmartAgriculture.repositories.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CultureService {

    private final CultureRepository cultureRepository;
    private final DeviceRepository deviceRepository;

    public CultureService(CultureRepository cultureRepository, DeviceRepository deviceRepository) {
        this.cultureRepository = cultureRepository;
        this.deviceRepository = deviceRepository;
    }


    public Long addCulture(CultureModel cultureModel) {

        Culture newCulture = new Culture();
        newCulture.setTitle(cultureModel.getTitle());
        newCulture.setDescription(cultureModel.getDescription());
        newCulture.setDevices(cultureModel.getDevices().stream().map(DeviceModel::toDevice).collect(Collectors.toList()));

        newCulture = this.cultureRepository.save(newCulture);

        return newCulture.getCultureId();
    }

    public List<Culture> getAll() {
        return this.cultureRepository.findAll();
    }

    public boolean deleteCulture(Long id) {
        boolean exists = this.cultureRepository.existsById(id);
        if(exists){
            this.cultureRepository.deleteById(id);
        }
        return exists;
    }

    public void addDeviceToCulture(Long id, Long deviceId) {
        Optional<Culture> culture = this.cultureRepository.findById(id);
        if(culture.isEmpty())
            return;

        Culture existingCulture = culture.get();

        Optional<Device> device = this.deviceRepository.findById(deviceId);
        existingCulture.getDevices().add(device.get());

        this.cultureRepository.save(existingCulture);
    }

    public boolean deleteDeviceFromCulture(Long cultureId, Long devId) {
        boolean existsCulture = this.cultureRepository.existsById(cultureId);
        boolean existsDevice = this.deviceRepository.existsById(devId);
        if(!existsCulture || !existsDevice) return false;
        Optional<Culture> culture = this.cultureRepository.findById(cultureId);
        Optional<Device> device = this.deviceRepository.findById(devId);
        Culture existingCulture = culture.get();
        existingCulture.getDevices().remove(device.get());
        this.cultureRepository.save(existingCulture);
        return true;

    }

}




package hr.fer.tel.SmartAgriculture.controllers;

import hr.fer.tel.SmartAgriculture.models.CultureModel;
import hr.fer.tel.SmartAgriculture.models.DeviceModel;
import hr.fer.tel.SmartAgriculture.services.CultureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/culture")
public class CultureController {

    private final CultureService cultureService;

    public CultureController(CultureService cultureService) {
        this.cultureService = cultureService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> cultureAdd(@RequestBody CultureModel cultureModel) throws URISyntaxException {
        Long id = this.cultureService.addCulture(cultureModel);
        return ResponseEntity.created(new URI("/api/culture/" + id)).build();
    }

    @GetMapping("/all")
    public List<CultureModel> getAllCultures() {
        return this.cultureService
                .getAll()
                .stream()
                .map(CultureModel::new)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCulture(@PathVariable Long id) {
        boolean deleted = this.cultureService.deleteCulture(id);
        if(deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/devices/add")
    public ResponseEntity<?> addDeviceToCulture(@PathVariable Long id, @RequestBody DeviceModel deviceModel) {
        this.cultureService.addDeviceToCulture(id, deviceModel);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cultureId}/devices/delete/{devId}")
    public ResponseEntity<?> deleteDeviceFromCulture(@PathVariable Long cultureId, @PathVariable Long devId) {
        boolean deleted = this.cultureService.deleteDeviceFromCulture(cultureId, devId);
        if(deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}


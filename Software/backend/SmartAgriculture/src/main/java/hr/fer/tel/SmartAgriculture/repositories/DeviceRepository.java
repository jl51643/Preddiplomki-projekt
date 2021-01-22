package hr.fer.tel.SmartAgriculture.repositories;
import hr.fer.tel.SmartAgriculture.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    Optional<Device> getByDevId(String devId);

}

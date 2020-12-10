package hr.fer.tel.SmartAgriculture.repositories;

import hr.fer.tel.SmartAgriculture.entities.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
}

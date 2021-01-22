package hr.fer.tel.SmartAgriculture.repositories;

import hr.fer.tel.SmartAgriculture.entities.Boundary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoundaryRepository extends JpaRepository<Boundary, Long> {

    Optional<Boundary> getByUserIdAndCultureCultureId(Long userId, Long cultureId);

}

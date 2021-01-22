package hr.fer.tel.SmartAgriculture.repositories;

import hr.fer.tel.SmartAgriculture.entities.Culture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CultureRepository extends JpaRepository<Culture, Long> {
}

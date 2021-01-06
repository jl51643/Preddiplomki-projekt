package hr.fer.tel.SmartAgriculture.repositories;

import hr.fer.tel.SmartAgriculture.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getByUsername(String username);

}

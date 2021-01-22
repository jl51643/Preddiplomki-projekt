package hr.fer.tel.SmartAgriculture.repositories;

import hr.fer.tel.SmartAgriculture.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> getAllByUserIdAndSentIsFalse(Long userId);

}

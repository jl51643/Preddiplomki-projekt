package hr.fer.tel.SmartAgriculture.controllers;

import hr.fer.tel.SmartAgriculture.entities.Notification;
import hr.fer.tel.SmartAgriculture.models.NotificationModel;
import hr.fer.tel.SmartAgriculture.repositories.NotificationRepository;
import hr.fer.tel.SmartAgriculture.security.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationRepository notificationRepository;

    public NotificationController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @GetMapping()
    public List<NotificationModel> getAllNotifications() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Notification> notifications = this.notificationRepository.getAllByUserIdAndSentIsFalse(userPrincipal.getUser().getId());

        for (Notification notification : notifications) {
            notification.setSent(true);
        }

        this.notificationRepository.saveAll(notifications);

        return notifications.stream().map(NotificationModel::new).collect(Collectors.toList());
    }

}

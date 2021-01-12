package hr.fer.tel.SmartAgriculture.models;

import hr.fer.tel.SmartAgriculture.entities.Notification;

import java.util.Date;

public class NotificationModel {

    private Date time;

    private Long cultureId;

    private String message;

    public NotificationModel() {
    }

    public NotificationModel(Notification notification) {
        this.time = notification.getTime();
        this.cultureId = notification.getCulture().getCultureId();
        this.message = "Vrijednost " + notification.getType().getText() + " je " + (notification.isHigh() ? "iznad" : "ispod") + " granice! Vrijednost: " + notification.getValue();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getCultureId() {
        return cultureId;
    }

    public void setCultureId(Long cultureId) {
        this.cultureId = cultureId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

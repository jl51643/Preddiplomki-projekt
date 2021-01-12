package hr.fer.tel.SmartAgriculture.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "\"Notifications\"")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date time;

    @ManyToOne
    @JoinColumn(name = "\"user_id\"")
    private User user;

    @ManyToOne
    @JoinColumn(name = "\"culture_id\"")
    private Culture culture;

    @Enumerated(EnumType.STRING)
    private MeasurementType type;

    @Column(precision = 8, scale = 2)
    private Double value;

    @Column(name="\"is_high\"")
    private boolean isHigh;

    private boolean sent;

    public Notification() {
    }

    public Notification(Long id, Date time, User user, Culture culture, MeasurementType type, Double value, boolean isHigh, boolean sent) {
        this.id = id;
        this.time = time;
        this.user = user;
        this.culture = culture;
        this.type = type;
        this.value = value;
        this.isHigh = isHigh;
        this.sent = sent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Culture getCulture() {
        return culture;
    }

    public void setCulture(Culture culture) {
        this.culture = culture;
    }

    public MeasurementType getType() {
        return type;
    }

    public void setType(MeasurementType type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public boolean isHigh() {
        return isHigh;
    }

    public void setHigh(boolean high) {
        isHigh = high;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }
}

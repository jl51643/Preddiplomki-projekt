package hr.fer.tel.SmartAgriculture.entities;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "\"Cultures\"")
public class Culture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"cultureId\"")
    private Long cultureId;

    @Column(name = "\"title\"")
    private String title;

    @Column(name = "\"description\"")
    private String description;

    @OneToMany(mappedBy = "culture")
    private List<Boundary> boundaries;

    @OneToMany(mappedBy = "culture")
    private List<Notification> notifications;

    @ManyToMany
    @JoinTable(
            name = "\"Culture_Devices\"",
            joinColumns = @JoinColumn(name = "\"culture_id\""),
            inverseJoinColumns = @JoinColumn(name = "\"device_id\""))
    private List<Device> devices;

    public Culture() {
    }

    public Culture(Long cultureId, String title, String description, List<Device> devices) {
        this.cultureId = cultureId;
        this.title = title;
        this.description = description;
        this.devices = devices;
    }


    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public Long getCultureId() {
        return cultureId;
    }

    public void setCultureId(Long cultureId) {
        this.cultureId = cultureId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Boundary> getBoundaries() {
        return boundaries;
    }

    public void setBoundaries(List<Boundary> boundaries) {
        this.boundaries = boundaries;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}


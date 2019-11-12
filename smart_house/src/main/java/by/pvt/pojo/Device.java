package by.pvt.pojo;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Device implements Serializable {

    @Transient
    private final static long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 15)
    private String name;

    @Column(unique = true, nullable = false, length = 15)
    private String serialNumber;

    @Column(nullable = false, length = 5)
    private String ipAddress;

    @Column
    private String location;

    @OneToMany(mappedBy = "device",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Sensor> sensors;

    public Device(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", pinCod='" + ipAddress + '\'' +
                '}';
    }
}

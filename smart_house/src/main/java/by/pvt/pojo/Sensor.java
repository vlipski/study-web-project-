package by.pvt.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Sensor implements Serializable {

    @Transient
    private final static long serialVersionUID = 5L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL)
    private List<SensorValue> valueSensors;

    @ManyToOne
    private Device device;


    public Sensor(String name, Device device) {
        this.name = name;
        this.device = device;
    }

    public void setOneValue(SensorValue sensorValue) {
        if (valueSensors == null) {
            valueSensors = new ArrayList<>();
            valueSensors.add(sensorValue);
        } else {
            valueSensors.add(sensorValue);
        }
    }
}

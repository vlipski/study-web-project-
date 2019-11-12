package by.pvt.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class SensorValue implements Serializable {

    @Transient
    private final static long serialVersionUID = 6L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer value;

    @Column
    private Date date;

    @ManyToOne
    private Sensor sensor;


    public SensorValue(Integer value, Date date, Sensor sensor) {
        this.value = value;
        this.date = date;
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "SensorValue{" +
                "id=" + id +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}

package by.pvt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable {

    @Transient
    private final static long serialVersionUID = 2L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_thing")
    private String idThing;

    @Column
    private Date date;

    @Column
    private String body;

    @Column(name = "is_read")
    private Boolean isRead;


}

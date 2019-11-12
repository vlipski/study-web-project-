package by.pvt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Message implements Serializable {

    @Transient
    private final static long serialVersionUID = 2L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String idThing;

    @Column
    private Date date;

    @Column
    private String body;

    @Column
    private Boolean isRead;

    public Message(String idThing, Date date, String body, Boolean isRead) {
        this.idThing = idThing;
        this.date = date;
        this.body = body;
        this.isRead = isRead;
    }
}

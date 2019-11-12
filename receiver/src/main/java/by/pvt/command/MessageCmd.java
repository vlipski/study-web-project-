package by.pvt.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageCmd {

    @NotEmpty
    @NotNull
    private String idThing;

    @NotEmpty
    @NotNull
    private String body;

    @NotNull
    @Past
    private Date date;

}

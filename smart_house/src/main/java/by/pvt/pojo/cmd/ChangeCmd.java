package by.pvt.pojo.cmd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeCmd {

    private Long idDevice;

    private Integer power;

    private Integer temp;

}

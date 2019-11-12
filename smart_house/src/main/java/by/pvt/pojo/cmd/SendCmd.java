package by.pvt.pojo.cmd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendCmd {

    private String serialNumber;

    private Integer power;

    private Integer temp;

}

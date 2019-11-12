package by.pvt.pojo.cmd;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceCmd {

    private String name;

    private String serialNumber;

    private String pinCod;

}

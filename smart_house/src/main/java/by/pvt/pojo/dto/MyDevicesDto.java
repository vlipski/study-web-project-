package by.pvt.pojo.dto;

import by.pvt.pojo.cmd.SendCmd;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyDevicesDto {

    private Long id;
    private String name;
    private List<ShowDevicesDto> showDevicesDtoList;
    private SendCmd sendCmd;

    public MyDevicesDto(List<ShowDevicesDto> showDevicesDtoList) {
        this.showDevicesDtoList = showDevicesDtoList;
    }
}

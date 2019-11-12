package by.pvt.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowHistoryDto {

    private Long id;
    private String name;
    private List<ShowDevicesDto> showDevicesDtoList;
    private List<ShowSensorsDto> showSensorsDtoList;

    public ShowHistoryDto(List<ShowDevicesDto> showDevicesDtoList) {
        this.showDevicesDtoList = showDevicesDtoList;
    }
}

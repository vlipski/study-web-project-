package by.pvt.pojo.dto;

import by.pvt.pojo.Device;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataChartDto {

    private String from;

    private String to;

    private Device device;

    private List<String> metaData;

    private List<SensorValueDto> sensorValueDtoList;

    public DataChartDto(List<SensorValueDto> sensorValueDtoList) {
        this.sensorValueDtoList = sensorValueDtoList;
    }
}

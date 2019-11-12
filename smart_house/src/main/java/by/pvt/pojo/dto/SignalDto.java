package by.pvt.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignalDto {

    private String ipAddress;

    private String location;

    private Map<String, Integer> values;
}

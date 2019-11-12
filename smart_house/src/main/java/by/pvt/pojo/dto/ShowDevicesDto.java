package by.pvt.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowDevicesDto {

    private Long id;
    private String name;
    private String serialNumber;
}

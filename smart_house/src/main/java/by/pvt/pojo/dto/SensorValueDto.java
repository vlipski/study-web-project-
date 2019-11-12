package by.pvt.pojo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorValueDto {

    private Calendar date;
    private List<Integer> values;


    {
        values = new ArrayList<>();
    }

    public SensorValueDto(Calendar date) {
        this.date = date;
    }


    public void setOneValue(Integer value) {
        values.add(value);
    }
}

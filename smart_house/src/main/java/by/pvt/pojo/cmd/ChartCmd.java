package by.pvt.pojo.cmd;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChartCmd {
    private List<String> id;
    private Long idDevice;
    private String from;
    private String to;
}

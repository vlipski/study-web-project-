package by.pvt.util;



import by.pvt.pojo.dto.SignalDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class Coder {

    private static final Logger log = Logger.getLogger(Coder.class);

    @Autowired
    ObjectMapper mapper;


    public SignalDto deserialization(String json) {
        try {
            return mapper.readValue(json, SignalDto.class);
        } catch (IOException e) {
            log.error(e);
            return null;
        }
    }

}

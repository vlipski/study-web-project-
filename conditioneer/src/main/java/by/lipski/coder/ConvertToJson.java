package by.lipski.coder;

import by.lipski.entity.Message;
import by.lipski.entity.ReceiverCmd;
import by.lipski.entity.Signal;
import by.lipski.entity.SignalDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConvertToJson {

    private static final Logger log = Logger.getLogger(ConvertToJson.class);
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static ObjectMapper objectMapper1 = new ObjectMapper();


    static String serialization(Signal signal) {
        try {
            Map<String, Integer> values = Map.of("power",signal.getPower(),"temp", signal.getTemp());
            SignalDto signalDto = new SignalDto(values);
//            System.out.println(signalDto);
            return objectMapper.writeValueAsString(signalDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error(e);
            return null;
        }
    }

    public static String serialization(Message message) {
        try {
            return objectMapper1.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error(e);
            return null;
        }
    }

    public static ReceiverCmd deserialization(String json) {
        try {
            return objectMapper.readValue(json, ReceiverCmd.class);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e);
            return null;
        }
    }

    public static String fromReceiverToString(ReceiverCmd receiverCmd) {
        try {
            return objectMapper1.writeValueAsString(receiverCmd);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}

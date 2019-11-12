package by.lipski.coder;

import by.lipski.entity.Message;
import by.lipski.entity.Signal;


public class Coder {

    public static Message encode(Signal signal) {
        return new Message(
                ConvertToJson.serialization(signal));
    }

    public Signal decode(Message message) {
        return null;
    }
}

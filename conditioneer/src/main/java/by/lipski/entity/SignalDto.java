package by.lipski.entity;

import java.util.Map;

public class SignalDto {

    private final String ipAddress = "3030";
    private final String location = "localhost";

    private Map<String, Integer> values;

    public SignalDto() {
    }

    public SignalDto(Map<String, Integer> values) {
        this.values = values;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getLocation() {
        return location;
    }

    public Map<String, Integer> getValues() {
        return values;
    }

    public void setValues(Map<String, Integer> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "SignalDto{" +
                "ipAddress=" + ipAddress +
                ", location='" + location + '\'' +
                ", values=" + values +
                '}';
    }
}

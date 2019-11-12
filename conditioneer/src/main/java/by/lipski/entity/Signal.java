package by.lipski.entity;

import java.util.Date;

public class Signal {

    private final String ipAddress = "3030";
    private final String location = "localhost";

    private int temp;

    private int power;

    private boolean onOff;

    public String getIpAddress() {
        return ipAddress;
    }

    public String getLocation() {
        return location;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public boolean isOnOff() {
        return onOff;
    }

    public void setOnOff(boolean onOff) {
        this.onOff = onOff;
    }


    @Override
    public String toString() {
        return "Signal{" +
                "ipAddress=" + ipAddress +
                ", location='" + location + '\'' +
                ", temp=" + temp +
                ", power=" + power +
                ", onOff=" + onOff +
                '}';
    }
}

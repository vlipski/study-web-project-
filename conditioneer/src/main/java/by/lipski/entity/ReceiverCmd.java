package by.lipski.entity;




public class ReceiverCmd {

    private String serialNumber;

    private Integer power;

    private Integer temp;

    public ReceiverCmd(String serialNumber, Integer power, Integer temp) {
        this.serialNumber = serialNumber;
        this.power = power;
        this.temp = temp;
    }

    public ReceiverCmd() {
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "ReceiverCmd{" +
                "serialNumber='" + serialNumber + '\'' +
                ", power=" + power +
                ", temp=" + temp +
                '}';
    }
}

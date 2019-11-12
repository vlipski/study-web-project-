package by.pvt.service;

import by.pvt.pojo.Device;
import by.pvt.pojo.Message;
import by.pvt.pojo.Sensor;
import by.pvt.pojo.SensorValue;
import by.pvt.pojo.dto.SignalDto;
import by.pvt.repository.*;
import by.pvt.util.Coder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class MessageService {


    @Autowired
    MessageRepository messageRepository;

    @Autowired
    Coder coder;

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    SensorValueRepository sensorValueRepository;


    @Transactional
    public void handleMessages() {
        messageRepository.getNotRead()
                .stream()
                .collect(Collectors.groupingBy(Message::getIdThing))
                .forEach((idThing, messages) -> {
                    Device existDevice = deviceRepository.getBySerialNumber(idThing);
                    if (existDevice == null) {
                        Device device = new Device(idThing);
                        fromMessagesToNewDevice(messages, device);
                        deviceRepository.save(device);
                    } else {
                        fromMessagesToExistDevice(messages, existDevice);
                    }
                });
    }

    private void fromMessagesToExistDevice(List<Message> messageList, Device device) {
        List<Sensor> sensorList = device.getSensors();
        messageList
                .forEach(message -> {
                    Map<String, Integer> sensorNameValue = coder.deserialization(message.getBody()).getValues();
                    sensorList.forEach(sensor ->
                            sensorValueRepository.save(new SensorValue(
                                    sensorNameValue.get(sensor.getName()),
                                    message.getDate(),
                                    sensor))
                    );
                    message.setIsRead(true);
                    messageRepository.update(message);
                });
    }

    private void fromMessagesToNewDevice(List<Message> messageList, Device device) {
        SignalDto signalDto = coder.deserialization(messageList.get(0).getBody());
        List<Sensor> sensorList = signalDto
                .getValues()
                .keySet()
                .stream()
                .map(sensorName -> new Sensor(sensorName, device))
                .collect(Collectors.toList());

        messageList
                .forEach(message -> {
                    Map<String, Integer> sensorNameValue = coder.deserialization(message.getBody()).getValues();
                    sensorList.forEach(sensor ->
                            sensor.setOneValue(new SensorValue(
                                    sensorNameValue.get(sensor.getName()),
                                    message.getDate(),
                                    sensor))
                    );
                    message.setIsRead(true);
                    messageRepository.update(message);
                });
        device.setIpAddress(signalDto.getIpAddress());
        device.setSensors(sensorList);
        device.setLocation(signalDto.getLocation());
    }
}

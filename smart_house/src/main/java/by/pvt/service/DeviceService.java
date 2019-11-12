package by.pvt.service;

import by.pvt.pojo.*;
import by.pvt.pojo.cmd.DeviceCmd;
import by.pvt.pojo.dto.MyDevicesDto;
import by.pvt.pojo.dto.ShowDevicesDto;
import by.pvt.pojo.dto.ShowHistoryDto;
import by.pvt.repository.DeviceRepository;
import by.pvt.repository.SensorValueRepository;
import by.pvt.repository.UserRepository;
import by.pvt.util.DataChartBuilding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.stream.Collectors;


@Service
public class DeviceService {

    @Autowired
    SensorValueRepository sensorValueRepository;

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    DataChartBuilding dataChartBuilding;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public String addDevice(DeviceCmd deviceCmd, String userName) {
        Device device = deviceRepository.getBySerialNumber(deviceCmd.getSerialNumber());
        if (device == null) {
            return "A device with such a serial number does not exist";
        }

        if (!device.getIpAddress().equals(deviceCmd.getPinCod())) {
            return "PIN code is not correct";
        }
        device.setName(deviceCmd.getName());
        deviceRepository.update(device);
        User user = userRepository.getUserByLogin(userName);
        user.getDevices().add(device);
        userRepository.update(user);
        return null;
    }

    @Transactional
    public MyDevicesDto getDevicesByUserName(String username) {
        return new MyDevicesDto(userRepository.getUserByLogin(username).getDevices().stream()
                .map(device -> new ShowDevicesDto(device.getId(), device.getName(), device.getSerialNumber()))
                .collect(Collectors.toList()));
    }

    @Transactional
    public ShowHistoryDto getShowHistoryDto(String username) {
        return new ShowHistoryDto(userRepository.getUserByLogin(username).getDevices().stream()
                .map(device -> new ShowDevicesDto(device.getId(), device.getName(), device.getSerialNumber()))
                .collect(Collectors.toList()));
    }
}




package by.pvt.service;

import by.pvt.io.IOService;
import by.pvt.pojo.Device;
import by.pvt.pojo.SensorValue;
import by.pvt.pojo.User;
import by.pvt.pojo.cmd.ChangeCmd;
import by.pvt.pojo.cmd.ChartCmd;
import by.pvt.pojo.cmd.SendCmd;
import by.pvt.pojo.dto.*;
import by.pvt.repository.DeviceRepository;
import by.pvt.repository.SensorValueRepository;
import by.pvt.repository.UserRepository;
import by.pvt.util.DataChartBuilding;
import by.pvt.util.ParsDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SensorService {


    @Autowired
    SensorValueRepository sensorValueRepository;

    @Autowired
    DataChartBuilding dataChartBuilding;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    ParsDate parsDate;

    @Autowired
    IOService ioService;

    @Transactional
    public DataChartDto getDataForChart(ChartCmd chartCmd, String userName) {
        List<SensorValue> sensorValues = new ArrayList<>();
        List<String> metaData = new ArrayList<>();
        Date from = parsDate.parsStringToDate(chartCmd.getFrom());
        Date to = parsDate.parsStringToDate(chartCmd.getTo());
        Device device = userRepository.getUserByLogin(userName).getDevices().stream()
                .filter(deviceFilter -> deviceFilter.getId().equals(chartCmd.getIdDevice()))
                .findFirst()
                .orElse(null);

        if (device != null) {
            chartCmd.getId()
                    .forEach(id -> {
                        String[] splitId = id.split("amp&");
                        metaData.add(splitId[0]);
                        List<SensorValue> sensorValueList = sensorValueRepository
                                .getSensorValuesByIdAndDate(Long.valueOf(splitId[1]), from, to);
                        sensorValues.addAll(sensorValueList);
                    });
            DataChartDto dataChartDto = dataChartBuilding.getDataChart(sensorValues, from, to);
            dataChartDto.setMetaData(metaData);
            dataChartDto.setDevice(device);
            dataChartDto.setFrom(chartCmd.getFrom());
            dataChartDto.setTo(chartCmd.getTo());
            return dataChartDto;
        }
        return null;
    }

    @Transactional
    public DataChartDto getDataForPdf(ChartCmd chartCmd, String userName) {
        List<SensorValue> sensorValues = new ArrayList<>();
        List<String> metaData = new ArrayList<>();
        Date from = parsDate.parsStringToDate(chartCmd.getFrom());
        Date to = parsDate.parsStringToDate(chartCmd.getTo());
        Device device = userRepository.getUserByLogin(userName).getDevices().stream()
                .filter(deviceFilter -> deviceFilter.getId().equals(chartCmd.getIdDevice()))
                .findFirst()
                .orElse(null);

        if (device != null) {
            chartCmd.getId()
                    .forEach(id -> {
                        String[] splitId = id.split("amp&");
                        metaData.add(splitId[0]);
                        List<SensorValue> sensorValueList = sensorValueRepository
                                .getSensorValuesByIdAndDate(Long.valueOf(splitId[1]), from, to);
                        sensorValues.addAll(sensorValueList);
                    });
            DataChartDto dataChartDto = dataChartBuilding.getDataPdf(sensorValues, from, to);
            dataChartDto.setMetaData(metaData);
            dataChartDto.setDevice(device);
            dataChartDto.setFrom(chartCmd.getFrom());
            dataChartDto.setTo(chartCmd.getTo());
            return dataChartDto;
        }
        return null;
    }

    @Transactional
    public MyDevicesDto getValuesSensors(Long id, String username) {

        User user = userRepository.getUserByLogin(username);
        List<Device> deviceList = user.getDevices();
        Device device = deviceList.stream()
                .filter(filterDevice -> filterDevice.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (device != null) {
            SendCmd sendCmd = ioService.send(device.getLocation(), device.getIpAddress());

            return new MyDevicesDto(
                    id,
                    device.getName(),
                    deviceList.stream()
                            .map(mapDevice -> new ShowDevicesDto(mapDevice.getId(), mapDevice.getName(), mapDevice.getSerialNumber()))
                            .collect(Collectors.toList()),
                    sendCmd
            );
        }
        return null;
    }

    @Transactional
    public MyDevicesDto send(ChangeCmd changeCmd, String username) {
        User user = userRepository.getUserByLogin(username);
        List<Device> deviceList = user.getDevices();
        Device device = deviceList.stream()
                .filter(filterDevice -> filterDevice.getId().equals(changeCmd.getIdDevice()))
                .findFirst()
                .orElse(null);

        if (device != null) {
            ioService.send(device.getLocation(), device.getIpAddress(), changeCmd, device.getSerialNumber());
            return new MyDevicesDto(
                    changeCmd.getIdDevice(),
                    device.getName(),
                    deviceList.stream()
                            .map(mapDevice -> new ShowDevicesDto(
                                    mapDevice.getId(),
                                    mapDevice.getName(),
                                    mapDevice.getSerialNumber()))
                            .collect(Collectors.toList()),
                    new SendCmd(device.getSerialNumber(), changeCmd.getPower(), changeCmd.getTemp())
            );
        }
        return null;
    }

    @Transactional
    public ShowHistoryDto getShowHistoryDto(Long id, String username) {
        User user = userRepository.getUserByLogin(username);
        List<Device> deviceList = user.getDevices();
        Device device = Objects.requireNonNull(deviceList.stream()
                .filter(dev -> dev.getId().equals(id))
                .findFirst()
                .orElse(null));

        return new ShowHistoryDto(
                id,
                device.getName(),
                deviceList.stream()
                        .map(dev -> new ShowDevicesDto(dev.getId(), dev.getName(), dev.getSerialNumber()))
                        .collect(Collectors.toList()),


                device.getSensors()
                        .stream()
                        .map(sensor -> new ShowSensorsDto(sensor.getId(), sensor.getName()))
                        .collect(Collectors.toList())
        );
    }
}

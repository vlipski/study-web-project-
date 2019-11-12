package by.pvt.util;


import by.pvt.pojo.SensorValue;
import by.pvt.pojo.dto.DataChartDto;
import by.pvt.pojo.dto.SensorValueDto;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class DataChartBuilding {

    public DataChartDto getDataChart(List<SensorValue> sensorValueList, Date from, Date to) {

        List<SensorValueDto> sensorValueDtoList = new ArrayList<>();
        sensorValueList.stream()
                .collect(Collectors.groupingBy(SensorValue::getDate))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(dateListEntry -> {
                    Calendar date = Calendar.getInstance();
                    if (sensorValueDtoList.size() == 0) {
                        date.setTime(from);
                        if (from.getTime() < dateListEntry.getKey().getTime()) {
                            sensorValueDtoList.add(getSensorValueDtoZero(dateListEntry, date));
                            Calendar nextDate = Calendar.getInstance();
                            nextDate.setTime(dateListEntry.getKey());
                            sensorValueDtoList.add(getSensorValueDtoZero(dateListEntry, nextDate));
                            sensorValueDtoList.add(getSensorValueDto(dateListEntry, nextDate));
                        } else {
                            sensorValueDtoList.add(getSensorValueDto(dateListEntry, date));
                        }
                    } else {
                        date.setTime(dateListEntry.getKey());
                        sensorValueDtoList.add(new SensorValueDto(
                                date, sensorValueDtoList.get(sensorValueDtoList.size() - 1).getValues())
                        );
                        sensorValueDtoList.add(getSensorValueDto(dateListEntry, date));
                    }
                });
        Calendar date = Calendar.getInstance();
        date.setTime(to);
        if (sensorValueDtoList.get(sensorValueDtoList.size() - 1).getDate().getTime().getTime() > to.getTime()) {
//            sensorValueDtoList.remove(sensorValueDtoList.size() - 1);
//            sensorValueDtoList.get(sensorValueDtoList.size() - 1).setDate(date);
        } else {
//            sensorValueDtoList.get(sensorValueDtoList.size() - 1).setDate(date);
        }

//        sensorValueDtoList.forEach(sensorValueDto -> System.out.println(sensorValueDto.getValues()));
        return new DataChartDto(sensorValueDtoList);
    }


    public DataChartDto getDataPdf(List<SensorValue> sensorValueList, Date from, Date to) {
        List<SensorValueDto> sensorValueDtoList = new ArrayList<>();
        sensorValueList.stream()
                .collect(Collectors.groupingBy(SensorValue::getDate))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(dateListEntry -> {
                    Calendar date = Calendar.getInstance();
                    if (sensorValueDtoList.size() == 0) {
                        date.setTime(from);
                        if (from.getTime() < dateListEntry.getKey().getTime()) {
                            sensorValueDtoList.add(getSensorValueDtoZero(dateListEntry, date));
                            Calendar nextDate = Calendar.getInstance();
                            nextDate.setTime(dateListEntry.getKey());
                            sensorValueDtoList.add(getSensorValueDto(dateListEntry, nextDate));
                        } else {
                            sensorValueDtoList.add(getSensorValueDto(dateListEntry, date));
                        }
                    } else {
                        date.setTime(dateListEntry.getKey());
                        sensorValueDtoList.add(getSensorValueDto(dateListEntry, date));
                    }
                });
        Calendar date = Calendar.getInstance();
        date.setTime(to);
        sensorValueDtoList.add(new SensorValueDto(date, sensorValueDtoList.get(sensorValueDtoList.size() - 1).getValues()));
        sensorValueDtoList.forEach(sensorValueDto -> System.out.println(sensorValueDto.getValues()));
        return new DataChartDto(sensorValueDtoList);
    }


    private SensorValueDto getSensorValueDto(Map.Entry<Date, List<SensorValue>> dateListEntry, Calendar date) {
        SensorValueDto sensorValueDto = new SensorValueDto(date);
        dateListEntry.getValue().
                forEach(sensorValue -> sensorValueDto.setOneValue(sensorValue.getValue()));
        return sensorValueDto;
    }

    private SensorValueDto getSensorValueDtoZero(Map.Entry<Date, List<SensorValue>> dateListEntry, Calendar date) {
        SensorValueDto sensorValueDto = new SensorValueDto(date);
        dateListEntry.getValue().
                forEach(sensorValue -> sensorValueDto.setOneValue(0));
        return sensorValueDto;
    }
}




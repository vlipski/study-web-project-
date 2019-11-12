package by.pvt.validator;

import by.pvt.pojo.cmd.DeviceCmd;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AddDeviceValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return AddDeviceValidator.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DeviceCmd deviceCmd = (DeviceCmd) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "name",
                "addDevice.name.empty",
                "Name must not be empty");
        if (deviceCmd.getName().length() < 5) {
            errors.rejectValue(
                    "name",
                    "addDevice.name.minSize",
                    "Name must be more than 5 characters");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "serialNumber",
                "addDevice.serialNumber.empty",
                "Serial number must not be empty");
        if (deviceCmd.getSerialNumber().length() < 5) {
            errors.rejectValue(
                    "serialNumber",
                    "addDevice.serialNumber.minSize",
                    "Serial number must be more than 5 characters");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "pinCod",
                "addDevice.pinCod.empty",
                "Pin cod number must not be empty");
        if (deviceCmd.getPinCod().length() < 4) {
            errors.rejectValue(
                    "pinCod",
                    "addDevice.pinCod.minSize",
                    "Pin cod number must be more than 4 characters");
        }

    }
}

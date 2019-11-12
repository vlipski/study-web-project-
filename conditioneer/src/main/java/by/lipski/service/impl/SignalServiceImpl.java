package by.lipski.service.impl;


import by.lipski.entity.ReceiverCmd;
import by.lipski.entity.Signal;
import by.lipski.gui.ConditionerGui;
import by.lipski.gui.buttons.ButtonOnOff;
import by.lipski.gui.display.DisplayPower;
import by.lipski.gui.display.DisplayTemperature;
import by.lipski.service.SignalService;


import java.util.Date;

public class SignalServiceImpl implements SignalService {

    private static volatile SignalService signalService;
    private Signal signal;
    private ConditionerGui conditionerGui;


    private SignalServiceImpl(ConditionerGui conditionerGui) {
        signal = new Signal();
        this.conditionerGui = conditionerGui;
    }

    public static synchronized SignalService getInstance(ConditionerGui conditionerGui) {
        if (signalService == null) {
            signalService = new SignalServiceImpl(conditionerGui);

        }
        return signalService;
    }

    public Signal getSignal() {
//        signal.setId(1L);
        signal.setOnOff(ButtonOnOff.getOnOff());
        signal.setTemp(DisplayTemperature.getTemperature());
        signal.setPower(DisplayPower.getPower());
//        signal.setDate(new Date());
        return signal;
    }

    public void updateDisplays(ReceiverCmd signal) {
        conditionerGui.getButtonOnOff().setOnOffSynchronize(true);
        conditionerGui
                .getDisplayPower()
                .setPowerSynchronize(signal
                        .getPower());
        conditionerGui.getDisplayTemperature().setTemperatureSynchronize(signal.getTemp());
    }
}

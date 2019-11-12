package by.lipski.service;

import by.lipski.entity.ReceiverCmd;
import by.lipski.entity.Signal;

public interface SignalService {

    Signal getSignal();

    void updateDisplays(ReceiverCmd receiverCmd);
}

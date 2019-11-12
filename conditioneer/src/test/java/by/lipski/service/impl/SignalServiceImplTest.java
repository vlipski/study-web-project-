package by.lipski.service.impl;

import by.lipski.entity.ReceiverCmd;
import by.lipski.entity.Signal;
import by.lipski.gui.ConditionerGui;
import by.lipski.service.SignalService;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

import static org.junit.Assert.*;

public class SignalServiceImplTest {
    private SignalService signalService;



    private  void startWindow() {
        Thread thread = new Thread(() -> {
            Display display = new Display();
            Shell shell = new Shell(display);
            ConditionerGui conditionerGui = new ConditionerGui(display, shell);
            conditionerGui.createGUI();
            signalService = SignalServiceImpl.getInstance(conditionerGui);
            shell.pack();
            shell.open();
            while (!shell.isDisposed()) {
                if (!display.readAndDispatch())
                    display.sleep();
            }
        });
        thread.start();
    }


    @Test
    public void testUpdateAndGetSignal() {
        startWindow();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ReceiverCmd signal = new ReceiverCmd();
//        signal.setOnOff(true);
        signal.setTemp(16);
        signal.setPower(5);
        signalService.updateDisplays(signal);
        Signal newSignal = signalService.getSignal();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(signal,newSignal);
    }
}
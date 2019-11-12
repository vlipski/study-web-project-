package by.lipski.socket;

import by.lipski.entity.Signal;
import by.lipski.gui.ConditionerGui;
import by.lipski.service.SignalService;
import by.lipski.service.impl.SignalServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketInTest {

    private SignalService signalService;

// запускаем окно кондиционера в отдельном потоке
    private void startWindow() {
        Thread thread = new Thread(() -> {
            Display display = new Display();
            Shell shell = new Shell(display);
            ConditionerGui conditionerGui = new ConditionerGui(display, shell);
            conditionerGui.createGUI();
            signalService = SignalServiceImpl.getInstance(conditionerGui);
// запускаем поток чтения входящего сигнала
            Thread threadIn = new SocketIn(conditionerGui);
            threadIn.start();
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
    public void testSocket() {
        startWindow();
        try {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
// запускаем сокет и передаем сигналы на сокет чтения входящего сигнала
            Socket socket = new Socket("localhost",3030);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Signal signal = new Signal();
            signal.setOnOff(true);
            signal.setTemp(16);
            signal.setPower(5);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(signal);
            System.out.println(json);
            dataOutputStream.writeUTF(json);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Signal newSignal = signalService.getSignal();
            assertEquals(signal, newSignal);

            signal.setOnOff(false);
            signal.setTemp(20);
            signal.setPower(8);
            json = objectMapper.writeValueAsString(signal);
            dataOutputStream.writeUTF(json);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            newSignal = signalService.getSignal();
            assertEquals(signal, newSignal);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

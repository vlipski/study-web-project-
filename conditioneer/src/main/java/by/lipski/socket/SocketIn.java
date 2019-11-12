package by.lipski.socket;

import by.lipski.entity.ReceiverCmd;
import by.lipski.entity.Signal;
import by.lipski.gui.ConditionerGui;
import by.lipski.service.SignalService;
import by.lipski.service.impl.SignalServiceImpl;
import by.lipski.coder.ConvertToJson;
import org.apache.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketIn extends Thread {

    private static final Logger log = Logger.getLogger(SocketIn.class);
    private static SignalService signalServiceImpl;

    public SocketIn(ConditionerGui conditionerGui) {
        signalServiceImpl = SignalServiceImpl.getInstance(conditionerGui);
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(3030)) {
            ExecutorService service = Executors.newFixedThreadPool(4);
            while (true) {
                Socket socket = serverSocket.accept();
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

                service.submit(() -> {
                    try {
                        String signal = dataInputStream.readUTF();
//                        System.out.println(signal);
                        if (!"".equals(signal)) {
//                            dataOutputStream.writeUTF(signal);
//                            dataOutputStream.flush();
//                            dataOutputStream.close();
                            dataInputStream.close();
//                            System.out.println(signal);
                            ReceiverCmd receiverCmd = ConvertToJson.deserialization(signal);
//                            System.out.println(receiverCmd);
                            signalServiceImpl.updateDisplays(receiverCmd);
                        }else {
                            Signal signal1 = signalServiceImpl.getSignal();
                            String json = ConvertToJson.fromReceiverToString(new ReceiverCmd(
                                    "conditioner0001",
                                    signal1.getPower(),
                                    signal1.getTemp()
                            ));
                            dataOutputStream.writeUTF(json);
                            dataInputStream.close();
                            dataOutputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e);
        }
    }

}

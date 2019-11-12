package by.lipski.socket;

import by.lipski.coder.Coder;
import by.lipski.entity.Message;
import by.lipski.entity.Signal;
import by.lipski.gui.ConditionerGui;
import by.lipski.service.SignalService;
import by.lipski.service.impl.SignalServiceImpl;
import by.lipski.coder.ConvertToJson;
import org.apache.log4j.Logger;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Socket;


public class SocketOut extends Thread {

    private static final Logger log = Logger.getLogger(SocketOut.class);
    private static SignalService signalServiceImpl;


    public SocketOut(ConditionerGui conditionerGui) {
        signalServiceImpl = SignalServiceImpl.getInstance(conditionerGui);
    }

    @Override
    public void run() {
        while (true) {

            HttpURLConnection connection = HttpConnection.getConnection();

            try {
                OutputStream os = connection.getOutputStream();
                Signal signal = signalServiceImpl.getSignal();
                Message message = Coder.encode(signal);
                String json = ConvertToJson.serialization(message);
                os.write(json.getBytes());
                os.flush();
                connection.getResponseCode();
                connection.disconnect();

                Thread.sleep(1000);
            } catch (Exception e) {
                log.info("not connection");
                log.error(e);
            }
        }


    }








   /* @Override
    public void run() {

        while (true) {
            try {
//                OutputStream os = connection.getOutputStream();
//            jaxbContext.createMarshaller().marshal(customer, os);
//            os.flush();
//
//            connection.getResponseCode();
//            connection.disconnect();

                HttpURLConnection connection = SocketManager.getConnection();
                OutputStream os = connection.getOutputStream();

//                Socket socket = SocketManager.getSocket();
//                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

                while (true) {
                    try {

                        String input = "{ \"body\": \"string\", \"date\": \"2019-10-30T08:52:59.112Z\", \"id\": 0, \"idThing\": \"string\", \"isRead\": true}";

                        os.write(input.getBytes());
                        os.flush();
                        System.out.println(input);



//                        sendMessage(dataOutputStream);
                        Thread.sleep(50000);
                    } catch (InterruptedException | IOException e) {
//                            e.printStackTrace();
                        log.error(e);
                        *//* если соединение прервано, присваивает сокету null (setSocket() устанавливает null),
                         и опять пытается подключится*//*
                        SocketManager.setSocket();
                        break;
                    }
                }
            } catch (IOException e) {
//                e.printStackTrace();
                log.error(e);
            }
        }
    }*/

    private void sendMessage(DataOutputStream dataOutputStream) throws IOException {
        Signal signal = signalServiceImpl.getSignal();
        Message message = Coder.encode(signal);
        String json = ConvertToJson.serialization(message);
        if (json != null) {
            System.out.println(json);
            dataOutputStream.writeUTF(json);
        }
    }

}

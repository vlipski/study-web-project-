package by.pvt.io;


import by.pvt.pojo.cmd.ChangeCmd;
import by.pvt.pojo.cmd.SendCmd;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

@Controller
public class IOService {

    private static final Logger log = Logger.getLogger(IOService.class);

    @Autowired
    ObjectMapper mapper;



    public void send(String location, String ipAddress, ChangeCmd changeCmd, String serialNumber) {

//        Device device = deviceService.getDeviceById(changeCmd.getIdDevice());

        try (Socket socket = new Socket(location, Integer.parseInt(ipAddress))) {
            String json = mapper.writeValueAsString(new SendCmd(
                    serialNumber,
                    changeCmd.getPower(),
                    changeCmd.getTemp())
            );

            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
//            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            outputStream.writeUTF(json);
            outputStream.flush();
//            String jsonRead = inputStream.readUTF();
//            inputStream.close();
            outputStream.close();
//            return mapper.readValue(jsonRead, SendCmd.class);
        } catch (IOException e) {
            log.info("error connection");
            log.error(e);
//            return null;
        }
    }

    public SendCmd send(String location, String ipAddress) {

//        Device device = deviceService.getDeviceById(id);

        try (Socket socket = new Socket(location, Integer.parseInt(ipAddress))) {
            String json = "";

            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            outputStream.writeUTF(json);
            outputStream.flush();
            String jsonRead = inputStream.readUTF();
            inputStream.close();
            outputStream.close();
            return mapper.readValue(jsonRead, SendCmd.class);
        } catch (IOException e) {
            log.info("error connection");
            log.error(e);
            return null;
        }
    }




}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejemplos;

import com.fazecast.jSerialComm.SerialPort;
import java.io.InputStream;

/**
 *
 * @author Tas
 */
public class Ejemplo5 {

    public static void main(String[] args) {
        SerialPort comPort = SerialPort.getCommPorts()[0];
        comPort.openPort();
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
        InputStream in = comPort.getInputStream();
        try {
            for (int j = 0; j < 1000; ++j) {
                byte[] lista = new byte[]{0x1b, 0x5b, 0x41};
                comPort.writeBytes(lista, lista.length);
                System.out.print((char) in.read());
                Thread.sleep(2000);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        comPort.closePort();
    }
}

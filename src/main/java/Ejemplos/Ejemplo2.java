/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejemplos;

import com.fazecast.jSerialComm.SerialPort;

/**
 *
 * @author Tas
 */
public class Ejemplo2 {

    public static void main(String[] args) {
        SerialPort comPort = SerialPort.getCommPorts()[1];
        comPort.openPort();
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
        try {
            while (true) {
                
                byte[] lista = new byte[]{0x1b, 0x5b, 0x41};
                comPort.writeBytes(lista, lista.length);
                Thread.sleep(2000);
                System.out.println(" antes " + comPort.bytesAvailable());
                byte[] readBuffer = new byte[1024];
                int numRead = comPort.readBytes(readBuffer, readBuffer.length);
                System.out.println("Read " + numRead + " bytes.");
                System.out.println(" despues " + comPort.bytesAvailable());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        comPort.closePort();
    }
}

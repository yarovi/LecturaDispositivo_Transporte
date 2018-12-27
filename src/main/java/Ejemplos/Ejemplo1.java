/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejemplos;

import com.fazecast.jSerialComm.SerialPort;

/**
 * Non -Blocking Reading /Polling
 */
public class Ejemplo1 {

    public static void main(String[] args) {
        SerialPort comPort = SerialPort.getCommPorts()[1];
        comPort.openPort();
        try {
            while (true) {

                System.out.println("pensando ...");
                byte[] lista = new byte[]{0x1b, 0x5b, 0x41};
                comPort.writeBytes(lista, lista.length);
                System.out.println(" .. " + comPort.bytesAvailable());

                while (comPort.bytesAvailable() == 0) {
                    Thread.sleep(20);
                }

                byte[] readBuffer = new byte[comPort.bytesAvailable()];
                int numRead = comPort.readBytes(readBuffer, readBuffer.length);
                System.out.println("Read " + numRead + " bytes.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        comPort.closePort();

    }

}

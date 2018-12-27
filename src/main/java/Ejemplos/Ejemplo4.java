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
public class Ejemplo4 {

    public static void main(String[] args) {

        SerialPort comPort = SerialPort.getCommPorts()[1];
        comPort.openPort();
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 1000, 0);
        System.out.println(" puerto :"+ comPort.getDescriptivePortName());
        try {
            while (true) {
                byte[] readBuffer = new byte[1024];
                int numRead = comPort.readBytes(readBuffer, readBuffer.length);
                System.out.println("Read " + numRead + " bytes.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        comPort.closePort();

    }
}

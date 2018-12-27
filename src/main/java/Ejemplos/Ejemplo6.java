/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejemplos;

import com.fazecast.jSerialComm.SerialPort;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Tas
 */
public class Ejemplo6 {

    public static void main(String[] args) {
        SerialPort comPort = SerialPort.getCommPorts()[1];
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING, 0, 0);
        //comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 1000, 0);
        comPort.openPort();
        try {
            InputStream in = comPort.getInputStream();
            int contador = 0;
            while (true) {

                Thread.sleep(200);
                System.out.println("Buscando .....");
                byte[] lista = new byte[]{0x1b, 0x5b, 0x41};
                comPort.writeBytes(lista, lista.length);

                byte[] readBuffer = new byte[1024]; //comPort.bytesAvailable()];

                int v1 = in.read();
                int v2 = in.read();
                int v3 = in.read();
                int v4 = in.read();
                int v5 = in.read();
                int v6 = in.read();
                int v7 = in.read();

                System.out.println("DAto 0 ." + v1);
                System.out.println("DAto 1 ." + v2);
                System.out.println("DAto 3 ." + v3);
                System.out.println("DAto 4 ." + v4);
                System.out.println("DAto 5 ." + v5);
                System.out.println("DAto 6 ." + v6);
                System.out.println("DAto 7 ." + v7);
                char c1 = (char) (v1);
                char c2 = (char) (v2);
                char c3 = (char) (v3);
                char c4 = (char) (v4);
                char c5 = (char) (v5);
                char c6 = (char) (v6);
                char c7 = (char) (v7);
                System.out.println("Convertido :  " + c1 + c2 + c3 + c4 + c5 + c6 + c7);

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                 System.out.println("Antes de leer Disponible bytesAvailable :" + comPort.bytesAvailable() + " available " + comPort.getInputStream().available());
                int numRead = comPort.readBytes(readBuffer, readBuffer.length);
                System.out.println("Read " + numRead + " bytes.  " + dtf.format(now));
                System.out.println("Despues de leer Disponible bytesAvailable :" + comPort.bytesAvailable() + " available " + comPort.getInputStream().available());
                if (contador == 300) {
                    break;
                }
                contador++;

            }
            in.close();
        } catch (Exception e) {

            System.err.println("Fallida ---- :" + e.getMessage());
            e.printStackTrace();
        }

        comPort.closePort();
    }

}

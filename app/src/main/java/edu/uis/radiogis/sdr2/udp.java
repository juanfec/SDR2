package edu.uis.radiogis.sdr2;

/**
 * Created by Juanfe on 15/12/2015.
 */
import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//implements Runnable so it can be created as a new thread
public class udp implements Runnable {
    public static final String SERVERIP = "192.168.0.8";
    public static final int SERVERPORT = 9999;

    public void run() {
        try { //mandatory error catching on Inet functions

//get serverAddress
            InetAddress serverAddr = InetAddress.getByName(SERVERIP);

//Create the socket using the serverAddress
            Log.d("UDP", "***Creating server");
            DatagramSocket socket = new DatagramSocket(SERVERPORT, serverAddr);

//create a buffer to copy packet contents into
            byte[] buf = new byte[1472];

//create a packet to receive
            DatagramPacket packet = new DatagramPacket(buf, buf.length);

            Log.d("UDP", "***Waiting on packet!");

//wait to receive the packet
            socket.receive(packet);
            Log.d("UDP", "***Packet received");
            Log.d("UDP", "***" + new String(packet.getData()));

        }
        catch (Exception e) {
            Log.e("UDP", "***UDP Server creation Failed!");
        }
    }
}

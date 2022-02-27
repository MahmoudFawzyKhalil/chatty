package gov.iti.jets.network;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Program {
    public final static String SERVER = JOptionPane.showInputDialog("Please enter server    ip");

    public static void main(String[] args) throws Exception {
        AudioFormat af = new AudioFormat(8000.0f, 8, 1, true, false);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, af);
        TargetDataLine microphone = (TargetDataLine) AudioSystem.getLine(info);
        microphone.open(af);
        Socket conn = new Socket(SERVER, 3000);
        microphone.start();
        DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
        int bytesRead = 0;
        byte[] soundData = new byte[1];
        Thread inThread = new Thread(new SoundReceiver(conn));
        inThread.start();
        while (bytesRead != -1) {
            bytesRead = microphone.read(soundData, 0, soundData.length);
            if (bytesRead >= 0) {
                dos.write(soundData, 0, bytesRead);
            }
        }
        System.out.println("IT IS DONE.");
    }

    static class SoundReceiver implements Runnable {
        Socket connection = null;
        DataInputStream soundIn = null;
        SourceDataLine inSpeaker = null;

        public SoundReceiver(Socket conn) throws Exception {
            connection = conn;
            soundIn = new DataInputStream(connection.getInputStream());
            AudioFormat af = new AudioFormat(8000.0f, 8, 1, true, false);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, af);
            inSpeaker = (SourceDataLine) AudioSystem.getLine(info);
            inSpeaker.open(af);
        }

        public void run() {
            int bytesRead = 0;
            byte[] inSound = new byte[1];
            inSpeaker.start();
            while (bytesRead != -1) {
                try {
                    bytesRead = soundIn.read(inSound, 0, inSound.length);
                } catch (Exception e) {
                }
                if (bytesRead >= 0) {
                    inSpeaker.write(inSound, 0, bytesRead);
                }
            }
        }
    }
}

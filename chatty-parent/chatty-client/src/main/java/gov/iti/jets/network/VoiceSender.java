package gov.iti.jets.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import java.io.IOException;
import java.net.*;

public class VoiceSender implements Runnable {
    private final static VoiceSender INSTANCE = new VoiceSender();
    private volatile boolean isInCall = true;
    private TargetDataLine audioIn;
    private DatagramSocket datagramSocket;
    private byte[] buffer = new byte[512];
    private final int port = 7775;
    private InetAddress receiverAddress;
    private Logger logger = LoggerFactory.getLogger(VoiceSender.class);

    private VoiceSender() {
    }


    public static VoiceSender getInstance() {
        return INSTANCE;
    }

    public void setConnection(String receiverIp) {
        try {
            this.isInCall = true;
            this.receiverAddress = InetAddress.getByName(receiverIp);
            setDataGramSocket();
            setAudioIn();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    private void setDataGramSocket() {
        try {
            datagramSocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    private void setAudioIn() {
        try {
            AudioFormat format = buildAudioFormatInstance();
            audioIn = AudioSystem.getTargetDataLine(format);
            audioIn.open(format);
            audioIn.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private AudioFormat buildAudioFormatInstance() {
        float sampleRate = 8000.0f;
        int sampleSizeInBits = 16;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }

    @Override
    public void run() {

        while (isInCall) {
            audioIn.read(buffer, 0, buffer.length);

            DatagramPacket data = new DatagramPacket(buffer, buffer.length, receiverAddress, port);
            try {
                datagramSocket.setSoTimeout(1000);
                datagramSocket.send(data);
            } catch (SocketTimeoutException e) {
                logger.info("Couldn't send...");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        datagramSocket.close();
        audioIn.close();
        audioIn.drain();
    }

    public void closeCall() {
        isInCall = false;
    }

    public boolean isInCall() {
        return isInCall;
    }
}

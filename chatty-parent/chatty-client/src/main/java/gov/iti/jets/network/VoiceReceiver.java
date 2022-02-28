package gov.iti.jets.network;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class VoiceReceiver implements Runnable {
    private final static VoiceReceiver INSTANCE = new VoiceReceiver();
    private volatile boolean isInCall = true;
    private final int port = 7775;
    private DatagramSocket datagramSocket;
    private SourceDataLine audioOut;
    byte[] buffer = new byte[512];

    private VoiceReceiver() {
    }

    public static VoiceReceiver getInstance() {
        return INSTANCE;
    }

    public void setConnection() {
        isInCall = true;
        initAudio();
    }

    private void initAudio() {
        AudioFormat format = buildAudioFormatInstance();
        try {
            DataLine.Info infoOut = new DataLine.Info(SourceDataLine.class, format);
            this.audioOut = (SourceDataLine) AudioSystem.getLine(infoOut);
            this.audioOut.open(format);
            this.audioOut.start();
            this.datagramSocket = new DatagramSocket(port);
        } catch (SocketException | LineUnavailableException e) {
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
        DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
        while (isInCall) {
            try {
                datagramSocket.receive(incoming);
                buffer = incoming.getData();
                audioOut.write(buffer, 0, buffer.length);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (datagramSocket != null)
            datagramSocket.close();
        audioOut.close();
        audioOut.drain();
    }

    public void closeCall() {
        isInCall = false;
    }

    public boolean isInCall() {
        return isInCall;
    }
}

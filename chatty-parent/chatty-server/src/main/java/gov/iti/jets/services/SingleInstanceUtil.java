package gov.iti.jets.services;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

public class SingleInstanceUtil {
    private String appName;
    private File file;
    private FileChannel channel;
    private FileLock lock;
    private static SingleInstanceUtil singleInstanceUtil = new SingleInstanceUtil( "chattyServer" );
    
    private SingleInstanceUtil( String appName ) {
        this.appName = appName;
    }

    public static SingleInstanceUtil getInstance() {
        return singleInstanceUtil;
    }

    public boolean isAppActive() {
        try {
            file = new File
                    ( System.getProperty( "user.home" ), appName + ".tmp" );
            channel = new RandomAccessFile( file, "rw" ).getChannel();

            try {
                lock = channel.tryLock();
            } catch (OverlappingFileLockException e) {
                return true;
            }

            if (lock == null) {
                return true;
            }

            Runtime.getRuntime().addShutdownHook( new Thread() {
                // destroy the lock when the JVM is closing
                @Override
                public void run() {
                    closeLock();
                    deleteFile();
                }
            } );
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    private void closeLock() {
        try {
            lock.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteFile() {
        try {
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

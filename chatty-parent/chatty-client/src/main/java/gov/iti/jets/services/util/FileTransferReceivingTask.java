package gov.iti.jets.services.util;

import gov.iti.jets.presentation.models.FileModel;
import gov.iti.jets.presentation.models.FileTransferOperationAvailabilityModel;
import gov.iti.jets.presentation.util.ModelFactory;
import javafx.application.Platform;
import javafx.concurrent.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileTransferReceivingTask extends Task<Long> {

    private String currentDirectory = System.getProperty("user.dir");
    private FileModel fileModel;
    private Socket socket;
    private ServerSocket serverSocket;
    private BufferedInputStream in;
    private BufferedOutputStream out;
    private FileTransferOperationAvailabilityModel fileTransferOperationAvailabilityModel = ModelFactory.getInstance().getFileTransferOperationAvailabilityModel();
    public FileTransferReceivingTask(FileModel fieModel) {
        this.fileModel =fieModel;
    }
    private Logger logger = LoggerFactory.getLogger(FileTransferTask.class);

    @Override
    protected Long call() throws Exception {
        fileTransferOperationAvailabilityModel.setAvailable(false);
        File file = new File(currentDirectory, fileModel.getFileName());
        long bytesCount = 0;
        try {
            serverSocket = new ServerSocket(9999);
            socket = serverSocket.accept();
            in = new BufferedInputStream(socket.getInputStream());
            out = new BufferedOutputStream(new FileOutputStream(file,true));
            byte[] buffer = new byte[1024 * 1024];
            int lengthRead;;
            while ((lengthRead = in.read(buffer)) > 0) {
                bytesCount += lengthRead;
                out.write(buffer);
                out.flush();
                updateValue(bytesCount);
                Platform.runLater(()->{
                    fileModel.setNumberOfBytesSoFar(getValue());
                });
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            logger.info("User closed the application");
        }finally {
            close();
            fileTransferOperationAvailabilityModel.setAvailable(true);
        }
        return null;
    }
    public void close() throws IOException {
        System.out.println("hellllllllllooooooooooooooooooooooooooooooooooo");
        serverSocket.close();
        socket.close();
        in.close();
        out.close();
    }
}

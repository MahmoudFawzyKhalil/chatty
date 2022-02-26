package gov.iti.jets.services.util;

import gov.iti.jets.presentation.models.FileModel;
import gov.iti.jets.presentation.models.FileTransferOperationAvailabilityModel;
import gov.iti.jets.presentation.util.ModelFactory;
import javafx.application.Platform;
import javafx.concurrent.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

public class FileTransferTask extends Task<Long> {

    private FileModel fileModel;
    private String receiverIp;
    private Socket socket;
    private BufferedOutputStream out;
    private BufferedInputStream in;
    private FileTransferOperationAvailabilityModel fileTransferOperationAvailabilityModel = ModelFactory.getInstance().getFileTransferOperationAvailabilityModel();
    private Logger logger = LoggerFactory.getLogger(FileTransferTask.class);
    public FileTransferTask(){
    }

    public FileTransferTask(FileModel fileModel, String receiverIp) {
        this.fileModel = fileModel;
        this.receiverIp = receiverIp;
    }

    @Override
    protected Long call() throws Exception {
        fileTransferOperationAvailabilityModel.setAvailable(false);
        long bytesCount=0;
        try{
            socket = new Socket(receiverIp,9999);
            in = new BufferedInputStream(new FileInputStream(fileModel.getFile()));
            out = new BufferedOutputStream(socket.getOutputStream());

            byte[] buffer = new byte[1024] ;
            int lengthRead;
            while ((lengthRead = in.read(buffer)) > 0 ) {
                if(!fileModel.isCanceled()){
                    bytesCount+=lengthRead;
                    out.write(buffer);
                    out.flush();
                    updateValue(bytesCount);
                    Platform.runLater(()->{
                        fileModel.setNumberOfBytesSoFar(getValue());
                    });
                }
            }
        } catch (IOException e) {
            logger.info("User closed the application while transferring file.");
        }finally{
            close();
            fileTransferOperationAvailabilityModel.setAvailable(true);
        }
        return bytesCount;
    }

    public void close() throws IOException {
        System.out.println("inside thread");
        socket.close();
        in.close();
        out.close();
    }
}

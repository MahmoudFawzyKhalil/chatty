package gov.iti.jets.commons.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.File;
import java.io.Serializable;

public class FileTransferPermissionDto implements Serializable {

    @NotNull
    @Size(min=11,max=11)
    private String senderPhoneNumber;

    @NotNull
    @Size (min=11,max=11)
    private String receiverPhoneNumber;

    @NotNull
    private String fileName;

    @NotNull
    private long fileSize;

    @NotNull
    private File file;

    public FileTransferPermissionDto() {
    }

    public FileTransferPermissionDto(String senderPhoneNumber, String receiverPhoneNumber, String fileName, long fileSize, File file) {
        this.senderPhoneNumber = senderPhoneNumber;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.file = file;
    }

    public String getSenderPhoneNumber() {
        return senderPhoneNumber;
    }

    public void setSenderPhoneNumber(String senderPhoneNumber) {
        this.senderPhoneNumber = senderPhoneNumber;
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public void setReceiverPhoneNumber(String receiverPhoneNumber) {
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}

package gov.iti.jets.commons.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.File;
import java.io.Serializable;

public class FileTransferResponseDto implements Serializable {

    @NotNull
    private boolean isAccepted;
    private String receiverIp;

    @NotNull
    @Size(min=11,max=11)
    private String senderPhoneNumber;

    @NotNull
    @Size (min=11,max=11)
    private String receiverPhoneNumber;

    @NotNull
    private File file;

    public FileTransferResponseDto() {
    }

    public FileTransferResponseDto(boolean isAccepted, String receiverIp, String senderPhoneNumber, String receiverPhoneNumber, File file) {
        this.isAccepted = isAccepted;
        this.receiverIp = receiverIp;
        this.senderPhoneNumber = senderPhoneNumber;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.file = file;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public String getReceiverIp() {
        return receiverIp;
    }

    public void setReceiverIp(String receiverIp) {
        this.receiverIp = receiverIp;
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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}

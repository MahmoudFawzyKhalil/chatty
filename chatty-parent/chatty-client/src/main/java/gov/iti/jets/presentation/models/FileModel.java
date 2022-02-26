package gov.iti.jets.presentation.models;

import javafx.beans.Observable;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.*;
import javafx.util.Callback;

import java.io.File;

public class FileModel {

    private File file;
    private StringProperty fileName = new SimpleStringProperty();
    private DoubleProperty fileSize = new SimpleDoubleProperty();
    private StringProperty senderName = new SimpleStringProperty();
    private DoubleProperty numberOfBytesSoFar = new SimpleDoubleProperty();
    private BooleanProperty isCanceled = new SimpleBooleanProperty();
    private DoubleBinding fileTransferProgress = numberOfBytesSoFar.divide(fileSize);

    public FileModel() {
    }

    public FileModel(File file, StringProperty fileName, DoubleProperty fileSize, StringProperty senderName, DoubleProperty numberOfBytesSoFar, BooleanProperty isCanceled, DoubleBinding fileTransferProgress) {
        this.file = file;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.senderName = senderName;
        this.numberOfBytesSoFar = numberOfBytesSoFar;
        this.isCanceled = isCanceled;
        this.fileTransferProgress = fileTransferProgress;
    }

    public static Callback<FileModel, Observable[]> extractor() {
        return new Callback<FileModel, Observable[]>() {
            @Override
            public Observable[] call( FileModel param ) {
                return new Observable[]{param.fileName, param.fileSize, param.numberOfBytesSoFar};
            }
        };
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName.get();
    }

    public StringProperty fileNameProperty() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName.set(fileName);
    }

    public double getFileSize() {
        return fileSize.get();
    }

    public DoubleProperty fileSizeProperty() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize.set(fileSize);
    }

    public String getSenderName() {
        return senderName.get();
    }

    public StringProperty senderNameProperty() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName.set(senderName);
    }

    public double getNumberOfBytesSoFar() {
        return numberOfBytesSoFar.get();
    }

    public DoubleProperty numberOfBytesSoFarProperty() {
        return numberOfBytesSoFar;
    }

    public void setNumberOfBytesSoFar(double numberOfBytesSoFar) {
        this.numberOfBytesSoFar.set(numberOfBytesSoFar);
    }

    public boolean isCanceled() {
        return isCanceled.get();
    }

    public BooleanProperty isCanceledProperty() {
        return isCanceled;
    }

    public void setIsCanceled(boolean isCanceled) {
        this.isCanceled.set(isCanceled);
    }

    public Number getFileTransferProgress() {
        return fileTransferProgress.get();
    }

    public DoubleBinding fileTransferProgressProperty() {
        return fileTransferProgress;
    }
}

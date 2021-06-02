package view.beans;

import java.awt.image.BufferedImage;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import javax.imageio.ImageIO;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class Main {

    private int imageWidth;
    private int imageHeight;
    private String fileName;
    private String fileType;

    public Main() {
    }

    public void inputFileVCL(ValueChangeEvent valueChangeEvent) throws IOException {
        UploadedFile file = (UploadedFile) valueChangeEvent.getNewValue();
        BufferedImage img = ImageIO.read(file.getInputStream());
        String fileName = file.getFilename().toString();
        String fileContent = file.getContentType();
        
        setFileName(fileName);
        setFileType(fileContent);
        setImageHeight(img.getHeight());
        setImageWidth(img.getWidth());
        
        
    }
    
    
    private String showInfoMessage(int width, int height){
        String messageText = "Image width: " +width + " Image height: " +height;
        FacesMessage fm = new FacesMessage(messageText);
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
        return null;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }
}

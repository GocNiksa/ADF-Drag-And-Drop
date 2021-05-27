package view.beans;

import java.awt.image.BufferedImage;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import javax.imageio.ImageIO;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class Main {

    public Main() {
    }

    public void inputFileVCL(ValueChangeEvent valueChangeEvent) throws IOException {
        UploadedFile file = (UploadedFile) valueChangeEvent.getNewValue();
        String fileName = file.getFilename().toString();
        String fileContent = file.getContentType();
        
        
        if (fileContent.startsWith("image/")){
            BufferedImage img = ImageIO.read(file.getInputStream());
            showInfoMessage(getImageWidth(img), getImageHeight(img));
        }else{
            System.out.println("Uploaded file is not an image file...");
        }
    }
    
    
    private int getImageWidth(BufferedImage img) {
        return img.getWidth();
    }
    
    private int getImageHeight(BufferedImage img){
        return img.getHeight();
    }
    
    private String showInfoMessage(int width, int height){
        String messageText = "Image width: " +width + " Image height: " +height;
        FacesMessage fm = new FacesMessage(messageText);
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
        return null;
    }
}

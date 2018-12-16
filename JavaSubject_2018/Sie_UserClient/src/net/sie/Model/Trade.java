package net.sie.Model;

import java.awt.*;
import java.io.Serializable;

public class Trade implements Serializable {

    private String method;
    private Image image;

    public Trade(){
        super();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}

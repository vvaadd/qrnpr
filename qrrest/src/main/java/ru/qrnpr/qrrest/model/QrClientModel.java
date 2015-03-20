package ru.qrnpr.qrrest.model;

import java.io.Serializable;

/**
 * Created by Vadim on 20.03.2015.
 */
public class QrClientModel implements Serializable {
    private String xpos;
    private String ypos;
    private String size;
    private String message;
    private String color;

    public String getXpos() {
        return xpos;
    }

    public void setXpos(String xpos) {
        this.xpos = xpos;
    }

    public String getYpos() {
        return ypos;
    }

    public void setYpos(String ypos) {
        this.ypos = ypos;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

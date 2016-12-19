package sk.localhost.hm.homeControl.ui.controller;

import java.util.Date;

public class Mdata {

    private Date date;
    private float preassure;
    private int luminous;

    public Mdata(Date date, float preassure, int luminous) {
        super();
        this.date = date;
        this.preassure = preassure;
        this.luminous = luminous;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPreassure() {
        return preassure;
    }

    public void setPreassure(float preassure) {
        this.preassure = preassure;
    }

    public float getLuminous() {
        return luminous;
    }

    public void setLuminous(int luminous) {
        this.luminous = luminous;
    }

}

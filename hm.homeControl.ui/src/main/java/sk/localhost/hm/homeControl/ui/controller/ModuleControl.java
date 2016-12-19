package sk.localhost.hm.homeControl.ui.controller;

import hm.moduleconnectivity.ui.webservice.ModuleMessageWS;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import sk.localhost.hm.homeControl.business.ModuleControlWebService;

@ViewScoped
@Named
public class ModuleControl implements Serializable {

    private static final long serialVersionUID = 1608056454358417755L;
    private boolean switch1 = false;
    private boolean switch2 = false;
    private boolean switch3 = false;
    private boolean switch4 = false;

    @Inject
    private transient ModuleControlWebService service;

    @PostConstruct
    public void init() {

    }

    public String sendMessage(String msg) {
        ModuleMessageWS message = new ModuleMessageWS();
        message.setSentMessage(msg);
        message.setSentOn(getCallendar(new Date()));
        ModuleMessageWS response = service.getMessage(message);
        System.out.println(response.getResponsedMessage());
        return response.getResponsedMessage();
    }

    private XMLGregorianCalendar getCallendar(Date date) {

        GregorianCalendar gregorianCalendarFrom = new GregorianCalendar();
        gregorianCalendarFrom.setTime(date);
        XMLGregorianCalendar gregorToDate = null;
        try {
            gregorToDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendarFrom);

        } catch (DatatypeConfigurationException e1) {}
        return gregorToDate;

    }

    public void addMessage() {
        String summary = switch1 ? "Checked" : "Unchecked";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }

    public boolean isSwitch1() {
        return switch1;
    }

    public void setSwitch1(boolean switch1) {
        if (switch1) {
            sendMessage("a");
        } else {
            sendMessage("b");
        }
        this.switch1 = switch1;
    }

    public boolean isSwitch2() {
        return switch2;
    }

    public void setSwitch2(boolean switch2) {
        if (switch2) {
            sendMessage("c");
        } else {
            sendMessage("d");
        }
        this.switch2 = switch2;
    }

    public boolean isSwitch3() {
        return switch3;
    }

    public void setSwitch3(boolean switch3) {
        if (switch3) {
            sendMessage("e");
        } else {
            sendMessage("f");
        }
        this.switch3 = switch3;
    }

    public boolean isSwitch4() {
        return switch4;
    }

    public void setSwitch4(boolean switch4) {
        if (switch4) {
            sendMessage("g");
        } else {
            sendMessage("h");
        }
        this.switch4 = switch4;
    }

}

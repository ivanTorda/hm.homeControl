package sk.localhost.hm.homeControl.ui.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import sk.localhost.hm.homeControl.business.ModuleControlWebService;
import sk.localhost.hm.homeControl.persistence.entities.MeteoData;
import sk.localhost.hm.homeControl.persistence.services.MeteoDataDAO;

import com.google.gson.Gson;

@ViewScoped
@Named
public class LinechartController implements Serializable {

    private static final long serialVersionUID = 8765309501795021915L;

    private String dataStr;

    private List<MeteoData> meteoData = new ArrayList<MeteoData>();

    private MeteoData actualData = new MeteoData();

    private Date selectedDate;

    @Inject
    private transient MeteoDataDAO meteoDataDAO;

    @Inject
    private transient ModuleControlWebService service;

    @PostConstruct
    public void init() {
        selectedDate = new Date();
        refreshData();
    }

    public void refreshData() {

        if (selectedDate != null) {
            meteoData = meteoDataDAO.findByDate(getStartDay(selectedDate), getEndDay(selectedDate));
            actualData = meteoDataDAO.getActualData();
        }
        dataStr = new Gson().toJson(meteoData);
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("createChart(" + dataStr + ")");

    }

    private Date getStartDay(Date selectedDate) {
        Calendar date = new GregorianCalendar();
        date.setTime(selectedDate);
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        return date.getTime();

    }

    private Date getEndDay(Date selectedDate) {
        Calendar date = new GregorianCalendar();
        date.setTime(selectedDate);
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        date.add(Calendar.DAY_OF_MONTH, +1);
        return date.getTime();
    }

    public void measureData() {
        service.measureData();
        refreshData();
    }

    public String getDataStr() {
        return dataStr;
    }

    public void setDataStr(String dataStr) {
        this.dataStr = dataStr;
    }

    public MeteoData getActualData() {
        return actualData;
    }

    public void setActualData(MeteoData actualData) {
        this.actualData = actualData;
    }

    public Date getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }

}

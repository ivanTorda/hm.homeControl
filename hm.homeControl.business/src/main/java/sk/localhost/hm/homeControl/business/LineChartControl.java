package sk.localhost.hm.homeControl.business;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sk.localhost.hm.homeControl.persistence.entities.MeteoData;
import sk.localhost.hm.homeControl.persistence.services.MeteoDataDAO;

@Stateless
public class LineChartControl implements Serializable {

    private static final long serialVersionUID = 7280705078213749635L;

    @Inject
    private transient MeteoDataDAO meteoDataDAO;

    @Inject
    private transient ModuleControlWebService service;

    public List<MeteoData> getMeteoData(Date from, Date to) {
        return meteoDataDAO.findByDate(from, to);
    }

    public MeteoData getActualData() {
        return meteoDataDAO.getActualData();
    }

    public void measureData() {
        service.measureData();
    }
}

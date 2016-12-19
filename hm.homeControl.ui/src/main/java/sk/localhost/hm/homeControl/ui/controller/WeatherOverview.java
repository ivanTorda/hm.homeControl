package sk.localhost.hm.homeControl.ui.controller;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import sk.localhost.hm.homeControl.persistence.entities.MeteoData;
import sk.localhost.hm.homeControl.persistence.services.MeteoDataDAO;

@ViewScoped
@Named
public class WeatherOverview implements Serializable {

    private static final long serialVersionUID = 1L;

    private LineChartModel lineModel1 = new LineChartModel();

    private List<MeteoData> meteoData = new ArrayList<MeteoData>();

    private MeteoData actualData = new MeteoData();

    @Inject
    private transient MeteoDataDAO meteoDataDAO;

    @PostConstruct
    public void init() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String datefromInString = "05-11-2016 22:58:00";
        String datetoInString = "06-11-2016 22:58:00";

        Date dateFrom = null;
        Date dateTo = null;
        try {
            dateFrom = sdf.parse(datefromInString);
            dateTo = sdf.parse(datetoInString);

        } catch (ParseException e) {
            return;
        }

        if (dateFrom != null && dateTo != null) {
            meteoData = meteoDataDAO.findByDate(dateFrom, dateTo);
        }

        actualData = meteoDataDAO.getActualData();
        createLineModels();
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");

        for (MeteoData m : meteoData) {

            new SimpleDateFormat("hh:mm");

            // series1.set(df.format(m.getDate()), m.getPreassure().floatValue());

        }
        series1.set("10:30", 2);
        series1.set("10:31", 1);
        series1.set("10:32", 3);
        series1.set("10:33", 6);
        series1.set("10:34", 8);
        model.addSeries(series1);

        return model;
    }

    private void createLineModels() {
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);

        lineModel1 = initCategoryModel();
        lineModel1.setTitle("Category Chart");
        lineModel1.setLegendPosition("e");
        lineModel1.setShowPointLabels(true);
        lineModel1.setZoom(true);
        lineModel1.getAxes().put(AxisType.X, new CategoryAxis("Years"));
        yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setLabel("Births");
        yAxis.setMin(95000);
        yAxis.setMax(101000);
    }

    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();

        ChartSeries preassure = new ChartSeries();
        preassure.setLabel("Preassure");

        for (MeteoData m : meteoData) {

            DateFormat df = new SimpleDateFormat("hh:mm");
            preassure.set(df.format(m.getDate()), m.getPreassure().floatValue());

        }

        model.addSeries(preassure);

        return model;
    }

    public LineChartModel getLineModel1() {
        return lineModel1;
    }

    public void setLineModel1(LineChartModel lineModel1) {
        this.lineModel1 = lineModel1;
    }

    public MeteoData getActualData() {
        return actualData;
    }

    public void setActualData(MeteoData actualData) {
        this.actualData = actualData;
    }

}

package sk.localhost.hm.homeControl.persistence.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "meteo_data")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "MeteoData.findAll", query = "SELECT m FROM MeteoData m"),
    @NamedQuery(name = "MeteoData.findById", query = "SELECT m FROM MeteoData m WHERE m.id = :id"),
    @NamedQuery(name = "MeteoData.findByDate", query = "SELECT m FROM MeteoData m WHERE m.date = :date"),
    @NamedQuery(name = "MeteoData.findInRange",
    query = "SELECT m FROM MeteoData m WHERE m.date >:fromDate AND m.date <= :toDate"),
    @NamedQuery(name = "MeteoData.getActualData", query = "SELECT m FROM MeteoData m  order by m.date asc") })
public class MeteoData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "temperature1")
    private Float temperature1;

    @Column(name = "temperature2")
    private Float temperature2;

    @Column(name = "preassure")
    private Float preassure;

    @Column(name = "luminous")
    private Integer luminous;

    @Column(name = "humidity1")
    private Float humidity1;

    @Column(name = "humidity2")
    private Float humidity2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getTemperature1() {
        return temperature1;
    }

    public void setTemperature1(Float temperature1) {
        this.temperature1 = temperature1;
    }

    public Float getTemperature2() {
        return temperature2;
    }

    public void setTemperature2(Float temperature2) {
        this.temperature2 = temperature2;
    }

    public Float getPreassure() {
        return preassure;
    }

    public void setPreassure(Float preassure) {
        this.preassure = preassure;
    }

    public Integer getLuminous() {
        return luminous;
    }

    public void setLuminous(Integer luminous) {
        this.luminous = luminous;
    }

    public Float getHumidity1() {
        return humidity1;
    }

    public void setHumidity1(Float humidity1) {
        this.humidity1 = humidity1;
    }

    public Float getHumidity2() {
        return humidity2;
    }

    public void setHumidity2(Float humidity2) {
        this.humidity2 = humidity2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MeteoData)) {
            return false;
        }
        MeteoData other = (MeteoData) object;
        if ((id == null && other.id != null) || (id != null && !id.equals(other.id))) {
            return false;
        }
        return true;
    }

}

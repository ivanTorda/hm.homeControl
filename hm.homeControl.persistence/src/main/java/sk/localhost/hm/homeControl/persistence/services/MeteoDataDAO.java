package sk.localhost.hm.homeControl.persistence.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sk.localhost.hm.homeControl.persistence.entities.MeteoData;

@Stateless
public class MeteoDataDAO extends AbstractDAO<MeteoData> {

    @PersistenceContext(unitName = "hm.homeControl.persistence")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MeteoDataDAO() {
        super(MeteoData.class);
    }

    public void saveMeteoData(MeteoData meteoData) {
        System.currentTimeMillis();
        create(meteoData);
        System.currentTimeMillis();
    }

    public List<MeteoData> findByDate(Date from, Date to) {
        return em.createNamedQuery("MeteoData.findInRange").setParameter("fromDate", from).setParameter("toDate", to)
            .getResultList();
    }

    public MeteoData getActualData() {
        List<MeteoData> retr = em.createNamedQuery("MeteoData.getActualData").getResultList();

        if (retr.isEmpty()) {
            return null;
        } else {
            return retr.get(retr.size() - 1);
        }
    }

}

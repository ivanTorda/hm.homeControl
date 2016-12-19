package sk.localhost.hm.homeControl.business;

import hm.moduleconnectivity.ui.webservice.ModuleControlService;
import hm.moduleconnectivity.ui.webservice.ModuleControlService_Service;
import hm.moduleconnectivity.ui.webservice.ModuleMessageWS;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Stateless
public class ModuleControlWebService {

    private Logger logger = LogManager.getLogger(ModuleControlWebService.class);

    @Resource(lookup = "java:/HM/ModuleControlWebservice")
    private String soapUrlString;

    private URL newWsdlLocation;
    private ModuleControlService service;

    @PostConstruct
    public void init() {

        try {
            newWsdlLocation = new URL(soapUrlString);
        } catch (MalformedURLException e) {
            logger.error("Malformed URL: " + newWsdlLocation, e);
        }
    }

    private ModuleControlService getService() {
        if (service == null) {
            ModuleControlService_Service accessService = null;
            try {
                accessService = new ModuleControlService_Service(newWsdlLocation);
                service = accessService.getModuleControlServicePort();
            } catch (Exception e) {
                logger.error("Exception during get ModuleControlWebservice.", e);
            }
        }
        return service;
    }

    public void measureData() {
        getService().measureData();
    }

    public ModuleMessageWS getMessage(ModuleMessageWS message) {

        ModuleMessageWS output = new ModuleMessageWS();
        output = getService().getMessage(message);
        return output;
    }

}

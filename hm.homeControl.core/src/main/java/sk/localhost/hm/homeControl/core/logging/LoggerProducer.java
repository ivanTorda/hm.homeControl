package sk.localhost.hm.homeControl.core.logging;

import javax.enterprise.inject.Produces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sk.localhost.hm.homeControl.core.qualifiers.DataLoadingLogger;
import sk.localhost.hm.homeControl.core.qualifiers.MainLogger;

import java.io.Serializable;

/**
 * Producer which handles instantiation of used loggers.
 * 
 * @author machoj
 */
public class LoggerProducer implements Serializable {
	public LoggerProducer(){
		
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String MAIN_LOGGER = "MAIN_LOGGER";
	private static final String DATA_LOADING_LOGGER = "DATA_LOADING_LOGGER";

    /**
     * Creates main logger instance, which creates logs into MP_LOG file.
     * 
     * @return logger main logger instance
     */
    @Produces
    @MainLogger
    public Logger produceLogger() {
    	return LogManager.getLogger(MAIN_LOGGER);
    }
    /**
     * Creates data loading logger instance, which creates logs into DATA_LOADING_LOG file.
     * 
     * @return logger data loading logger instance
     */
    @Produces
    @DataLoadingLogger
    public Logger produceDataLoadingLogger() {
    	return LogManager.getLogger(DATA_LOADING_LOGGER);
    }
}

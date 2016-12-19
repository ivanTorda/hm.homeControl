package sk.localhost.hm.homeControl.ui.filter;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * A PF filter for filtering fields with Date objects
 * @author malyp
 *
 */

@Named("DateFilter")
@ViewScoped
public class DatatableDateFilter implements Serializable {

	private static final long serialVersionUID = -1228928679020763577L;

		/**
		 * A filter method for filtering
		 *  in the primefaces datatable component
		 * @author malyp
		 * @param value concrete value of the column
		 * @param filter value from the filter
		 * @param locale
		 * @return true if the value match the filter
		 */
		public boolean filterByDate(Object value, Object filter, Locale locale) {
	        if( filter == null ) {
	            return true;
	        }

	        if( value == null ) {
	            return false;
	        }

	        return equalsDates((Date) filter, (Date) value);
	    }

		
		/**
		 * Compare the the two dates without mm:ss:ss
		 * @author malyp
		 * @param first date to compare
		 * @param second date for compare
		 * @return true if the dates equals
		 */
		public boolean equalsDates(Date one,Date two){
		        Calendar cal1=GregorianCalendar.getInstance();
		        Calendar cal2=GregorianCalendar.getInstance();      
		        cal1.setTime(one);
		        cal2.setTime(two);
		        
		        cal1.set(cal1.get(Calendar.YEAR),cal1.get(Calendar.MONTH),cal1.get(Calendar.DAY_OF_MONTH),0,0,0);
		        cal2.set(cal2.get(Calendar.YEAR),cal2.get(Calendar.MONTH),cal2.get(Calendar.DAY_OF_MONTH),0,0,0);
		        cal1.set(Calendar.MILLISECOND,0);
		        cal2.set(Calendar.MILLISECOND,0);
		       
		        if(cal1.getTimeInMillis()==cal2.getTimeInMillis())
		            return true;
		        else
		            return false;
		    }

}

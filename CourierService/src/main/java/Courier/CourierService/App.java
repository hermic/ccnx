package Courier.CourierService;

import java.io.IOException;
import java.util.List;

import org.ccnx.ccn.apps.ccnchat.CCNChatText;
import org.ccnx.ccn.config.ConfigurationException;
import org.ccnx.ccn.protocol.MalformedContentNameStringException;

import Courier.CourierService.Repositories.RouteRepository;
import Courier.CourierService.CCN.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ServiceWorker worker;
		try {
			worker = new ServiceWorker();
			worker.start();
		} catch (MalformedContentNameStringException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	


    	
//    	RouteRepository repository = new RouteRepository();
//    	List<Route> routes = repository.Get();
//    	for(Route route: routes)
//    	{
//    		System.out.println("zv" + route.getFrom() + " do " + route.getTo());
//    	}
        
    }
}

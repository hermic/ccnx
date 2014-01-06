package Courier.CourierService.CCN;

import java.io.IOException;
import java.util.logging.Level;

import org.ccnx.android.ccnlib.CCNxConfiguration;
import org.ccnx.android.ccnlib.CCNxServiceCallback;
import org.ccnx.android.ccnlib.CCNxServiceStatus.SERVICE_STATUS;
import org.ccnx.ccn.apps.ccnchat.CCNChatNet;
import org.ccnx.ccn.config.ConfigurationException;
import org.ccnx.ccn.protocol.MalformedContentNameStringException;



public class ServiceWorker implements Runnable {

	protected final Thread _thd;
	protected final Manager _manager;	
	
	public ServiceWorker() throws MalformedContentNameStringException
	{
		_manager = new Manager("courier");
		_thd = new Thread(this, "ServiceWorker");
	}
	
	public void run() {
	}
	
	public void start() throws ConfigurationException, MalformedContentNameStringException, IOException {
		_thd.start();
		_manager.listen();
		//_chat.setLogging(Level.WARNING);
		//_chat.listen();
	}
}

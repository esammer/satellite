package net.lifeless.satellite;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

	private static final String		applicationContextPath = "classpath:/net/lifeless/satellite/application.xml";
	
	private Log						log;
	private CommandLine				parsedCommandLine;
	
	public static void main(String[] args) {
		ApplicationContext		context;
		Application				application;
		
		System.out.println("satellite starting up...");
		
		try {
			context		= new ClassPathXmlApplicationContext(applicationContextPath);
			application	= (Application) context.getBean("application");
			
			application.run(args);
		} catch (SatelliteException e) {
			System.err.println("Satellite Error: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Unknown Error: " + e.getMessage());
		}
	}
	
	public Application() {
		log			= LogFactory.getLog(Application.class);
	}
	
	public void run(String[] args) throws SatelliteException {
		CommandLineParser		parser;
		Options					options;
		Option					optionHelp;
		
		log.debug("running");
		
		parser			= new GnuParser();
		options			= new Options();
		optionHelp		= OptionBuilder.create("help");
		
		optionHelp.setDescription("display help text");
		
		options.addOption(optionHelp);
		
		try {
			parsedCommandLine	= parser.parse(options, args);
		} catch (ParseException e) {
			throw new SatelliteException("Unable to parse command line options: " + e.getMessage());
		}
	}
	
	public static class SatelliteException extends Exception {
		
		private static final long serialVersionUID = 1861634469327802605L;

		public SatelliteException(Throwable t) {
			super(t);
		}
		
		public SatelliteException(String message) {
			super(message);
		}
		
		public SatelliteException(String message, Throwable t) {
			super(message, t);
		}
	}
}
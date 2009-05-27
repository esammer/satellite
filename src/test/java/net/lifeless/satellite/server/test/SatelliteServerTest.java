package net.lifeless.satellite.server.test;

import javax.annotation.Resource;

import net.lifeless.satellite.server.Host;
import net.lifeless.satellite.server.SatelliteServer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:/net/lifeless/satellite/application.xml" } )
public class SatelliteServerTest {

	@Resource
	private SatelliteServer		server;
	
	@Test
	public void testRun() {
		Host					host;
		
		Assert.assertNotNull(server);
		
		host		= new Host();
		
		host.setId("1");
		host.setUrl("http://www.bloodfury.net");
		
		server.getHosts().add(host);
		
		host		= new Host();
		
		host.setId("2");
		host.setUrl("http://www.lifeless.net");
		
		server.getHosts().add(host);
		
		server.run();
	}
}

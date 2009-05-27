package net.lifeless.satellite.test;

import javax.annotation.Resource;

import net.lifeless.satellite.Application;
import net.lifeless.satellite.Application.SatelliteException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:/net/lifeless/satellite/application.xml" } )
public class ApplicationTest {

	@Resource
	private Application			application;
	
	@Test
	public void testApplication() throws SatelliteException {
		Assert.assertNotNull(application);
		
		application.run(new String[] { "--help" });
	}
}

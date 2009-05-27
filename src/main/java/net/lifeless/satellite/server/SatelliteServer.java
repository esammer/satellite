package net.lifeless.satellite.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SatelliteServer {

	private String			serverId;
	private Log				log;
	private List<Host>		hosts;
	private HttpClient		httpClient;
	
	public SatelliteServer() {
		log			= LogFactory.getLog(SatelliteServer.class);
		hosts		= new ArrayList<Host>();
	}

	public void run() {
		log.info("satellite server starting");
		
		for (Host host : hosts) {
			HttpMethod			method;
			byte[]				response;
			
			log.debug("checking host:" + host);
			
			method		= new GetMethod(host.getUrl());
			response	= null;
			
			try {
				httpClient.executeMethod(method);
				
				response = method.getResponseBody();
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				method.releaseConnection();
			}
			
			if (response != null) {
				log.debug("content size:" + response.length);
			} else {
				log.debug("no content returned");
			}
		}
	}
	
	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public List<Host> getHosts() {
		return hosts;
	}

	public void setHosts(List<Host> hosts) {
		this.hosts = hosts;
	}

	public HttpClient getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}
	
}

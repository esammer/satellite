package net.lifeless.satellite.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.Header;
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
			long				requestStartTime;
			long				requestEndTime;
			long				requestDuration;
			
			log.debug("checking host:" + host);
			
			method				= new GetMethod(host.getUrl());
			response			= null;
			requestStartTime	= 0;
			requestEndTime		= 0;
			requestDuration		= 0;
			
			try {
				requestStartTime		= System.currentTimeMillis();
				
				httpClient.executeMethod(method);
				
				response				= method.getResponseBody();
				requestEndTime			= System.currentTimeMillis();
				requestDuration			= requestEndTime - requestStartTime;
				
				Header[] responseHeaders = method.getResponseHeaders();
				
				for (Header header : responseHeaders) {
					log.debug("header:" + header.getName() + " value:" + header.getValue());
				}
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
				log.debug("content size:" + response.length + " requestDuration:" + requestDuration + "ms");
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

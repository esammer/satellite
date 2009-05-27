package net.lifeless.satellite.server;

public class Host {

	private String			id;
	private Integer			port;
	private String			url;
	
	@Override
	public String toString() {
		return new StringBuilder("Host id:")
			.append(id)
			.append(" url:").append(url)
			.append(" port:").append(port)
			.toString();
	}
	
	public boolean hasPort() {
		return port != null;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getPort() {
		return port;
	}
	
	public void setPort(Integer port) {
		this.port = port;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
}

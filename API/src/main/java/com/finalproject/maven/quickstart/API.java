package com.finalproject.maven.quickstart;

/**
 * Create a class called API that will store the information for each API.
 * An API should have the following instance variables:
 *  - host
 *  - rapidapiHost
 *  - rapidapiKey
 *  
 *  @author shiao xiaoxuan wu
 */

public class API {
	
	private String host;
	private String rapidapiHost;
	private String rapidapiKey;

	/**
	 * Create a constructor 
	 * @param host
	 * @param rapidapiHost
	 * @param rapidapiKey
	 */
	API(String host, String rapidapiHost, String rapidapiKey){
		this.host = host;
		this.rapidapiHost = rapidapiHost;
		this.rapidapiKey = rapidapiKey;
	}
	
	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @return the rapidapiHost
	 */
	public String getRapidapiHost() {
		return rapidapiHost;
	}

	/**
	 * @return the rapidapiKey
	 */
	public String getRapidapiKey() {
		return rapidapiKey;
	}
}

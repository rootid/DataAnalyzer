package com.ub.dataanalyzer.server.tpservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class ThirdPartyCaller {
	
	private String url = null;
	private HttpURLConnection connection = null;
	private URL serverAddress = null;
	private InputStream inputStream = null;
	private StringBuffer responseBuffer;
	private String response;
	
	
	public ThirdPartyCaller(String url) {
		this.url = url;
		this.responseBuffer = new StringBuffer();
	}
	
	/**
	 * 
	 * @return
	 */
	public String callService() {
		
		try {
			serverAddress = new URL(this.url);
			connection =  (HttpURLConnection) serverAddress.openConnection();

			//set up connection parameter
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.setReadTimeout(20*1000); //20 sec timeout
			try {
				connection.connect();
				inputStream = connection.getInputStream();
				/**
				 * ISO-8859-1 : this parameter is necessary as first it was UTF-8
				 * Reason : Invalid byte 2 of 4-byte UTF-8 sequence in JAXB exception
				 */
				//BufferedReader br =  new BufferedReader(new InputStreamReader(inputStream, "ISO-8859-1"));
				//BufferedReader br =  new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
				BufferedReader br =  new BufferedReader(new InputStreamReader(inputStream, "ISO-8859-1"));
				String lineString = "";
				while((lineString = br.readLine()) != null) {
					responseBuffer.append(lineString);
				}
			}
			catch (Exception e) {
				//Handle the http code exception 
				if(connection.getResponseCode() == 500) {
					responseBuffer.append("No Server response");
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
		}
		
		finally {
			connection.disconnect();
		}
		
		response = responseBuffer.toString();
		return response;
	}

	
	public void callAnotherService() {
		 URL oracle;
		 URLConnection yc = null;
		 BufferedReader in = null;
		 String inputLine;
		 StringBuffer responseBufferClone = new StringBuffer();
		 try {
			oracle = new URL("");
			 yc = oracle.openConnection();
			 in = new BufferedReader(
					 new InputStreamReader(
							 yc.getInputStream()));
			 while ((inputLine = in.readLine()) != null)
			 {
				 System.out.println("The input of read line"+ inputLine);
				responseBufferClone.append(inputLine);
			 }
			 	in.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	       
	       
	}
		
}

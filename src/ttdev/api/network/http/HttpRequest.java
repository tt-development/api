package ttdev.api.network.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class HttpRequest {

	private String website;
	private String parameters;
	
	public HttpRequest(String website) {
		this.website = website;
	}
	
	public HttpRequest(String website, String parameters) {
		this.website = website;
		this.parameters = parameters;
	}
	
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public String getParameters() {
		return this.parameters;
	}
	
	public String getWebsite() {
		return this.website;
	}
	
	public String sendGETRequest() {
        try {
            URL url = new URL(this.website + "?" + this.parameters);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inLine;
            String lineSum = new String();
            while ((inLine = in.readLine()) != null) {
                lineSum += inLine;
            }
            in.close();

            return lineSum;
        } catch (IOException e) {
        	return null;
        }
	}
	
}

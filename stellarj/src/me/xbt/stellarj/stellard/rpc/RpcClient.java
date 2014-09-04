package me.xbt.stellarj.stellard.rpc;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * access stellard using rpc.  
 * 
 * @author kaye wu
 *
 */
public class RpcClient {
	// 
	private final String url;
	
	public RpcClient(String url) {
		this.url = url;
	}
	
	/**
	 * convenience method for single object parameter.  
	 * single object parameters is common enough that it deserves its own method. 
	 * it will simply construct a jsonarray and put param in it and call sendCommand().  
	 * @param method
	 * @param param
	 * @return
	 * @throws IOException
	 */
	public String sendCommand(String method, JSONObject param) throws IOException {
		JSONArray params = new JSONArray();
		params.put(param);
		return sendCommand(method, params);
	}
	
	public String sendCommand(String method, JSONArray params) throws IOException {
		Command command = new Command();
		command.setMethod(method);
		command.setParams(params);
		return sendCommand(command);
	}
	
	public String sendCommand(Command command) throws IOException {
		return post(command.toJson());
	}
	
	private String post(JSONObject data) throws IOException {
		System.out.println("calling " + url + ".  with data=" + data.toString(2));
		return post(url, data.toString());
	}
	
	/**
	 * 
	 * @param url
	 * @param parameters - pass in "" if no parameters are needed.
	 * eg. 
{
  "method": "account_currencies",
  "params": [
    {
      "account": "gM4Fpv2QuHY4knJsQyYGKEHFGw3eMBwc1U",
      "ledger_index": 400
    }
  ]
}
	 * @throws Exception
	 */
	private String post(String url, String parameters) throws IOException {
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
 
		//add reuqest header
		con.setRequestMethod("POST");
		//con.addRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // don't think it is needed.
		con.setRequestProperty("Content-Type", "application/json");
 
		// don't do this.  this will screw up json result.
		//String urlParameters = URLEncoder.encode(parameters, "UTF-8");
		
		//System.out.println("calling " + url + ".  with parameters=" + parameters);
 
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(parameters);
		wr.flush();
		wr.close();
 
		int responseCode = con.getResponseCode();
		System.out.println("http response code="+responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//System.out.println(response.toString());
		
		String sResponse = response.toString();
		System.out.println("response="+sResponse);
		return sResponse;
	}
	
	public static class Command {
		private String method = null;
		private JSONArray params = null;
		
		public JSONObject toJson() {
			JSONObject json = new JSONObject();
			json.put("method", method);
			json.put("params", params);
			return json;
		}
		
		public String getMethod() {
			return method;
		}
		public void setMethod(String method) {
			this.method = method;
		}
		public JSONArray getParams() {
			return params;
		}
		public void setParams(JSONArray params) {
			this.params = params;
		}
	}
	
	
}

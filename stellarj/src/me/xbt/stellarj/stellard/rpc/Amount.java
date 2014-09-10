package me.xbt.stellarj.stellard.rpc;

import org.json.JSONObject;

import flexjson.JSONSerializer;

/**
 * https://www.stellar.org/api/#api-amount explains the structure.  
 * 
  eg. 
  {
    "value": "1",
    "currency": "EUR",
    "issuer": "rvYAfWj5gh67oV6fW32ZzP3Aw4Eubs59B"
  }
 * @author t
 *
 * @see {@link https://www.stellar.org/api/#api-amount}
 */
public class Amount {

	private String value = null;
	private String currency = null;
	private String issuer = null;
	
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	
	/**
	 * 
	 * @return jsonobject that represents this object.  
	 */
	public JSONObject toJSONObject() {
		JSONObject json = new JSONObject();
		if (value != null) { json.put("value", value); }
		if (currency != null) { json.put("currency", currency); }
		if (issuer != null) { json.put("issuer", issuer); }
		return json;
	}
	
	/**
	 * An amount is either an amount of stellars (STR) (represetned as String) 
	 * or an amount of some other currency (represented as an json object)
	 * 
	 * eg. "amount" : "55000000"
	 * eg. 
  "amount" : {
    "value" : "34.5"
    "currency" : "BTC",
    "issuer" : "ganVp9o5emfzpwrG5QVUXqMv8AgLcdvySb",
  }
	 * 
	 * @return String or JSONObject
	 */
	public Object toValue() {
		if (value != null && currency == null && issuer == null) {
			return value;
		} else {
			return toJSONObject();
		}
	}
	
	public String toString() {
		return new JSONSerializer().prettyPrint(true).deepSerialize(this);
	}
	
}

package me.xbt.stellarj.stellard.rpc.result;

import org.json.JSONObject;

/**
 * coded based on {@link https://dev.ripple.com/rippled-apis.html#submit}
 * 
engine_result	String	Code indicating the status of the transaction, for example tesSUCCESS
engine_result_code	Integer	Numeric code indicating the status of the transaction, directly correlated to engine_result
engine_result_message	String	Human-readable explanation of the status of the transaction
tx_blob	String	The complete transaction in hex string format
tx_json	Object	The complete transaction in JSON format


{
  "result": {
    "engine_result": "tecPATH_DRY",
    "engine_result_code": 128,
    "engine_result_message": "Path could not send partial amount.",
    "status": "success",
    "tx_blob": "120000228000000024000006FB61D4871AFD498D0000000000000000000000000000555344000000000078064F19696F4ADD2438E27070E5EA51B4DD233668400000000000000A7320053DC64AE87B36159EA11EC9C387699E71596A4FE1FBD32EDE814D789E3F52217440EF68F7DF0C543002057190085DF512F147C209CF6DDB19A68BB0CC6697C02DE8DA41DACEA76BEF577936E0EB452FF96E80FD9A2B976ABD16B226E0AC26C780088114DF8286CDBB009AA5C29F526B5C3B4C480B44EABE8314ED6EC1D865CC2D4720B6CED71C42B4BA0E4E4B93",
    "tx_json": {
      "Account": "gM4Fpv2QuHY4knJsQyYGKEHFGw3eMBwc1U",
      "Amount": {
        "currency": "USD",
        "issuer": "gBAde4mkDijZatAdNhBzCsuC7GP4MzhA3B",
        "value": "2"
      },
      "Destination": "g4eRqgZfzfj3132y17iaf2fp6HQj1gofjt",
      "Fee": "10",
      "Flags": 2147483648,
      "Sequence": 1787,
      "SigningPubKey": "053DC64AE87B36159EA11EC9C387699E71596A4FE1FBD32EDE814D789E3F5221",
      "TransactionType": "Payment",
      "TxnSignature": "EF68F7DF0C543002057190085DF512F147C209CF6DDB19A68BB0CC6697C02DE8DA41DACEA76BEF577936E0EB452FF96E80FD9A2B976ABD16B226E0AC26C78008",
      "hash": "E1DA79AEB4F7D8827033E582A165B2FD792E6BC4D68AFBAB0CE6F76D395F0F03"
    }
  }
}

 * @author kaye wu
 *
 */
public class SubmitResult extends StellarResult {

	private String engine_result = null;
	private Long engine_result_code = null;
	private String engine_result_message = null;
	private String tx_blob = null;
	private JSONObject tx_json = null;
	
	public String getEngine_result() {
		return engine_result;
	}
	public void setEngine_result(String engine_result) {
		this.engine_result = engine_result;
	}
	public Long getEngine_result_code() {
		return engine_result_code;
	}
	public void setEngine_result_code(Long engine_result_code) {
		this.engine_result_code = engine_result_code;
	}
	public String getEngine_result_message() {
		return engine_result_message;
	}
	public void setEngine_result_message(String engine_result_message) {
		this.engine_result_message = engine_result_message;
	}
	public String getTx_blob() {
		return tx_blob;
	}
	public void setTx_blob(String tx_blob) {
		this.tx_blob = tx_blob;
	}
	public JSONObject getTx_json() {
		return tx_json;
	}
	public void setTx_json(JSONObject tx_json) {
		this.tx_json = tx_json;
	}
	
	
	/**
	 * convert the json string into an instance of this object.  
	 * convert manually instead of using flexjson because taker_gets can be string or object.
	 * 
	 * @param jsonString
	 * @return
	 */
	public static SubmitResult convert(String jsonString) {
		SubmitResult sr = new SubmitResult();
		JSONObject json = new JSONObject(jsonString);
		JSONObject resultJson = json.getJSONObject("result");
		if (resultJson != null) {
			StellarResult.convert(jsonString, sr);
			sr.setEngine_result(resultJson.getString("engine_result"));
			sr.setEngine_result_code(resultJson.getLong("engine_result_code"));
			sr.setEngine_result_message(resultJson.getString("engine_result_message"));
			sr.setTx_blob(resultJson.getString("tx_blob"));
			sr.setTx_json(resultJson.getJSONObject("tx_json"));
		}
		
		return sr;
	}
	
}

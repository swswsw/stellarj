package me.xbt.stellarj.stellard.rpc.result;

import org.json.JSONArray;
import org.json.JSONObject;

public class TxHistoryResult extends StellarResult {

	private Long index = null;
	private JSONArray txs = null;
	
	/**
	 * convert json string to representation of this object.  
	 * @param jsonString
	 */
	public static TxHistoryResult convert(String jsonString) {
		TxHistoryResult thr = new TxHistoryResult();
		JSONObject json = new JSONObject(jsonString);
		JSONObject resultJson = json.getJSONObject("result");
		if (resultJson != null) {
			StellarResult.convert(jsonString, thr);
			
			thr.setIndex(resultJson.getLong("index"));
			thr.setTxs(resultJson.getJSONArray("txs"));
		}
		
		return thr;
	}
	
	public Long getIndex() {
		return index;
	}
	public void setIndex(Long index) {
		this.index = index;
	}
	public JSONArray getTxs() {
		return txs;
	}
	public void setTxs(JSONArray txs) {
		this.txs = txs;
	}
	
	
}

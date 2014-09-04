package me.xbt.stellarj.stellard.rpc.result;

import java.util.ArrayList;
import java.util.List;

import flexjson.JSONSerializer;

/**
{
  "result": {
    "ledger_index": 400,
    "receive_currencies": [],
    "send_currencies": [],
    "status": "success"
  }
}
 * @author kaye wu
 *
 */
public class AccountCurrenciesResult extends StellarResult {

	//private String status;

	/** eg. ["USD", "LTC"] */
	private List<String> receive_currencies = new ArrayList<String>();
	/** eg. ["USD", "LTC"] */
	private List<String> send_currencies = new ArrayList<String>();
	

//	public String getStatus() {
//		return status;
//	}
//	public void setStatus(String status) {
//		this.status = status;
//	}
	
	public List<String> getReceive_currencies() {
		return receive_currencies;
	}
	public void setReceive_currencies(List<String> receive_currencies) {
		this.receive_currencies = receive_currencies;
	}
	public List<String> getSend_currencies() {
		return send_currencies;
	}
	public void setSend_currencies(List<String> send_currencies) {
		this.send_currencies = send_currencies;
	}

	
}

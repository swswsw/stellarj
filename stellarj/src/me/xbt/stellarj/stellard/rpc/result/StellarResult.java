package me.xbt.stellarj.stellard.rpc.result;

import flexjson.JSONSerializer;

/**
 * 
 * @author kaye wu
 *
 */
public class StellarResult {

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public String toString() {
		return new JSONSerializer().prettyPrint(true).deepSerialize(this);
	}
}

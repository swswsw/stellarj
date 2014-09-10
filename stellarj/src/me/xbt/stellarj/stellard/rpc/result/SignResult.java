package me.xbt.stellarj.stellard.rpc.result;

/**
 * coded according to https://www.stellar.org/api/#api-sign
 * 
 * @author kaye wu
 * 
 * @see {@link https://www.stellar.org/api/#api-sign}
 */
public class SignResult extends StellarResult {

	private String hash = null;
	private String tx_blob = null;
	
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getTx_blob() {
		return tx_blob;
	}
	public void setTx_blob(String tx_blob) {
		this.tx_blob = tx_blob;
	}
	
}

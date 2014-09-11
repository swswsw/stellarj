package me.xbt.stellarj.stellard.rpc;

/**
 * object representing order book.  
 * used in {@link https://www.stellar.org/api/#api-subscribe}
 * 
Order book object is:

taker_gets- type of currency and issuer the taker of these offers will get.
taker_pays- type of currency and issuer the taker of these offers will pay.
both- if set to true will send you events for both sides of the order book.
snapshot- if set to true will send you the current state of the order book before it starts streaming the changes.
Example: { "taker_gets" : { "currency": "USD", "issuer" : address }, "taker_pays" : { "currency": "BTC", "issuer" : address }, "snapshot" : "true" , "both" : "true" }

 * @author kaye wu
 *
 */
public class Book {
	
	private Currency taker_gets = null;
	private Currency taker_pays = null;
	private Boolean both = null;
	private Boolean snapshot = null;
	
	
	
	public Currency getTaker_gets() {
		return taker_gets;
	}
	public void setTaker_gets(Currency taker_gets) {
		this.taker_gets = taker_gets;
	}
	public Currency getTaker_pays() {
		return taker_pays;
	}
	public void setTaker_pays(Currency taker_pays) {
		this.taker_pays = taker_pays;
	}
	public Boolean getBoth() {
		return both;
	}



	public void setBoth(Boolean both) {
		this.both = both;
	}



	public Boolean getSnapshot() {
		return snapshot;
	}



	public void setSnapshot(Boolean snapshot) {
		this.snapshot = snapshot;
	}



	public static class Currency {
		private String currency = null;
		private String issuer = null;
		
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
		
		
	}
}

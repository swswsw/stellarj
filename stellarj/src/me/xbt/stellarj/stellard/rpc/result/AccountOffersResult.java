package me.xbt.stellarj.stellard.rpc.result;

import java.util.ArrayList;
import java.util.List;

/**
 * because documentation is currently unavailable on stellar.  
 * we follow ripple documentation here: http://dev.ripple.com/rippled-apis.html#account-offers
 * 
 * @author kaye wu
 *
 */
public class AccountOffersResult extends StellarResult {

	private String account = null;
	
	private List<Offer> offers = new ArrayList<Offer>();
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	/**
	 * eg.
	 {
        "flags": 0,
        "seq": 40,
        "taker_gets": "600000000",
        "taker_pays": {
          "currency": "DBG",
          "issuer": "g4cHTkj4YQmMJhrJFCKL1g9rNf4yJRpruP",
          "value": "55"
        }
      }
	 * 
	 * note taker_gets and taker_pays may be string or object 
	 * depending on the type of currency it represents.  
	 * 
	 * @author kaye wu
	 *
	 */
	public static class Offer {
		private int flags = -1;
		private int seq = -1;
		
		// these 2 value could be a string or an object.
		// java is a strongly-typed language and cannot do that. 
		// so we encapsulate the value in CurrencyAmount object.  
		private CurrencyAmount taker_gets = null;
		private CurrencyAmount taker_pays = null;
		
		public int getFlags() {
			return flags;
		}
		public void setFlags(int flags) {
			this.flags = flags;
		}
		public int getSeq() {
			return seq;
		}
		public void setSeq(int seq) {
			this.seq = seq;
		}
		public CurrencyAmount getTaker_gets() {
			return taker_gets;
		}
		public void setTaker_gets(CurrencyAmount taker_gets) {
			this.taker_gets = taker_gets;
		}
		public CurrencyAmount getTaker_pays() {
			return taker_pays;
		}
		public void setTaker_pays(CurrencyAmount taker_pays) {
			this.taker_pays = taker_pays;
		}
	}
	
	/**
	 * see "Specifying Currency Amounts" section on this doc for more detail.
	 * http://dev.ripple.com/rippled-apis.html#specifying-currency-amounts
	 * 
	 * if it is native currency (str), then only value will be specified.
	 * eg.
		{
          "value": "55"
        }
	 * 
	 *   
	 * if it is other currency, all the information will be specified.  
	 * 
	 * eg. 
		{
          "currency": "DBG",
          "issuer": "g4cHTkj4YQmMJhrJFCKL1g9rNf4yJRpruP",
          "value": "55"
        }
	 * 
	 * @author kaye wu
	 *
	 */
	public static class CurrencyAmount {
		private String currency = null;
		private String value = null;
		private String issuer = null;
		
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getIssuer() {
			return issuer;
		}
		public void setIssuer(String issuer) {
			this.issuer = issuer;
		}
	}
	
	
}

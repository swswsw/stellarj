package me.xbt.stellarj.stellard.rpc;

/**
 * used in book_offers().
 * 
  eg. 
  {
    "value": "1",
    "currency": "EUR",
    "issuer": "rvYAfWj5gh67oV6fW32ZzP3Aw4Eubs59B"
  }
 * @author t
 *
 * @see {@link http://dev.ripple.com/rippled-apis.html#book-offers}
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
	
	
}

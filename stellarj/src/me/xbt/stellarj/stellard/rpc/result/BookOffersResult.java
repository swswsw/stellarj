package me.xbt.stellarj.stellard.rpc.result;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * for documentation, see 
 * http://dev.ripple.com/rippled-apis.html#book-offers "Response Format" section.  
 * and 
 * https://www.stellar.org/api/#api-book_offers
 * 
 * note, stellar doc has 2 fields, hash, and marker, that are not in ripple doc.  
 * ripple doc has 3 fields that are not in stellar doc.  
 * 
 * currently, this class conforms to the ripple doc because ripple doc seems to be more mature at the moment.   
 * 
 * @author kaye wu
 * @see {@link https://www.stellar.org/api/#api-book_offers}
 * @see {@link http://dev.ripple.com/rippled-apis.html#book-offers}
 */
public class BookOffersResult extends StellarResult {
	//
	// these 2 fields comes from stellar doc, but is not in stellar doc, for now, we are not include them because ripple doc seems more mature.  
	//
//	private String hash = null;
//	private String marker = null;
	
//	Field	Type	Description
//	ledger_current_index	Integer	(Omitted if ledger version provided) Sequence number of the ledger version used when retrieving this data.
//	ledger_index	Integer	(Omitted if ledger_current_index provided instead) Sequence number, provided in the request, of the ledger version that was used when retrieving this data.
//	ledger_hash	String	(May be omitted) Hex hash, provided in the request, of the ledger version that was used when retrieving this data.
//	offers	Array	Array of offer objects, each of which has the fields of an offer transaction
	
	// these 3 fields comes from ripple doc
	private Long ledger_current_index = null;
	private Long ledger_index = null;
	private String ledger_hash = null;
	
	
	List<Offer> offers = new ArrayList<Offer>();
	
	
	
	
	
	public Long getLedger_current_index() {
		return ledger_current_index;
	}
	public void setLedger_current_index(Long ledger_current_index) {
		this.ledger_current_index = ledger_current_index;
	}
	public Long getLedger_index() {
		return ledger_index;
	}
	public void setLedger_index(Long ledger_index) {
		this.ledger_index = ledger_index;
	}
	public String getLedger_hash() {
		return ledger_hash;
	}
	public void setLedger_hash(String ledger_hash) {
		this.ledger_hash = ledger_hash;
	}
	public List<Offer> getOffers() {
		return offers;
	}
	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}





	/**
	 * 
	 * @author kaye wu
	 */
	public static class Offer {
//		taker_gets_funded	String (XRP) or Object (non-XRP)	(Only included in partially-funded offers) The maximum amount of currency that the taker can get, given the funding status of the offer.
//		taker_pays_funded	String (XRP) or Object (non-XRP)	(Only included in partially-funded offers) The maximum amount of currency that the taker would pay, given the funding status of the offer.
//		quality	Number	The exchange rate, as the ratio taker_pays divided by taker_gets. For fairness, offers that have the same quality are automatically taken first-in, first-out. (In other words, if multiple people offer to exchange currency at the same rate, the oldest offer is taken first.)		
		private String taker_gets_funded = null;
		private String taker_pays_funded = null;
		private double quality = UNINITIATED;
		
		public String getTaker_gets_funded() {
			return taker_gets_funded;
		}
		public void setTaker_gets_funded(String taker_gets_funded) {
			this.taker_gets_funded = taker_gets_funded;
		}
		public String getTaker_pays_funded() {
			return taker_pays_funded;
		}
		public void setTaker_pays_funded(String taker_pays_funded) {
			this.taker_pays_funded = taker_pays_funded;
		}
		public double getQuality() {
			return quality;
		}
		public void setQuality(double quality) {
			this.quality = quality;
		} 
		
		
	}
}

package me.xbt.stellarj.stellard.rpc.result;

/**
 * the format is not entirely the same as ripple doc because stellard returns 
 * different format as ripple.  the class attributes here follow emperical 
 * result of stellard.  
 * 
 * @author kaye wu
 *
 */
public class LedgerResult extends StellarResult {

	private LedgerContainer closed = null;
	private LedgerContainer open = null;

	public LedgerContainer getClosed() {
		return closed;
	}

	public void setClosed(LedgerContainer closed) {
		this.closed = closed;
	}

	public LedgerContainer getOpen() {
		return open;
	}

	public void setOpen(LedgerContainer open) {
		this.open = open;
	}
	
	
	public static class LedgerContainer {
		private Ledger ledger = null;

		public Ledger getLedger() {
			return ledger;
		}

		public void setLedger(Ledger ledger) {
			this.ledger = ledger;
		}
		
		
	}
	
	public static class Ledger {
//		accepted	Boolean	This designation is for internal protocol use, and should not be used. It does not mean the same as validated
//		account_hash	String	Hash of all account state information in this ledger, as hex
//		close_time	Integer	The time this ledger was closed, in seconds since the Ripple Epoch
//		close_time_human	String	The time this ledger was closed, in human-readable format
//		close_time_resolution	Integer	Approximate number of seconds between closing one ledger version and closing the next one
//		closed	Boolean	Whether or not this ledger has been closed
//		ledger_hash	String	Unique identifying hash of the entire ledger.
//		ledger_index	String	Ledger sequence number as a quoted integer
//		parent_hash	String	Unique identifying hash of the ledger that came immediately before this one.
//		total_coins	String	Total number of XRP drops in the network, as a quoted integer. (This decreases as transaction fees cause XRP to be destroyed.)
//		transaction_hash	String	Hash of the transaction information included in this ledger, as hex
		
		private Boolean accepted = null;
		private String account_hash = null;
		private Long close_time = null;
		private String close_time_human = null;
		private Long close_time_resolution = null;
		private Boolean closed = null;
		private String ledger_hash = null;
		private String ledger_index = null;
		private String parent_hash = null;
		private String total_coins = null;
		private String transaction_hash = null;
		
		public Boolean getAccepted() {
			return accepted;
		}
		public void setAccepted(Boolean accepted) {
			this.accepted = accepted;
		}
		public String getAccount_hash() {
			return account_hash;
		}
		public void setAccount_hash(String account_hash) {
			this.account_hash = account_hash;
		}
		public Long getClose_time() {
			return close_time;
		}
		public void setClose_time(Long close_time) {
			this.close_time = close_time;
		}
		public String getClose_time_human() {
			return close_time_human;
		}
		public void setClose_time_human(String close_time_human) {
			this.close_time_human = close_time_human;
		}
		public Long getClose_time_resolution() {
			return close_time_resolution;
		}
		public void setClose_time_resolution(Long close_time_resolution) {
			this.close_time_resolution = close_time_resolution;
		}
		public Boolean getClosed() {
			return closed;
		}
		public void setClosed(Boolean closed) {
			this.closed = closed;
		}
		public String getLedger_hash() {
			return ledger_hash;
		}
		public void setLedger_hash(String ledger_hash) {
			this.ledger_hash = ledger_hash;
		}
		public String getLedger_index() {
			return ledger_index;
		}
		public void setLedger_index(String ledger_index) {
			this.ledger_index = ledger_index;
		}
		public String getParent_hash() {
			return parent_hash;
		}
		public void setParent_hash(String parent_hash) {
			this.parent_hash = parent_hash;
		}
		public String getTotal_coins() {
			return total_coins;
		}
		public void setTotal_coins(String total_coins) {
			this.total_coins = total_coins;
		}
		public String getTransaction_hash() {
			return transaction_hash;
		}
		public void setTransaction_hash(String transaction_hash) {
			this.transaction_hash = transaction_hash;
		}
		
		
		
	}
}

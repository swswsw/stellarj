package me.xbt.stellarj.stellard.rpc.result;

import flexjson.JSONSerializer;

/**
 * why are attribute names in camelcase when 
 * most of the **Result class have _ attribute names?  
 * it is because the actual stellar api defined the name 
 * inconsistently.  we merely follow the name in api.  
 * 
 * @author kaye wu
 *
 */
public class AccountInfoResult extends StellarResult {

	private AccountData account_data = null;
	
	public AccountData getAccount_data() {
		return account_data;
	}

	public void setAccount_data(AccountData account_data) {
		this.account_data = account_data;
	}

	public static class AccountData {
		private long Balance = -1;
		private int Flags = -1;
		private int OwnerCount = -1;
		private String PreviousTxnID = null;
		private long PreviousTxnLgrSeq = -1;
		private long Sequence = -1;
		private String index = null;
		
		public long getBalance() {
			return Balance;
		}
		public void setBalance(long balance) {
			Balance = balance;
		}
		public int getFlags() {
			return Flags;
		}
		public void setFlags(int flags) {
			Flags = flags;
		}
		public int getOwnerCount() {
			return OwnerCount;
		}
		public void setOwnerCount(int ownerCount) {
			OwnerCount = ownerCount;
		}
		public String getPreviousTxnID() {
			return PreviousTxnID;
		}
		public void setPreviousTxnID(String previousTxnID) {
			PreviousTxnID = previousTxnID;
		}
		public long getPreviousTxnLgrSeq() {
			return PreviousTxnLgrSeq;
		}
		public void setPreviousTxnLgrSeq(long previousTxnLgrSeq) {
			PreviousTxnLgrSeq = previousTxnLgrSeq;
		}
		public long getSequence() {
			return Sequence;
		}
		public void setSequence(long sequence) {
			Sequence = sequence;
		}
		public String getIndex() {
			return index;
		}
		public void setIndex(String index) {
			this.index = index;
		}
		
	}
	
	public String toString() {
		return new JSONSerializer().prettyPrint(true).deepSerialize(this);
	}
}

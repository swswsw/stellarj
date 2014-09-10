package me.xbt.stellarj.stellard.rpc;

/**
A particular ledger to get this information from. It can either be a sequence number or one of the following:

current- The current view of stellard can change when the ledger is closed.
validated- The last validated ledger this stellard knows about. As long as this stellard is properly connected to the network, this ledger should only change as a result of a major catastrophe.

 * 
 * in other words, it can either contain sequence number, or a keyword (current|validated), but not both.
 * 
 * @author kaye wu
 *
 */
public class LedgerIndex {

	public enum Keyword {
		current,
		validated
	}
	
	private int seqNum = -1;
	private Keyword keyword = null;
	
	public int getSeqNum() {
		return seqNum;
	}
	public void setSeqNum(int seqNum) {
		this.seqNum = seqNum;
	}
	public Keyword getKeyword() {
		return keyword;
	}
	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}
	
	/**
	 * convert to value.  
	 * it is either string of "current" or "validated", or a sequence number.  
	 * @return String or Integer
	 */
	public Object toValue() {
		if (this.getKeyword() != null) {
			return this.getKeyword().toString();
		} else {
			return this.getSeqNum();
		}
	}
	
}

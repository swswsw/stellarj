package me.xbt.stellarj.stellard.rpc.result;

import java.util.ArrayList;
import java.util.List;


/**
 * result of account_lines() method.
 * 
 * @author kaye wu
 *
 */
public class AccountLinesResult extends StellarResult {
	
	private List<Line> lines = new ArrayList<Line>();
	
	public List<Line> getLines() {
		return lines;
	}

	public void setLines(List<Line> lines) {
		this.lines = lines;
	}

	public static class Line {
		private String account = null;
		private String balance = null;
		private String currency = null;
		private String limit = null;
		private String limit_peer = null;
		private int quality_in = UNINITIATED;
		private int quality_out = UNINITIATED;
		
		public String getAccount() {
			return account;
		}
		public void setAccount(String account) {
			this.account = account;
		}
		public String getBalance() {
			return balance;
		}
		public void setBalance(String balance) {
			this.balance = balance;
		}
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
		}
		public String getLimit() {
			return limit;
		}
		public void setLimit(String limit) {
			this.limit = limit;
		}
		public String getLimit_peer() {
			return limit_peer;
		}
		public void setLimit_peer(String limit_peer) {
			this.limit_peer = limit_peer;
		}
		public int getQuality_in() {
			return quality_in;
		}
		public void setQuality_in(int quality_in) {
			this.quality_in = quality_in;
		}
		public int getQuality_out() {
			return quality_out;
		}
		public void setQuality_out(int quality_out) {
			this.quality_out = quality_out;
		}
		
		
	}
	
	
}

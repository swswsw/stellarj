package me.xbt.stellarj.stellard.rpc;

import org.json.JSONArray;

/**
 * represent a stellar transaction
 * 
 * we will create these when we understand this more.  
 * 
 * {@link https://wiki.ripple.com/Transaction_Format}
 * 
 * @author t
 *
 */
public class StellarTx {

//	TransactionType	UInt16	Required	The type of transaction this is. Internally represented as an integer. In JSON-RPC and WebSocket APIs, specify as a string such as "Payment", "OfferCreate", "OfferCancel", "TrustSet", or "AccountSet".
//	Flags	UInt32	Optional	Selects transaction options
//	SourceTag	UInt32	Optional	Allows the creator of transaction to identify the transaction. For payments, specify the DestinationTag for returning funds.
//	Memos	Array	Optional	Allows the creator to include additional information to identify the transaction.
//	Account	Account	Required	The account issuing the transaction
//	Sequence	UInt32	Required	The transaction's sequence number
//	PreviousTxnID	Hash256	Optional	The ID of the immediately previous transaction that this transaction replaces.
//	LastLedgerSequence	UInt32	Optional	Highest valid ledger number that a transaction can appear in. If this is specified, and the transaction is not included by the time the ledger reaches the specified ledger sequence number, then the transaction is considered to have failed and will no longer be valid.
//	Fee	Amount	Required	The transaction fee
//	SigningPubKey	Public Key	Required	The public key matching the private key used to sign the transaction.
//	TxnSignature	VariableLength	Optional	The signature for the transaction.
	
	private String TransactionType = null;
	private Long Flags = null;
	private Long SourceTag = null;
	private JSONArray Memos = null;
	private String Account = null;
	private Long Sequence = null;
	private String PreviousTxnID = null;
	private Amount Fee = null;
	private String SigningPubKey = null;
	private String TxnSignature = null;
	
	public String getTransactionType() {
		return TransactionType;
	}
	public void setTransactionType(String transactionType) {
		TransactionType = transactionType;
	}
	public Long getFlags() {
		return Flags;
	}
	public void setFlags(Long flags) {
		Flags = flags;
	}
	public Long getSourceTag() {
		return SourceTag;
	}
	public void setSourceTag(Long sourceTag) {
		SourceTag = sourceTag;
	}
	public JSONArray getMemos() {
		return Memos;
	}
	public void setMemos(JSONArray memos) {
		Memos = memos;
	}
	public String getAccount() {
		return Account;
	}
	public void setAccount(String account) {
		Account = account;
	}
	public Long getSequence() {
		return Sequence;
	}
	public void setSequence(Long sequence) {
		Sequence = sequence;
	}
	public String getPreviousTxnID() {
		return PreviousTxnID;
	}
	public void setPreviousTxnID(String previousTxnID) {
		PreviousTxnID = previousTxnID;
	}
	public Amount getFee() {
		return Fee;
	}
	public void setFee(Amount fee) {
		Fee = fee;
	}
	public String getSigningPubKey() {
		return SigningPubKey;
	}
	public void setSigningPubKey(String signingPubKey) {
		SigningPubKey = signingPubKey;
	}
	public String getTxnSignature() {
		return TxnSignature;
	}
	public void setTxnSignature(String txnSignature) {
		TxnSignature = txnSignature;
	}
	
	
	
	
}

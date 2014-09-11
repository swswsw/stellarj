package me.xbt.stellarj.stellard.rpc;

import org.json.JSONArray;

/**
 * payment type transaction.  
 * {@link https://wiki.ripple.com/Transaction_Format#Payment_.280.29} 
 * 
 * @author t
 *
 */
public class PaymentTx extends StellarTx {

//	Destination	Account	Required	The receiving account
//	Amount	Amount	Required	The amount and currency for the destination to receive. To deliver a specific issuer's currency, set the issuer to the account of the issuer. To not specify a specific issuer, set the issuer to the receiving account.
//	SendMax	Amount	Optional	Maximum amount in a currency for the source to send. If this option is not set, it defaults to the value of Amount with the sender as issuer. The transaction is invalid if this option is set for XRP transactions or if it is the same as the value of Amount. Otherwise, this option needs to be set if the transaction is for anyone on the Ripple network (Universal Send). This option needs to be set if the funds are from a different issuer (i.e., from a hot wallet) and the issuer has a transfer fee set.
//	Paths	PathSet	Optional	List of ripple paths to use. See Payment paths.
//	DestinationTag	UInt32	Optional	Specify a tag as required by the destination to identify the reason for payment.
//	InvoiceID	Hash256	Optional	Marks this transaction as paying an invoice. The ID is the transaction ID of the invoice/bill.
	
	private String Destination = null;
	//Amount - already in base StellarTx
	private Amount SendMax = null;
	private JSONArray Paths = null;
	private Long DestinationTag = null;
	private String InvoiceID = null;
	
	public String getDestination() {
		return Destination;
	}
	public void setDestination(String destination) {
		Destination = destination;
	}
	public Amount getSendMax() {
		return SendMax;
	}
	public void setSendMax(Amount sendMax) {
		SendMax = sendMax;
	}
	public JSONArray getPaths() {
		return Paths;
	}
	public void setPaths(JSONArray paths) {
		Paths = paths;
	}
	public Long getDestinationTag() {
		return DestinationTag;
	}
	public void setDestinationTag(Long destinationTag) {
		DestinationTag = destinationTag;
	}
	public String getInvoiceID() {
		return InvoiceID;
	}
	public void setInvoiceID(String invoiceID) {
		InvoiceID = invoiceID;
	}
	
	
}

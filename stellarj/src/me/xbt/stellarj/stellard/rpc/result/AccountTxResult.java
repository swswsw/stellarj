package me.xbt.stellarj.stellard.rpc.result;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * result object for account_tx rpc call.
 * 
 * since stellar doc is lacking at the moment, we use ripple doc.  
 * http://dev.ripple.com/rippled-apis.html#account-offers
 * 
 * 
 * 
 * @author kaye wu
 *
 */
public class AccountTxResult extends StellarResult {
//	Field	Type	Description
//	account	String	Unique address identifying the related account
//	ledger_index_min	Integer	The sequence number of the earliest ledger actually searched for transactions.
//	ledger_index_max	Integer	The sequence number of the most recent ledger actually searched for transactions.
//	limit	Integer	The limit value used in the request. (This may differ from the actual limit value enforced by the server.)
//	marker	(Not Specified)	Server-defined value. Pass this to the next call in order to resume where this call left off.
//	offset	Integer	The offset value used in the request.
//	transactions	Array	Array of transactions matching the request’s criteria, as explained below.
//	validated	Boolean	If included and set to true, the information in this request comes from a validated ledger version. Otherwise, the information is subject to change.
	
	private String account = null;
	private long ledger_index_min = UNINITIATED;
	private long ledger_index_max = UNINITIATED;
	private long limit = UNINITIATED;
	private JSONObject marker = null;
	private long offset = UNINITIATED;
	private List<Transaction> transactions = new ArrayList<Transaction>();
	private boolean validated = false;
	
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public long getLedger_index_min() {
		return ledger_index_min;
	}

	public void setLedger_index_min(long ledger_index_min) {
		this.ledger_index_min = ledger_index_min;
	}

	public long getLedger_index_max() {
		return ledger_index_max;
	}

	public void setLedger_index_max(long ledger_index_max) {
		this.ledger_index_max = ledger_index_max;
	}

	public long getLimit() {
		return limit;
	}

	public void setLimit(long limit) {
		this.limit = limit;
	}

	public JSONObject getMarker() {
		return marker;
	}

	public void setMarker(JSONObject marker) {
		this.marker = marker;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public boolean isValidated() {
		return validated;
	}

	public void setValidated(boolean validated) {
		this.validated = validated;
	}
	
	
	/**
	 * convert the json string into an instance of this object.  
	 * convert manually instead of using flexjson because marker is unspecified type.
	 * 
	 * @param json
	 * @return
	 */
	public static AccountTxResult convert(String jsonString) {
		// eg.		
//		{
//			  "result": {
//			    "account": "ganVp9o5emfzpwrG5QVUXqMv8AgLcdvySb",
//			    "ledger_index_max": 189373,
//			    "ledger_index_min": 0,
//			    "limit": 4,
//			    "marker": {
//			      "ledger": 186943,
//			      "seq": 0
//			    },
//			    "status": "success",
//			    "transactions": [
//			      {
//			        "meta": {
//			          "AffectedNodes": [
//			            {
//			              "ModifiedNode": {
//			                "FinalFields": {
//			                  "Account": "ganVp9o5emfzpwrG5QVUXqMv8AgLcdvySb",
//			                  "Balance": "197628077",
//			                  "Flags": 131072,
//			                  "InflationDest": "ghj4kXtHfQcCaLQwpLJ11q2hq6248R7k9C",
//			                  "OwnerCount": 35,
//			                  "Sequence": 1736
//			                },
//			                "LedgerEntryType": "AccountRoot",
//			                "LedgerIndex": "6047FB9C7976F2D0554618F5ABFF423E7136205BAF19E92BE9D295E549442C45",
//			                "PreviousFields": {
//			                  "Balance": "196628077"
//			                },
//			                "PreviousTxnID": "B5D91A855DBA30CA05F766C2A69D49BA6F0FC90122DB860AAED4E7873DF65CFF",
//			                "PreviousTxnLgrSeq": 187762
//			              }
//			            },
//			            {
//			              "ModifiedNode": {
//			                "FinalFields": {
//			                  "Account": "garyDNx3uf8HbjxKWUKJgosgRJ7aCdugjk",
//			                  "Balance": "2356809952",
//			                  "Flags": 0,
//			                  "InflationDest": "g4eRqgZfzfj3132y17iaf2fp6HQj1gofjt",
//			                  "OwnerCount": 0,
//			                  "Sequence": 5
//			                },
//			                "LedgerEntryType": "AccountRoot",
//			                "LedgerIndex": "E484D2A78A461DF7026E8E8FB359F5E23F1F498048B94607BC208CFD547039EF",
//			                "PreviousFields": {
//			                  "Balance": "2357809964",
//			                  "Sequence": 4
//			                },
//			                "PreviousTxnID": "4DBEDB6A3AC4B39422AF621D8A186F3F19AE3B4C7956AB466182135D74AF529C",
//			                "PreviousTxnLgrSeq": 184026
//			              }
//			            }
//			          ],
//			          "TransactionIndex": 0,
//			          "TransactionResult": "tesSUCCESS"
//			        },
//			        "tx": {
//			          "Account": "garyDNx3uf8HbjxKWUKJgosgRJ7aCdugjk",
//			          "Amount": "1000000",
//			          "Destination": "ganVp9o5emfzpwrG5QVUXqMv8AgLcdvySb",
//			          "DestinationTag": 1337,
//			          "Fee": "12",
//			          "Flags": 2147483648,
//			          "LastLedgerSequence": 188323,
//			          "Sequence": 4,
//			          "SigningPubKey": "FC01C1E6008F18878F24BECF7B3A282412F19371D918898A8915FD711FF99434",
//			          "TransactionType": "Payment",
//			          "TxnSignature": "D755F1C28C5785DFD5C0B2D2BCC1B514A7A3472A1E352C94D49C19D3538F9AC5375C1FAF72D8D0A033F8F753CCAB390AA5722ACF26F27A30403996F326340700",
//			          "date": 463191640,
//			          "hash": "A54F622FEEFB19722C5D997559E2B3EA1F96B4A047048B6A4C81AAA3F03CFC4F",
//			          "inLedger": 188315,
//			          "ledger_index": 188315
//			        },
//			        "validated": true
//			      },
//			    ]
//			  }
//			}
		AccountTxResult atr = new AccountTxResult();
		JSONObject json = new JSONObject(jsonString);
		JSONObject resultJson = json.getJSONObject("result");
		if (resultJson != null) {
			atr.setStatus(resultJson.getString("status"));
			atr.setAccount(resultJson.getString("account"));
			atr.setLedger_index_max(resultJson.getLong("ledger_index_max"));
			atr.setLedger_index_min(resultJson.getLong("ledger_index_min"));
			atr.setLimit(resultJson.getLong("limit"));
			atr.setMarker(resultJson.getJSONObject("marker"));
			try {
				atr.setOffset(resultJson.getLong("offset"));
			} catch (JSONException ex) {
				// do nothing.  although ripple doc said it should be there, it is not there in stellar.  
			}
			atr.setStatus(resultJson.getString("status"));
			try {
				atr.setValidated(resultJson.getBoolean("validated"));
			} catch (JSONException ex) {
				// do nothing.  validated is optional in response.  
			}
			
			JSONArray transactionsJson = resultJson.getJSONArray("transactions");
			List<Transaction> transactions = new ArrayList<Transaction>();
			if (transactionsJson != null) {
				for (int i=0; i<transactionsJson.length(); i++) {
					JSONObject transactionJson = transactionsJson.getJSONObject(i);
					Transaction transaction = new Transaction();
					
					try {
						transaction.setLedger_index(transactionJson.getLong("ledger_index"));
					} catch (JSONException ex) {
						// do nothing.  although ripple doc said it should be there, it is not there in stellar.  
					}
					
					transaction.setMeta(toMeta(transactionJson.get("meta")));
					
					try {
						transaction.setTx(transactionJson.getJSONObject("tx"));
					} catch (JSONException ex) {
						// do nothing.  only one of tx or tx_blob may be present.  
					}
					
					try {
						transaction.setTx_blob(transactionJson.getString("tx_blob"));
					} catch (JSONException ex) {
						// do nothing.  only one of tx or tx_blob may be present.   
					}
					
					transaction.setValidated(transactionJson.getBoolean("validated"));
					
					transactions.add(transaction);
				}
			}
			atr.setTransactions(transactions);
		}
		
		return atr;
	}

	/**
	 * convert a string or jsonobject to Meta
	 * only one of the str or obj will be populated depending on the format.
	 *   
	 * @param obj - could be either a String or JSONObject
	 * eg. "6000000" 
	 * eg.
	 * {
          ...
       }
	 * @return
	 */
	private static Meta toMeta(Object obj) {
		Meta meta = new Meta();
		if (obj instanceof String) {
			meta.setStr((String)obj);
		} else if (obj instanceof JSONObject) {
			meta.setObj((JSONObject)obj);
		}
		
		return meta;
	}
	
	public static class Transaction {
//		Field	Type	Description
//		ledger_index	Integer	The sequence number of the ledger version that included this transaction.
//		meta	Object (JSON) or String (Binary)	If binary is True, then this is a hex string of the transaction metadata. Otherwise, the transaction metadata is included in JSON format.
//		tx	Object	(JSON mode only) JSON object defining the transaction
//		tx_blob	String	(Binary mode only) Unique hashed String representing the transaction.
//		validated	Boolean	Whether or not the transaction is included in a validated ledger. Any transaction not yet in a validated ledger is subject to change.
		
		private long ledger_index = UNINITIATED;
		private Meta meta = null;
		private JSONObject tx = null;
		private String tx_blob = null;
		private boolean validated = false;
		
		public long getLedger_index() {
			return ledger_index;
		}
		public void setLedger_index(long ledger_index) {
			this.ledger_index = ledger_index;
		}
		public Meta getMeta() {
			return meta;
		}
		public void setMeta(Meta meta) {
			this.meta = meta;
		}
		public JSONObject getTx() {
			return tx;
		}
		public void setTx(JSONObject tx) {
			this.tx = tx;
		}
		public String getTx_blob() {
			return tx_blob;
		}
		public void setTx_blob(String tx_blob) {
			this.tx_blob = tx_blob;
		}
		public boolean isValidated() {
			return validated;
		}
		public void setValidated(boolean validated) {
			this.validated = validated;
		}
		
		
	}
	
	/**
	 * Object (JSON) or String (Binary)	
	 * If binary is True, then this is a hex string of the transaction metadata. 
	 * Otherwise, the transaction metadata is included in JSON format.
	 * 
	 * only one of the str or obj will be populated depending on the format.  
	 * 
	 * @author t
	 *
	 */
	public static class Meta {
		private String str = null;
		private JSONObject obj = null;
		
		public String getStr() {
			return str;
		}
		public void setStr(String str) {
			this.str = str;
		}
		public JSONObject getObj() {
			return obj;
		}
		public void setObj(JSONObject obj) {
			this.obj = obj;
		}
	}
}

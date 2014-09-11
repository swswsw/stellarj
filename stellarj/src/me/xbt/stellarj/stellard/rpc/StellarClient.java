package me.xbt.stellarj.stellard.rpc;

import java.io.IOException;

import me.xbt.stellarj.stellard.rpc.result.AccountCurrenciesResult;
import me.xbt.stellarj.stellard.rpc.result.AccountInfoResult;
import me.xbt.stellarj.stellard.rpc.result.AccountLinesResult;
import me.xbt.stellarj.stellard.rpc.result.AccountOffersResult;
import me.xbt.stellarj.stellard.rpc.result.AccountTxResult;
import me.xbt.stellarj.stellard.rpc.result.BookOffersResult;
import me.xbt.stellarj.stellard.rpc.result.CreateKeysResult;
import me.xbt.stellarj.stellard.rpc.result.LedgerResult;
import me.xbt.stellarj.stellard.rpc.result.PingResult;
import me.xbt.stellarj.stellard.rpc.result.SignResult;
import me.xbt.stellarj.stellard.rpc.result.StaticPathFindResult;
import me.xbt.stellarj.stellard.rpc.result.StellarResultContainer;
import me.xbt.stellarj.stellard.rpc.result.SubmitResult;
import me.xbt.stellarj.stellard.rpc.result.TxHistoryResult;
import me.xbt.stellarj.stellard.rpc.result.TxResult;

import org.json.JSONObject;

import flexjson.JSONDeserializer;

/**
 * 
 * @author kaye wu
 *
 */
public class StellarClient {

	private final String url;
	
	public StellarClient(String url) {
		this.url = url;
	}
	
	/**
	 * 
	 * @param account
	 * @param ledgerIndex optional | default: current 
		A particular ledger to get this information from. It can either be a sequence number or one of the following:
		
		current- The current view of stellard can change when the ledger is closed.
		validated- The last validated ledger this stellard knows about. As long as this stellard is properly connected to the network, this ledger should only change as a result of a major catastrophe.
	 * @param ledgerHash
	 * optional 
		You can also specify a particular ledger by its hash to get this information from.
	 * @return
	 * @throws IOException
	 */
	public AccountCurrenciesResult accountCurrencies(String account, LedgerIndex ledgerIndex, String ledgerHash) throws IOException {
		RpcClient client = new RpcClient(url);
		// send command
		JSONObject param = new JSONObject();
		param.put("account", account);
		if (ledgerIndex != null) {
			param.put("ledger_index", ledgerIndex.toValue());
		}
		if (ledgerHash != null) {
			param.put("ledger_hash", ledgerHash);
		}
		String jsonResult = client.sendCommand("account_currencies", param);
		// convert result
		//AccountCurrenciesResult result = new JSONDeserializer<AccountCurrenciesResult>().deserialize(jsonResult, AccountCurrenciesResult.class);
				
		StellarResultContainer container = new JSONDeserializer<StellarResultContainer>().use("result", AccountCurrenciesResult.class).deserialize(jsonResult, StellarResultContainer.class);
		
		return (AccountCurrenciesResult)container.getResult();
	}
	
	public AccountInfoResult accountInfo(String account) throws IOException {
		RpcClient client = new RpcClient(url);
		// send command
		JSONObject param = new JSONObject();
		param.put("account", account);
		
		String jsonResult = client.sendCommand("account_info", param);
		// convert result
		//AccountCurrenciesResult result = new JSONDeserializer<AccountCurrenciesResult>().deserialize(jsonResult, AccountCurrenciesResult.class);
				
		StellarResultContainer container = new JSONDeserializer<StellarResultContainer>().use("result", AccountInfoResult.class).deserialize(jsonResult, StellarResultContainer.class);
		
		return (AccountInfoResult)container.getResult();
	}
	
	public AccountLinesResult accountLines(String account, String peer, LedgerIndex ledgerIndex, String ledgerHash) throws IOException {
		RpcClient client = new RpcClient(url);
		// send command
		JSONObject param = new JSONObject();
		param.put("account", account);
		if (peer != null) {
			param.put("peer", peer);
		}
		if (ledgerIndex != null) {
			param.put("ledger_index", ledgerIndex.toValue());
		}
		if (ledgerHash != null) {
			param.put("ledger_hash", ledgerHash);
		}
		String jsonResult = client.sendCommand("account_lines", param);
		// convert result
		StellarResultContainer container = new JSONDeserializer<StellarResultContainer>().use("result", AccountLinesResult.class).deserialize(jsonResult, StellarResultContainer.class);
		
		return (AccountLinesResult)container.getResult();
	}
	
	public AccountOffersResult accountOffers(String account, String peer, LedgerIndex ledgerIndex, String ledgerHash) throws IOException {
		RpcClient client = new RpcClient(url);
		// send command
		JSONObject param = new JSONObject();
		param.put("account", account);
		if (peer != null) {
			param.put("peer", peer);
		}
		if (ledgerIndex != null) {
			param.put("ledger_index", ledgerIndex.toValue());
		}
		if (ledgerHash != null) {
			param.put("ledger_hash", ledgerHash);
		}
		String jsonResult = client.sendCommand("account_offers", param);
		
		// convert result manually because taker_gets can be string or object.
		AccountOffersResult result = AccountOffersResult.convert(jsonResult);
		
		return result;
	}
	
	/**
	 * 
Field	Type	Description
account	String	A unique identifier for the account, most commonly the account’s address.
ledger_index_min	Integer	Use to specify the earliest ledger to include transactions from. A value of -1 instructs the server to use the earliest ledger available.
ledger_index_max	Integer	Use to specify the most recent ledger to include transactions from. A value of -1 instructs the server to use the most recent one available.
ledger_hash	String	(Optional) Use instead of ledger_index_min and ledger_index_max to look for transactions from a single ledger only. (See Specifying a Ledger)
ledger_index	String or Unsigned Integer	(Optional) Use instead of ledger_index_min and ledger_index_max to look for transactions from a single ledger only. (See Specifying a Ledger)
binary	Boolean	(Optional, defaults to False) If set to True, return transactions as hex strings instead of JSON.
forward	boolean	(Optional, defaults to False) If set to True, return values indexed with the oldest ledger first. Otherwise, the results are indexed with the newest ledger first. (Each page of results may not be internally ordered, but the pages are overall ordered.)
limit	Integer	(Optional, default varies) Limit the number of transactions to retrieve. The server is not required to honor this value.
marker	(Not Specified)	Server-provided value to specify where to resume retrieving data from.

	 * @param account
	 * @param peer
	 * @param ledgerIndex
	 * @param ledgerHash
	 * @return
	 * @throws IOException
	 */
	public AccountTxResult accountTx(String account, Long ledgerIndexMin, Long ledgerIndexMax, LedgerIndex ledgerIndex, 
			String ledgerHash, Boolean binary, Boolean forward, Long limit, JSONObject marker) throws IOException {
		RpcClient client = new RpcClient(url);
		// send command
		JSONObject param = new JSONObject();
		param.put("account", account);
		if (ledgerIndexMin != null) { param.put("ledger_index_min", ledgerIndexMin); }
		if (ledgerIndexMax != null) { param.put("ledger_index_max", ledgerIndexMax); }
		if (ledgerIndex != null) {
			param.put("ledger_index", ledgerIndex.toValue());
		}
		if (ledgerHash != null) {
			param.put("ledger_hash", ledgerHash);
		}
		if (binary != null) { param.put("binary", binary); }
		if (forward != null) { param.put("forward", forward); }
		if (limit != null) { param.put("limit", limit); }
		if (marker != null) { param.put("marker", marker); }
		
		System.out.println("param=" + param);
		
		String jsonResult = client.sendCommand("account_tx", param);
		
		// convert result manually because taker_gets can be string or object.
		AccountTxResult result = AccountTxResult.convert(jsonResult);
		
		return result;
	}
	
	/**
	 * there are some differences between stellar doc and ripple doc for this methods 
	 * in the number of arguments.  we are following ripple in this case because ripple 
	 * doc seems to be more mature.  
	 * 
taker	String	(Optional, defaults to ACCOUNT_ONE) Unique base-58 address of an account to use as point-of-view. (This affects which unfunded offers are returned.)
taker_gets	Object	Specification of which currency the account taking the offer would receive, as an object with currency and issuer fields (omit issuer for XRP), similar to currency amounts.
taker_pays	Object	Specification of which currency the account taking the offer would pay, as an object with currency and issuer fields (omit issuer for XRP), similar to currency amounts.
	 */
	public BookOffersResult bookOffers(String taker, Amount takerGets, Amount takerPays) throws IOException {
		RpcClient client = new RpcClient(url);
		// send command
		JSONObject param = new JSONObject();
		if (taker != null) { param.put("taker", taker); }
		if (takerGets != null) { param.put("taker_gets", takerGets.toJSONObject()); }
		if (takerPays != null) { param.put("taker_pays", takerPays.toJSONObject()); }
		
		System.out.println("param=" + param);
		
		String jsonResult = client.sendCommand("book_offers", param);
		
		// convert result 
		StellarResultContainer container = new JSONDeserializer<StellarResultContainer>().use("result", BookOffersResult.class).deserialize(jsonResult, StellarResultContainer.class);
		
		return (BookOffersResult)container.getResult();
	}
	
	/**
	 * 
	 * Generates public/private key pair for use as a Stellar account.
	 * 
	 * @param passphrase - [string | optional]  If specified it will create the secret key from a hash of the passphrase.
	 * @return
	 * @throws IOException
	 * 
	 * @see {@link https://www.stellar.org/api/#api-create_keys}
	 */
	public CreateKeysResult createKeys(String passphrase) throws IOException {
		RpcClient client = new RpcClient(url);
		// send command
		JSONObject param = new JSONObject();
		
		if (passphrase != null) { param.put("passphrase", passphrase); }
		
		System.out.println("param=" + param);
		
		String jsonResult = client.sendCommand("create_keys", param);
		
		// convert result 
		StellarResultContainer container = new JSONDeserializer<StellarResultContainer>().use("result", CreateKeysResult.class).deserialize(jsonResult, StellarResultContainer.class);
		
		return (CreateKeysResult)container.getResult();
	}
	
	/**  
	 * 
accounts	Boolean	(Optional, defaults to false) If true, return information on accounts in the ledger. Admin required. If enabled, this method returns a large amount of data, which may cause the request to fail due to a timeout, depending on the server load and capabilities.
transactions	Boolean	(Optional, defaults to false) If true, return information on transactions in the specified ledger version.
full	Boolean	(Optional, defaults to false) If true, return full information on the entire ledger. (Equivalent to enabling transactions, accounts, and expand Admin required
expand	Boolean	(Optional, defaults to false) Provide full JSON-formatted information for transaction/account information instead of just hashes
ledger_hash	String	(Optional) A 20-byte hex string identifying the ledger version to use.
ledger_index	(Optional) Unsigned integer, or String	(Optional, defaults to current) The sequence number of the ledger to use, or “current”, “closed”, or “validated” to select a ledger dynamically. (See Ledger Indexes.)
	 */
	public LedgerResult ledger(Boolean accounts, Boolean transactions, Boolean full,
			Boolean expand, String ledgerHash, LedgerIndex ledgerIndex) throws IOException {
		RpcClient client = new RpcClient(url);
		// send command
		JSONObject param = new JSONObject();
		if (accounts != null) { param.put("accounts", accounts); }
		if (transactions != null) { param.put("transactions", transactions); }
		if (full != null) { param.put("full", full); }
		if (expand != null) { param.put("expand", accounts); }
		if (ledgerHash != null) { param.put("ledger_hash", ledgerHash); }
		if (ledgerIndex != null) {
			param.put("ledger_index", ledgerIndex.toValue());
		}
		
		System.out.println("param=" + param);
		
		String jsonResult = client.sendCommand("ledger", param);
		
		// convert result 
		StellarResultContainer container = new JSONDeserializer<StellarResultContainer>().use("result", LedgerResult.class).deserialize(jsonResult, StellarResultContainer.class);
		
		return (LedgerResult)container.getResult();
	}
	
	public PingResult ping() throws IOException {
		RpcClient client = new RpcClient(url);
		// send command
		JSONObject param = new JSONObject();
		
		String jsonResult = client.sendCommand("ping", param);
		// convert result
		StellarResultContainer container = new JSONDeserializer<StellarResultContainer>().use("result", PingResult.class).deserialize(jsonResult, StellarResultContainer.class);
		
		return (PingResult)container.getResult();
	}
	
	/**  
	 * arguments according to stellar doc: https://www.stellar.org/api/#api-sign
	 * 
	 * 
	 * 
tx_json	
required 
This is the JSON representation of the transaction for server to sign with the private key of the secret.
If Sequence or Fee are not filled out then stellard will fill them in for if you.

eg. 
"tx_json" : {
      "TransactionType": "Payment",
      "Account":"ganVp9o5emfzpwrG5QVUXqMv8AgLcdvySb",
      "Destination": "gHJPw9kW8v4BsUyDnBR8ZHWo8aEkhUMeAq",
      "Amount": {
        "currency": "USD",
        "issuer": "ghj4kXtHfQcCaLQwpLJ11q2hq6248R7k9C",
        "value": 10
      }
    }

secret	
required 
The base58 encoded secret key of the signer.

offline	
bool | optional 
If true stellard won't attempt to verify that the transaction is valid it will just sign it for you.
	 */
	public SignResult sign(JSONObject txJson, String secret, Boolean offline) throws IOException {
		RpcClient client = new RpcClient(url);
		// send command
		JSONObject param = new JSONObject();
		param.put("tx_json", txJson);
		param.put("secret", secret);
		if (offline != null) { param.put("offline", offline); }
		
		System.out.println("param=" + param);
		
		String jsonResult = client.sendCommand("sign", param);
		
		// convert result 
		StellarResultContainer container = new JSONDeserializer<StellarResultContainer>().use("result", SignResult.class).deserialize(jsonResult, StellarResultContainer.class);
		
		return (SignResult)container.getResult();
	}
	
	/**
	 * decided not to implement this method yet because stellar doc has not yet clearly  
	 * detail response format.  there is no static_path_find in ripple doc.
	 * todo: implements
	 * 
	 * @return
	 */
	public StaticPathFindResult staticPathFind(String sourceAccount, String destinationAccount, Amount destinationAmount) throws IOException {
		RpcClient client = new RpcClient(url);
		// send command
		JSONObject param = new JSONObject();
		param.put("source_account", sourceAccount);
		param.put("destination_account", destinationAccount);
		param.put("destination_amount", destinationAmount.toValue());
		
		System.out.println("param=" + param);
		
		String jsonResult = client.sendCommand("static_path_find", param);
		
		// convert result manually
		StaticPathFindResult result = StaticPathFindResult.convert(jsonResult);
		
		return result;
	}
	
	/**
	 * coded based on {@link https://dev.ripple.com/rippled-apis.html#submit}
	 * 
tx_blob	String	(Do not include if tx_json and secret are supplied) Hex representation of the signed transaction to submit.
tx_json	Object	(Do not include if tx_blob is supplied) Transaction definition in JSON format
secret	String	(Required if tx_json is supplied) Secret key of the account supplying the transaction, used to sign it. Do not send your secret to untrusted servers or through unsecured network connections.
fail_hard	Boolean	(Optional, defaults to false) If true, and the transaction fails locally, do not retry or relay the transaction to other servers
offline	Boolean	(Optional, defaults to false) If true, when constructing the transaction, do not attempt to automatically fill in or validate values. (No effect when specifying the transaction as tx_blob.)
	 * @param sourceAccount
	 * @param destinationAccount
	 * @param destinationAmount
	 * @return
	 * @throws IOException
	 */
	public SubmitResult submit(String txBlob, JSONObject txJson, String secret, Boolean failHard, Boolean offline) throws IOException {
		RpcClient client = new RpcClient(url);
		// send command
		JSONObject param = new JSONObject();
		if (txBlob != null) { param.put("tx_blob", txBlob); }
		if (txJson != null) { param.put("tx_json", txJson); }
		if (secret != null) { param.put("secret", secret); }
		if (failHard != null) { param.put("fail_hard", failHard); }
		if (offline != null) { param.put("offline", offline); }
		
		System.out.println("param=" + param);
		
		String jsonResult = client.sendCommand("submit", param);
		
		// convert result manually
		SubmitResult result = SubmitResult.convert(jsonResult);
		
		return result;
	}
	
//	/**
//	 * not implemented yet.  seems like this is mostly useful for websocket, not rpc.  
//	 * @return
//	 * @throws IOException
//	 */
//	public SubscribeResult subscribe(List<String> streams, List<String> accounts, List<String> accountsRt, 
//			List<Book> books, String url, String urlUsername, String urlPassword) throws IOException {
//		
//	}
	
//	/**
//	 * not implemented yet.  when i try "transactions_entry" method on test server, i get "notYetImplemented" error.  
//	 * i assume that it is not yet implemented on stellard.  
//	 * @return
//	 * @throws IOException
//	 */
//	public TransactionEntryResult transactionEntry()  throws IOException {
//		
//	}

	/**
	 * 
	 * 
transaction	
required 
Index (hash) of the transaction to fetch.

binary	
optional 
If true, will return the binary representation of this transaction.
	 * @return
	 * @throws IOException
	 */
	public TxResult tx(String transaction, Boolean binary) throws IOException {
		RpcClient client = new RpcClient(url);
		// send command
		JSONObject param = new JSONObject();
		param.put("transaction", transaction);
		if (binary != null) { param.put("binary", binary); }
		
		System.out.println("param=" + param);
		
		String jsonResult = client.sendCommand("tx", param);
		
		// convert result manually
		TxResult result = TxResult.convert(jsonResult);
		
		return result;
	}
	
	
	/**
	 * 
	 * @param start - Index to start returning transactions from.
	 * @return
	 * @throws IOException
	 */
	public TxHistoryResult txHistory(String start) throws IOException {
		RpcClient client = new RpcClient(url);
		// send command
		JSONObject param = new JSONObject();
		param.put("start", start);
		
		System.out.println("param=" + param);
		
		String jsonResult = client.sendCommand("tx_history", param);
		
		// convert result manually
		TxHistoryResult result = TxHistoryResult.convert(jsonResult);
		
		return result;
	}
}

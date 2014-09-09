package me.xbt.stellarj.stellard.rpc;

import java.io.IOException;

import me.xbt.stellarj.stellard.rpc.result.AccountCurrenciesResult;
import me.xbt.stellarj.stellard.rpc.result.AccountInfoResult;
import me.xbt.stellarj.stellard.rpc.result.AccountLinesResult;
import me.xbt.stellarj.stellard.rpc.result.AccountOffersResult;
import me.xbt.stellarj.stellard.rpc.result.AccountTxResult;
import me.xbt.stellarj.stellard.rpc.result.CreateKeysResult;
import me.xbt.stellarj.stellard.rpc.result.StellarResultContainer;

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
			if (ledgerIndex.getKeyword() != null) {
				param.put("ledger_index", ledgerIndex.getKeyword().toString());
			} else {
				param.put("ledger_index", ledgerIndex.getSeqNum());
			}
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
			if (ledgerIndex.getKeyword() != null) {
				param.put("ledger_index", ledgerIndex.getKeyword().toString());
			} else {
				param.put("ledger_index", ledgerIndex.getSeqNum());
			}
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
			if (ledgerIndex.getKeyword() != null) {
				param.put("ledger_index", ledgerIndex.getKeyword().toString());
			} else {
				param.put("ledger_index", ledgerIndex.getSeqNum());
			}
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
			if (ledgerIndex.getKeyword() != null) {
				param.put("ledger_index", ledgerIndex.getKeyword().toString());
			} else {
				param.put("ledger_index", ledgerIndex.getSeqNum());
			}
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
	
}

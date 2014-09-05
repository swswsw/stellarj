package me.xbt.stellarj.stellard.rpc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.xbt.stellarj.stellard.rpc.result.AccountCurrenciesResult;
import me.xbt.stellarj.stellard.rpc.result.AccountInfoResult;
import me.xbt.stellarj.stellard.rpc.result.AccountLinesResult;
import me.xbt.stellarj.stellard.rpc.result.AccountOffersResult;
import me.xbt.stellarj.stellard.rpc.result.StellarResultContainer;

import org.json.JSONArray;
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
		String sJsonResult = client.sendCommand("account_offers", param);
		
		// convert result manually because taker_gets can be string or object.
		// eg.		
//		{
//			  "result": {
//			    "account": "ganVp9o5emfzpwrG5QVUXqMv8AgLcdvySb",
//			    "offers": [
//			      {
//			        "flags": 0,
//			        "seq": 40,
//			        "taker_gets": "600000000",
//			        "taker_pays": {
//			          "currency": "DBG",
//			          "issuer": "g4cHTkj4YQmMJhrJFCKL1g9rNf4yJRpruP",
//			          "value": "55"
//			        }
//			      }
//			    ],
//			    "status": "success"
//			  }
//			}
		AccountOffersResult aor = new AccountOffersResult();
		JSONObject json = new JSONObject(sJsonResult);
		JSONObject resultJson = json.getJSONObject("result");
		if (resultJson != null) {
			aor.setStatus(resultJson.getString("status"));
			aor.setAccount(resultJson.getString("account"));
			JSONArray offersJson = resultJson.getJSONArray("offers");
			List<AccountOffersResult.Offer> offers = new ArrayList<AccountOffersResult.Offer>();
			if (offersJson != null) {
				for (int i=0; i<offersJson.length(); i++) {
					JSONObject offerJson = offersJson.getJSONObject(i);
					AccountOffersResult.Offer offer = new AccountOffersResult.Offer();
					offer.setFlags(offerJson.getInt("flags"));
					offer.setSeq(offerJson.getInt("seq"));
					
					Object takerGetsObj = offerJson.get("taker_gets");
					offer.setTaker_gets(toCurrencyAmount(takerGetsObj));
					Object takerPaysObj = offerJson.get("taker_pays");
					offer.setTaker_pays(toCurrencyAmount(takerPaysObj));
					
					offers.add(offer);
				}
			}
			aor.setOffers(offers);
		}
		
		return aor;
	}
	
	/**
	 * convert a string or jsonobject to CurrencyAmount
	 * 
	 * @param obj - could be either a String or JSONObject
	 * @return
	 */
	private AccountOffersResult.CurrencyAmount toCurrencyAmount(Object obj) {
		AccountOffersResult.CurrencyAmount ca = new AccountOffersResult.CurrencyAmount();
		if (obj instanceof String) {
			ca.setValue((String)obj);
		} else if (obj instanceof JSONObject) {
			JSONObject json = ((JSONObject) obj);
			ca.setCurrency(json.getString("currency"));
			ca.setIssuer(json.getString("issuer"));
			ca.setValue(json.getString("value"));
		}
		
		return ca;
	}
}

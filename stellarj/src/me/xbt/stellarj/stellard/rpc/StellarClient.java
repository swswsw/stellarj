package me.xbt.stellarj.stellard.rpc;

import java.io.IOException;

import org.json.JSONObject;

import flexjson.JSONDeserializer;
import me.xbt.stellarj.stellard.rpc.result.AccountCurrenciesResult;

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
		AccountCurrenciesResult result = new JSONDeserializer<AccountCurrenciesResult>().deserialize(jsonResult, AccountCurrenciesResult.class);
		return result;
	}
}

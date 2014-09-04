package me.xbt.stellarj.stellard.rpc;

import java.util.ArrayList;

import me.xbt.stellarj.stellard.rpc.result.AccountCurrenciesResult;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * 
 * @author kaye wu
 *
 */
public class StellarClientTest {

	public static void main(String[] args) throws Exception {
		AccountCurrenciesResult acr = new AccountCurrenciesResult();
		System.out.println(new JSONSerializer().include("receiveCurrencies").include("sendCurrencies").serialize(acr));
		
		acr = new JSONDeserializer<AccountCurrenciesResult>()
				//.include("receiveCurrencies").include("sendCurrencies")
				.use("receiveCurrencies", ArrayList.class)
				//.use("receiveCurrencies.members", ArrayList.class)
				.use("receiveCurrencies.members.values", String.class)
				.deserialize("{ \"ledger_current_index\" : 163312,      \"receive_currencies\" : [\"USD\"],      \"send_currencies\" : [],      \"status\" : \"success\"   }", AccountCurrenciesResult.class);
		System.out.println(acr);
		
		String url = "https://test.stellar.org:9002";
		StellarClient client = new StellarClient(url);
		String account = "gM4Fpv2QuHY4knJsQyYGKEHFGw3eMBwc1U";
		LedgerIndex ledgerIndex = new LedgerIndex();
		ledgerIndex.setKeyword(LedgerIndex.Keyword.current);
		String ledgerHash = null;
		
		acr = client.accountCurrencies(account, ledgerIndex, ledgerHash);
		System.out.println("AccountCurrenciesResult=" + acr);
	}
}

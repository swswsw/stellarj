package me.xbt.stellarj.stellard.rpc;

import java.io.IOException;
import java.util.ArrayList;

import me.xbt.stellarj.stellard.rpc.result.AccountCurrenciesResult;
import me.xbt.stellarj.stellard.rpc.result.AccountInfoResult;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * 
 * @author kaye wu
 *
 */
public class StellarClientTest {

	private static String url = "https://test.stellar.org:9002";
	private static StellarClient client = new StellarClient(url);
	
	public static void main(String[] args) throws Exception {
		testAccountCurrencies();
		testAccountInfo();
	}
	
	private static void testAccountCurrencies() throws IOException {
		String account = "gM4Fpv2QuHY4knJsQyYGKEHFGw3eMBwc1U";
		LedgerIndex ledgerIndex = new LedgerIndex();
		ledgerIndex.setKeyword(LedgerIndex.Keyword.current);
		String ledgerHash = null;
		
		AccountCurrenciesResult result = client.accountCurrencies(account, ledgerIndex, ledgerHash);
		System.out.println("AccountCurrenciesResult=" + result);
		
		if (!"success".equals(result.getStatus())) {
			System.out.println("testAccountCurrencies failed");
		}
	}
	
	private static void testAccountInfo() throws IOException {
		String account = "ganVp9o5emfzpwrG5QVUXqMv8AgLcdvySb";
		
		AccountInfoResult result = client.accountInfo(account);
		System.out.println("AccountInfoResult=" + result);
		
		if (!"success".equals(result.getStatus())) {
			System.out.println("testAccountInfo failed");
		}
	}
	
	private static void tempTest() {
		AccountCurrenciesResult acr = new AccountCurrenciesResult();
		System.out.println(new JSONSerializer().include("receiveCurrencies").include("sendCurrencies").serialize(acr));
		
		acr = new JSONDeserializer<AccountCurrenciesResult>()
				//.include("receiveCurrencies").include("sendCurrencies")
				.use("receiveCurrencies", ArrayList.class)
				//.use("receiveCurrencies.members", ArrayList.class)
				.use("receiveCurrencies.members.values", String.class)
				.deserialize("{ \"ledger_current_index\" : 163312,      \"receive_currencies\" : [\"USD\"],      \"send_currencies\" : [],      \"status\" : \"success\"   }", AccountCurrenciesResult.class);
		System.out.println(acr);
	}
}

package me.xbt.stellarj.stellard.rpc;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONObject;

import me.xbt.stellarj.stellard.rpc.result.AccountCurrenciesResult;
import me.xbt.stellarj.stellard.rpc.result.AccountInfoResult;
import me.xbt.stellarj.stellard.rpc.result.AccountLinesResult;
import me.xbt.stellarj.stellard.rpc.result.AccountOffersResult;
import me.xbt.stellarj.stellard.rpc.result.AccountTxResult;
import me.xbt.stellarj.stellard.rpc.result.BookOffersResult;
import me.xbt.stellarj.stellard.rpc.result.CreateKeysResult;
import me.xbt.stellarj.stellard.rpc.result.LedgerResult;
import me.xbt.stellarj.stellard.rpc.result.PingResult;
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
		testAccountLines();
		testAccountOffers();
		testAccountTx();
		testCreateKeys();
		testLedger();
		testPing();
	}
	
	private static void testAccountCurrencies() throws IOException {
		String account = "gM4Fpv2QuHY4knJsQyYGKEHFGw3eMBwc1U";
		LedgerIndex ledgerIndex = new LedgerIndex();
		ledgerIndex.setKeyword(LedgerIndex.Keyword.current);
		String ledgerHash = null;
		
		AccountCurrenciesResult result = client.accountCurrencies(account, ledgerIndex, ledgerHash);
		System.out.println("AccountCurrenciesResult=" + result);
		
		if (!"success".equals(result.getStatus())) {
			System.err.println("testAccountCurrencies failed");
		}
	}
	
	private static void testAccountInfo() throws IOException {
		String account = "ganVp9o5emfzpwrG5QVUXqMv8AgLcdvySb";
		
		AccountInfoResult result = client.accountInfo(account);
		System.out.println("AccountInfoResult=" + result);
		
		if (!"success".equals(result.getStatus())) {
			System.err.println("testAccountInfo failed");
		}
	}
	
	private static void testAccountLines() throws IOException {
		String account = "ganVp9o5emfzpwrG5QVUXqMv8AgLcdvySb";
		String peer = null;
		LedgerIndex ledgerIndex = null;
		String ledgerHash = null;
		
		AccountLinesResult result = client.accountLines(account, peer, ledgerIndex, ledgerHash);
		System.out.println("AccountLinesResult=" + result);
		
		if (!"success".equals(result.getStatus())) {
			System.err.println("testAccountLines failed");
		}
	}
	
	private static void testAccountOffers() throws IOException {
		String account = "ganVp9o5emfzpwrG5QVUXqMv8AgLcdvySb";
		String peer = null;
		LedgerIndex ledgerIndex = null;
		String ledgerHash = null;
		
		AccountOffersResult result = client.accountOffers(account, peer, ledgerIndex, ledgerHash);
		System.out.println("AccountOffersResult=" + result);
		
		if (!"success".equals(result.getStatus())) {
			System.err.println("testAccountOffers failed");
		}
	}
	
	private static void testAccountTx() throws IOException {
		String account = "ganVp9o5emfzpwrG5QVUXqMv8AgLcdvySb";
		long ledgerIndexMin = 0;
		Long ledgerIndexMax = null;
		LedgerIndex ledgerIndex = null;
		String ledgerHash = null;
		Boolean binary = null;
		Boolean forward = null;
		long limit = 4;
		JSONObject marker = null;
		
		AccountTxResult result = client.accountTx(account, ledgerIndexMin, ledgerIndexMax, ledgerIndex, ledgerHash, binary, forward, limit, marker);
		System.out.println("AccountTxResult=" + result);
		
		if (!"success".equals(result.getStatus())) {
			System.err.println("testAccountTx failed");
		}
	}
	
	private static void testBookOffers() throws IOException {
		String taker = null;
		Amount takerPays = null;
		Amount takerGets = null;
		
		BookOffersResult result = client.bookOffers(taker, takerGets, takerPays);
		System.out.println("BookOffersResult=" + result);
		
		if (!"success".equals(result.getStatus())) {
			System.err.println("testBookOffers failed");
		}
	}
	
	private static void testCreateKeys() throws IOException {
		String passphrase = null;
		
		CreateKeysResult result = client.createKeys(passphrase);
		System.out.println("CreateKeysResult=" + result);
		
		if (!"success".equals(result.getStatus())) {
			System.err.println("testCreateKeys failed");
		}
	}
	
	private static void testLedger() throws IOException {
		Boolean accounts = null;
		Boolean transactions = null;
		Boolean full = null;
		Boolean expand = null;
		String ledgerHash = null;
		LedgerIndex ledgerIndex = null;
		
		LedgerResult result = client.ledger(accounts, transactions, full, expand, ledgerHash, ledgerIndex);
		System.out.println("LedgerResult=" + result);
		
		if (!"success".equals(result.getStatus())) {
			System.err.println("testLedger failed");
		}
	}
	
	private static void testPing() throws IOException {
		PingResult result = client.ping();
		System.out.println("PingResult=" + result);
		
		if (!"success".equals(result.getStatus())) {
			System.err.println("testPing failed");
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

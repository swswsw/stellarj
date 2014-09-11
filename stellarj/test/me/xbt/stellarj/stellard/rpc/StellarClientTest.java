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
import me.xbt.stellarj.stellard.rpc.result.SignResult;
import me.xbt.stellarj.stellard.rpc.result.StaticPathFindResult;
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
		testBookOffers();
		testCreateKeys();
		testLedger();
		testPing();
		testSign();
		testStaticPathFind();
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
		Amount takerPays = new Amount();
		Amount takerGets = new Amount();
		
		takerPays.setCurrency("STR");
		
		takerGets.setCurrency("USD");
		takerGets.setIssuer("ganVp9o5emfzpwrG5QVUXqMv8AgLcdvySb");
		
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
	
	private static void testSign() throws IOException {
//		eg. 
//		"tx_json" : {
//	      "TransactionType": "Payment",
//	      "Account":"ganVp9o5emfzpwrG5QVUXqMv8AgLcdvySb",
//	      "Destination": "gHJPw9kW8v4BsUyDnBR8ZHWo8aEkhUMeAq",
//	      "Amount": {
//	        "currency": "USD",
//	        "issuer": "ghj4kXtHfQcCaLQwpLJ11q2hq6248R7k9C",
//	        "value": 10
//	      }
//	    }
		
		JSONObject txJson = new JSONObject();
		txJson.put("TransactionType", "Payment");
		txJson.put("Account", "ganVp9o5emfzpwrG5QVUXqMv8AgLcdvySb");
		txJson.put("Destination", "gHJPw9kW8v4BsUyDnBR8ZHWo8aEkhUMeAq");
		Amount amount = new Amount();
		amount.setCurrency("USD");
		amount.setIssuer("ghj4kXtHfQcCaLQwpLJ11q2hq6248R7k9C");
		amount.setValue("10"); // value should be string.
		txJson.put("Amount", amount.toJSONObject());
		
		String secret = "s3q5ZGX2ScQK2rJ4JATp7rND6X5npG3De8jMbB7tuvm2HAVHcCN";
		Boolean offline = null;
		
		SignResult result = client.sign(txJson, secret, offline);
		System.out.println("SignResult=" + result);
		
		if (!"success".equals(result.getStatus())) {
			System.err.println("testSign failed");
		}
	}
	
	private static void testStaticPathFind() throws IOException {
		String sourceAccount = "ganVp9o5emfzpwrG5QVUXqMv8AgLcdvySb";
		String destinationAccount = "gBV8kvK1rkFPmNRFzFkipHy2cZAeUf6RPz";
		Amount destinationAmount = new Amount();
		destinationAmount.setCurrency("USD");
		destinationAmount.setValue("50");
		destinationAmount.setIssuer("gBV8kvK1rkFPmNRFzFkipHy2cZAeUf6RPz");
		
		StaticPathFindResult result = client.staticPathFind(sourceAccount, destinationAccount, destinationAmount);
		System.out.println("StaticPathFindResult=" + result);
		
		if (!"success".equals(result.getStatus())) {
			System.err.println("testStaticPathFind failed");
		}
	}
	
	
//	private static void tempTest() {
//		AccountCurrenciesResult acr = new AccountCurrenciesResult();
//		System.out.println(new JSONSerializer().include("receiveCurrencies").include("sendCurrencies").serialize(acr));
//		
//		acr = new JSONDeserializer<AccountCurrenciesResult>()
//				//.include("receiveCurrencies").include("sendCurrencies")
//				.use("receiveCurrencies", ArrayList.class)
//				//.use("receiveCurrencies.members", ArrayList.class)
//				.use("receiveCurrencies.members.values", String.class)
//				.deserialize("{ \"ledger_current_index\" : 163312,      \"receive_currencies\" : [\"USD\"],      \"send_currencies\" : [],      \"status\" : \"success\"   }", AccountCurrenciesResult.class);
//		System.out.println(acr);
//	}
}

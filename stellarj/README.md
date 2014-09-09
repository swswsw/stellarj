Java Stellar Library

still very early stage of development.


Usage Example

		String url = "https://test.stellar.org:9002";
		StellarClient client = new StellarClient(url);
		
		String account = "ganVp9o5emfzpwrG5QVUXqMv8AgLcdvySb";
		AccountInfoResult result = client.accountInfo(account);
		System.out.println("AccountInfoResult=" + result);
		result.getStatus();





questions

i now have a decision i'm trying to decide.

each method will return a java object.  eg. account_currencies method will return AccountCurrenciesResult object.  which represents the json result.  ie. 

  {
    "ledger_index": 400,
    "receive_currencies": [],
    "send_currencies": [],
    "status": "success"
  }


Should AccountCurrenciesResult class has member name as receiveCurrencies (java convention) or receive_currencies (same as json member name)?

if i use the same name as json member name, it makes it easier to use jsonflex library to automatically convert json to java object.


second question?

should i just use the underscore (_) name for all the method parameters (same as the json name) or use camel case (java convention)?

eg. 

StellarClient.accountCurrencies(account, ledgerIndex, ledgerHash)

or

StellarClient.account_currencies(account, ledger_index, ledger_hash)


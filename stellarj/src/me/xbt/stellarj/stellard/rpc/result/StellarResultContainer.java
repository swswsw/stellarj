package me.xbt.stellarj.stellard.rpc.result;

/**
 * the reason to have container is this.
 * 
 * the response json looks like this.
 * 
 * 
{
  "result": {
    "ledger_index": 400,
    "receive_currencies": [],
    "send_currencies": [],
    "status": "success"
  }
}
 * 
 * but the AccountCurrenciesResult only covers:  
  {
    "ledger_index": 400,
    "receive_currencies": [],
    "send_currencies": [],
    "status": "success"
  }
 * 
 * we need a container to contain that object 
 * so flexjson can deserialize.  
 * 
 * @author kaye wu
 *
 */
public class StellarResultContainer {

	private StellarResult result;

	public StellarResult getResult() {
		return result;
	}

	public void setResult(StellarResult result) {
		this.result = result;
	}
	
	
}

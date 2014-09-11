package me.xbt.stellarj.stellard.rpc.result;

import org.json.JSONObject;

/**
 * Note: the json object of "result" is the transaction object.  
 * the format is a little bit different from the other result.  

eg.
{
  "result": {
    "Account": "ganVp9o5emfzpwrG5QVUXqMv8AgLcdvySb",
    "Amount": "250000000000000",
    "Destination": "gBV8kvK1rkFPmNRFzFkipHy2cZAeUf6RPz",
    "Fee": "10",
    "Flags": 2147483648,
    "Sequence": 90,
    "SigningPubKey": "BE3900393891A2A2244E28A82C43BA94CA94DD6BFE36D523576A22BFF86055D4",
    "TransactionType": "Payment",
    "TxnSignature": "770A43046ABD5CA637DE6BD35BBCFB41637519729DEBAF38E988E0EA982D0D04389666B137F107A1734785B2AB99C190318D5E1E478AC0A594CDB279CA1EAA0E",
    "hash": "002F9E7ACA1F5A00CE8288B2E07DB3A363F37D9BD12B4E71CAAFC0F7BC9746AC",
    "inLedger": 18518,
    "ledger_index": 18518,
    "meta": {
      "AffectedNodes": [
        {
          "ModifiedNode": {
            "FinalFields": {
              "Account": "gBV8kvK1rkFPmNRFzFkipHy2cZAeUf6RPz",
              "Balance": "999999999999980",
              "Flags": 0,
              "OwnerCount": 1,
              "Sequence": 3
            },
            "LedgerEntryType": "AccountRoot",
            "LedgerIndex": "36550CC12CE7B867B365C97CB4AADDB4E45CBFB45F2C8DAD105F96A3296D7CD1",
            "PreviousFields": {
              "Balance": "749999999999980"
            },
            "PreviousTxnID": "112BCC200684D488EA785CB05BBB8D7B0DBEBFC3E06A24661B24C0C6C29DFEDE",
            "PreviousTxnLgrSeq": 14058
          }
        },
        {
          "ModifiedNode": {
            "FinalFields": {
              "Account": "ganVp9o5emfzpwrG5QVUXqMv8AgLcdvySb",
              "Balance": "97249841707998065",
              "Flags": 0,
              "OwnerCount": 10,
              "Sequence": 91
            },
            "LedgerEntryType": "AccountRoot",
            "LedgerIndex": "6047FB9C7976F2D0554618F5ABFF423E7136205BAF19E92BE9D295E549442C45",
            "PreviousFields": {
              "Balance": "97499841707998075",
              "Sequence": 90
            },
            "PreviousTxnID": "7F8FF30540D6D6A02B1AC18717A5806586B3AC6D6EBC9DC04E1CEB342DCD40DB",
            "PreviousTxnLgrSeq": 17916
          }
        }
      ],
      "TransactionIndex": 0,
      "TransactionResult": "tesSUCCESS"
    },
    "status": "success",
    "validated": true
  }
}

 * @author kaye wu
 *
 */
public class TxResult extends StellarResult {
	private JSONObject result = null;
	
	/**
	 * convert json string to representation of this object.  
	 * @param jsonString
	 */
	public static TxResult convert(String jsonString) {
		TxResult tr = new TxResult();
		JSONObject json = new JSONObject(jsonString);
		JSONObject resultJson = json.getJSONObject("result");
		if (resultJson != null) {
			StellarResult.convert(jsonString, tr);
			tr.setResult(resultJson);
		}
		
		return tr;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
	
	
}

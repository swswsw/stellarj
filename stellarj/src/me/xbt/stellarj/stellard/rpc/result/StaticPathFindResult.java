package me.xbt.stellarj.stellard.rpc.result;

import java.util.ArrayList;
import java.util.List;

import me.xbt.stellarj.stellard.rpc.Amount;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * decided not to implement this class yet because stellar doc has not yet clearly  
 * detail response format.  there is no static_path_find in ripple doc.  
 * https://www.stellar.org/api/#api-static_path_find

eg.
{
  "result": {
    "alternatives": [
      {
        "paths_computed": [
          [
            {
              "account": "ghj4kXtHfQcCaLQwpLJ11q2hq6248R7k9C",
              "type": 1
            }
          ]
        ],
        "source_amount": {
          "currency": "USD",
          "issuer": "ganVp9o5emfzpwrG5QVUXqMv8AgLcdvySb",
          "value": "50"
        }
      },
      {
        "paths_computed": [
          [
            {
              "currency": "USD",
              "issuer": "ghj4kXtHfQcCaLQwpLJ11q2hq6248R7k9C",
              "type": 48
            },
            {
              "account": "ghj4kXtHfQcCaLQwpLJ11q2hq6248R7k9C",
              "type": 1
            }
          ]
        ],
        "source_amount": "5000"
      }
    ],
    "destination_account": "gBV8kvK1rkFPmNRFzFkipHy2cZAeUf6RPz",
    "destination_currencies": [
      "USD",
      "STR"
    ],
    "status": "success"
  }
}

 * @author 
 *
 */
public class StaticPathFindResult extends StellarResult {

	private List<Alternative> alternatives = new ArrayList<Alternative>();
	private String destination_account = null;
	private List<String> destination_currencies = new ArrayList<String>();
	
	
	
	public List<Alternative> getAlternatives() {
		return alternatives;
	}
	public void setAlternatives(List<Alternative> alternatives) {
		this.alternatives = alternatives;
	}
	public String getDestination_account() {
		return destination_account;
	}
	public void setDestination_account(String destination_account) {
		this.destination_account = destination_account;
	}
	public List<String> getDestination_currencies() {
		return destination_currencies;
	}
	public void setDestination_currencies(List<String> destination_currencies) {
		this.destination_currencies = destination_currencies;
	}


	/**
	 * convert the json string into an instance of this object.  
	 * convert manually instead of using flexjson because alternative.paths_computed is an array of objects and that objects can be different format.  
	 * 
	 * @param jsonString
	 * @return
	 */
	public static StaticPathFindResult convert(String jsonString) {
		// eg.		
//		{
//			  "result": {
//			    "alternatives": [
//			      {
//			        "paths_computed": [
//			          [
//			            {
//			              "account": "ghj4kXtHfQcCaLQwpLJ11q2hq6248R7k9C",
//			              "type": 1
//			            }
//			          ]
//			        ],
//			        "source_amount": {
//			          "currency": "USD",
//			          "issuer": "ganVp9o5emfzpwrG5QVUXqMv8AgLcdvySb",
//			          "value": "50"
//			        }
//			      },
//			      {
//			        "paths_computed": [
//			          [
//			            {
//			              "currency": "USD",
//			              "issuer": "ghj4kXtHfQcCaLQwpLJ11q2hq6248R7k9C",
//			              "type": 48
//			            },
//			            {
//			              "account": "ghj4kXtHfQcCaLQwpLJ11q2hq6248R7k9C",
//			              "type": 1
//			            }
//			          ]
//			        ],
//			        "source_amount": "5000"
//			      }
//			    ],
//			    "destination_account": "gBV8kvK1rkFPmNRFzFkipHy2cZAeUf6RPz",
//			    "destination_currencies": [
//			      "USD",
//			      "STR"
//			    ],
//			    "status": "success"
//			  }
//			}
		StaticPathFindResult spfr = new StaticPathFindResult();
		JSONObject json = new JSONObject(jsonString);
		JSONObject resultJson = json.getJSONObject("result");
		if (resultJson != null) {
			StellarResult.convert(jsonString, spfr);
			
			spfr.setDestination_account(resultJson.getString("destination_account"));
			JSONArray destinationCurrenciesJson = resultJson.getJSONArray("destination_currencies");
			for (int i=0; i<destinationCurrenciesJson.length(); i++) {
				String destinationCurrency = destinationCurrenciesJson.getString(i);
				spfr.getDestination_currencies().add(destinationCurrency);
			}
			
			// convert alternatives
			List<Alternative> alternatives = new ArrayList<Alternative>();
			JSONArray alternativesJson = resultJson.getJSONArray("alternatives");
			for (int i=0; i<alternativesJson.length(); i++) {
				Alternative alternative = new Alternative();
				JSONObject alternativeJson = alternativesJson.getJSONObject(i);
				JSONArray pathsComputedJson = alternativeJson.getJSONArray("paths_computed");
				
				List<JSONObject> pathsComputed = new ArrayList<JSONObject>();
				for (int j=0; j<pathsComputedJson.length(); j++) {
					pathsComputed.add(pathsComputedJson.getJSONObject(j));
				}
				
				alternative.setPaths_computed(pathsComputed);
				
				// source_amount can be a string or object.  
				Amount sourceAmount = new Amount();
				Object sourceAmountObj = alternativeJson.get("source_amount");
				if (sourceAmountObj instanceof String) {
					sourceAmount.setValue((String)sourceAmountObj);
				} else {
					JSONObject sourceAmountJsonObj = (JSONObject)sourceAmountObj;
					sourceAmount.setCurrency(sourceAmountJsonObj.getString("currency"));
					sourceAmount.setIssuer(sourceAmountJsonObj.getString("issuer"));
					sourceAmount.setValue(sourceAmountJsonObj.getString("value"));
				}
				
				alternative.setSource_amount(sourceAmount);
				
				alternatives.add(alternative);
			}
			
			spfr.setAlternatives(alternatives);
			
		}
		
		return spfr;
	}
	

	public static class Alternative {
		/** 
		 * path format is not very clear from documentation.  
		 * thus, we just put it as a list of JsonObject
		 */
		private List<JSONObject> paths_computed = new ArrayList<JSONObject>();
		private Amount source_amount = null;
		public List<JSONObject> getPaths_computed() {
			return paths_computed;
		}
		public void setPaths_computed(List<JSONObject> paths_computed) {
			this.paths_computed = paths_computed;
		}
		public Amount getSource_amount() {
			return source_amount;
		}
		public void setSource_amount(Amount source_amount) {
			this.source_amount = source_amount;
		}
		
		
	}
}

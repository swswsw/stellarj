package me.xbt.stellarj.stellard.rpc.result;

/**
 * @see {@link https://www.stellar.org/api/#api-create_keys}

account_id	

The generated accountID. It will become a usable stellar account if someone sends stellars to it.

master_seed	

The secret key in base58 format that corresponds to the accountID.

master_seed_hex	

The secret key in hex format that corresponds to the accountID.

public_key	

The public key in base58 format that corresponds to the accountID.

public_key_hex	

The public key in hex format that corresponds to the accountID.

eg.
  {
    "account_id": "gJANzBNatocYh3oGexsNfTXghp1RjpxPke",
    "master_seed": "sfS9WpJyuCutGQ8vQka1cJUWPTvwRNPx2fint78NUgrvYtfbpvd",
    "master_seed_hex": "25A600C2157A748A0363653959CD6578AA3F75531C5F0D4433CA4EE1CECFCB20",
    "public_key": "pGCt1LVU8HCuqsDSMWyM6mCYE2JhDd5SGZ4FTsHAoPs9mdmE4wy",
    "public_key_hex": "BC80B0732C99DB85A9705A1EBE20E789816CDC94079C422696EBFA6C06833160",
    "status": "success"
  }
 *
 * @author kaye wu
 *
 */
public class CreateKeysResult extends StellarResult {
	
	private String account_id = null;
	private String master_seed = null;
	private String master_seed_hex = null;
	private String public_key = null;
	private String public_key_hex = null;
	
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public String getMaster_seed() {
		return master_seed;
	}
	public void setMaster_seed(String master_seed) {
		this.master_seed = master_seed;
	}
	public String getMaster_seed_hex() {
		return master_seed_hex;
	}
	public void setMaster_seed_hex(String master_seed_hex) {
		this.master_seed_hex = master_seed_hex;
	}
	public String getPublic_key() {
		return public_key;
	}
	public void setPublic_key(String public_key) {
		this.public_key = public_key;
	}
	public String getPublic_key_hex() {
		return public_key_hex;
	}
	public void setPublic_key_hex(String public_key_hex) {
		this.public_key_hex = public_key_hex;
	}

	
}

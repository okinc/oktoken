package com.oklink.bitcoinj.script;

import static org.bitcoinj.script.ScriptOpCodes.*;

import java.util.List;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.ScriptException;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptChunk;

public class OKScript extends Script {

	public OKScript(byte[] programBytes, long creationTimeSeconds) throws ScriptException {
		super(programBytes, creationTimeSeconds);
	}

	public OKScript(byte[] programBytes) throws ScriptException {
		super(programBytes);
	}

	public OKScript(List<ScriptChunk> chunks) {
		super(chunks);
	}

	private boolean isSuperPayScript(){
			//chunkssize:13 p2sh, 14->p2pkh, 11->p2rawpk
		return (chunks.size() == 13 || chunks.size() == 14 || chunks.size() == 11) &&
				(chunks.get(4).equalsOpCode(OP_IF) &&
				chunks.get(5).equalsOpCode(OP_TRUE) &&
				chunks.get(6).equalsOpCode(OP_ELSE));
	}
	
	@Override
	public boolean isSentToAddress() {
		boolean isStandardSentToAddr = super.isSentToAddress();
		boolean isSuperSentToAddr = (isSuperPayScript() &&
				 chunks.get(8).equalsOpCode(OP_DUP) &&
	             chunks.get(9).equalsOpCode(OP_HASH160) &&
	             chunks.get(10).data.length == Address.LENGTH &&
	             chunks.get(11).equalsOpCode(OP_EQUALVERIFY) &&
	             chunks.get(12).equalsOpCode(OP_CHECKSIG));
		
		return (isStandardSentToAddr || isSuperSentToAddr);
	}

	@Override
	public boolean isPayToScriptHash() {
		boolean isSandardPayToScriptHash = super.isPayToScriptHash();
		boolean isSuperPayScriptHash = (isSuperPayScript() &&
				chunks.get(8).equalsOpCode(OP_DUP) &&
				chunks.get(9).equalsOpCode(OP_HASH160) &&
				chunks.get(10).data.length == Address.LENGTH &&
				chunks.get(11).equalsOpCode(OP_EQUAL));
		
		return (isSandardPayToScriptHash || isSuperPayScriptHash);
		
	}

	@Override
	@Deprecated
	public boolean isSentToP2SH() {
		return isPayToScriptHash();
	}

	@Override
	public boolean isSentToRawPubKey() {
		boolean isStandardSentToRawPubKey = super.isSentToRawPubKey();
		boolean isSuperSentToRawPubKey = (isSuperPayScript() &&
				chunks.get(9).equalsOpCode(OP_CHECKSIG) &&
	               !chunks.get(8).isOpCode() && chunks.get(8).data.length > 1);
		
		return isStandardSentToRawPubKey || isSuperSentToRawPubKey;
	}

	@Override
	public byte[] getPubKeyHash() throws ScriptException {
		if(isSuperPayScript()){
			if(isSentToAddress() || isPayToScriptHash()){
				return chunks.get(10).data;
			}
		}else{
			if (isSentToAddress())
	            return chunks.get(2).data;
	        else if (isPayToScriptHash())
	            return chunks.get(1).data;
		}
		
       throw new ScriptException("Script not in the standard scriptPubKey form");
	}

	@Override
	public byte[] getPubKey() throws ScriptException {
		//chunkssize = 2 标准； chunkssize=11 super script
		if (chunks.size() == 2) {
            return super.getPubKey();
        }else if(chunks.size() == 11){
        	 final ScriptChunk chunk0 = chunks.get(8);
             final byte[] chunk0data = chunk0.data;
             final ScriptChunk chunk1 = chunks.get(9);
//             final byte[] chunk1data = chunk1.data;
             if (chunk1.equalsOpCode(OP_CHECKSIG) && chunk0data != null && chunk0data.length > 2) {
                 // A large constant followed by an OP_CHECKSIG is the key.
                 return chunk0data;
             } else {
                 throw new ScriptException("Script did not match expected form: " + this);
             }
        }else{
        	throw new ScriptException("Script not of right size, expecting 2 but got " + chunks.size());
        }
	}
	
	
	
	

}

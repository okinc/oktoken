package com.oklink.bitcoinj.testor;

import org.bitcoinj.core.Coin;
import org.bitcoinj.core.Context;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.TransactionInput;
import org.bitcoinj.core.TransactionOutPoint;
import org.bitcoinj.core.TransactionOutput;
import org.bitcoinj.core.Utils;
import org.bitcoinj.script.ScriptBuilder;
import org.junit.Before;
import org.junit.Test;
import com.oklink.bitcoinj.core.*;
import com.oklink.bitcoinj.params.*;
import com.oklink.bitcoinj.script.*;
import com.subgraph.orchid.encoders.Hex;

public class OKBlockTest {
	
	@Before
    public void setUp() throws Exception {
//		NetworkParameters params = OKTestNetParams.get();
//        Context context = new Context(params);
    }
	
	@Test
	public void testCreateGenesisBlock(){
		NetworkParameters params = OKTestNetParams.get();
		//发行1000 Token
		OKTransaction coinbaseTx = new OKTransaction(params);
		
		final String issueByOKLink = "OKToken Genesis Block issued by OKLink";
		final ScriptBuilder inputBuilder = new OKScriptBuilder();
		final long genesisHeight = 0L;
	    inputBuilder.number(genesisHeight);
       inputBuilder.data(issueByOKLink.getBytes());
	    OKTransactionInput input = new OKTransactionInput(params, null, inputBuilder.build().getProgram());
		coinbaseTx.addInput(input);
		TransactionOutput output = new TransactionOutput(params, coinbaseTx, Coin.COIN.multiply(1000), OKTestNetParams.genesisCoinbaseECKey.toAddress(params));
		coinbaseTx.addOutput(output);
//		coinbaseTx.addOutput(Coin.COIN.multiply(1000), OKTestNetParams.genesisCoinbaseECKey.toAddress(params));
		
		System.out.println(coinbaseTx.getHashAsString());
		
		OKBlock block = new OKBlock(params, 1, Sha256Hash.ZERO_HASH, Sha256Hash.ZERO_HASH, null);
		block.addTransaction(coinbaseTx);
		System.out.println(block.toString());
		System.out.println(Utils.HEX.encode(block.bitcoinSerialize()));
		
	}

}

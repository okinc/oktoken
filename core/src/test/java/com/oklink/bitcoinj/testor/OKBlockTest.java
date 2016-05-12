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
	public void testCreateGenesisBlock0(){
		NetworkParameters params = OKTestNetParams.get();
		//发行1000 Token
		OKTransaction coinbaseTx = new OKTransaction(params);
		
		final String issueByOKLink = "OKToken Genesis Block issued by OKLink";//任意可以写到Coinbase里（不超过100byte）
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
		block.setTime(1461240965L);//测试数据指定时间
		block.addTransaction(coinbaseTx);
		System.out.println(block.toString());
		System.out.println(Utils.HEX.encode(block.bitcoinSerialize()));
		
	}
	
	@Test
	public void testCreateGenesisBlock1(){
		NetworkParameters params = OKTestNetParams.get();
		//发行1000 Token
		OKTransaction coinbaseTx = new OKTransaction(params);
		
		final String issueByOKLink = "OKToken Genesis Block issued by OKLink";//任意可以写到Coinbase里（不超过100byte）
		final ScriptBuilder inputBuilder = new OKScriptBuilder();
		long genesisHeight = 1L;
	    inputBuilder.number(genesisHeight);
	    inputBuilder.data(issueByOKLink.getBytes());
	    OKTransactionInput input = new OKTransactionInput(params, null, inputBuilder.build().getProgram());
		coinbaseTx.addInput(input);
		TransactionOutput output = new TransactionOutput(params, coinbaseTx, Coin.COIN.multiply(100), OKTestNetParams.genesisCoinbaseECKey.toAddress(params));
		coinbaseTx.addOutput(output);
//		coinbaseTx.addOutput(Coin.COIN.multiply(1000), OKTestNetParams.genesisCoinbaseECKey.toAddress(params));
		
		System.out.println(coinbaseTx.getHashAsString());
		System.out.println(Utils.HEX.encode(coinbaseTx.bitcoinSerialize()));
		coinbaseTx.getConfidence().setAppearedAtChainHeight(1);
		System.out.println(coinbaseTx.getConfidence().getAppearedAtChainHeight());
		System.out.println(Utils.HEX.encode(coinbaseTx.bitcoinSerialize()));
		OKBlock block = new OKBlock(params, 1, Sha256Hash.ZERO_HASH, Sha256Hash.ZERO_HASH, null);
		block.setTime(1461240965L);//测试数据指定时间
		block.addTransaction(coinbaseTx);
		System.out.println(block.toString());
		System.out.println(Utils.HEX.encode(block.bitcoinSerialize()));
		
	}
	
	

	@Test
	public void testTx(){
		NetworkParameters params = OKMainNetParams.get();
		final byte[] txData = Utils.HEX.decode("01000000010000000000000000000000000000000000000000000000000000000000000000ffffffff2851264f4b546f6b656e2047656e6573697320426c6f636b20697373756564206279204f4b4c696e6bffffffff0100e87648170000001976a9147346e6ee51b2c9f3c25b9c7fcec8d9227847bfd688ac00000000");
		OKTransaction tx = new OKTransaction(params, txData);
		
	try{
		System.out.println("version:" +tx.getVersion());
        
		System.out.println("locktime:" + tx.getLockTime());
		
		System.out.println("isCoinbase:" + tx.isCoinBase());
		 for(TransactionInput oti:tx.getInputs()){
			if(tx.isCoinBase()){
				System.out.println("input scriptbyte:" +Utils.HEX.encode(oti.getScriptBytes()));
			}else{
				System.out.println(oti.getConnectedTransaction().getHashAsString().toCharArray());
				System.out.println(oti.getConnectedOutput().getIndex());
					
				System.out.println(oti.getScriptSig().getProgram());
				System.out.println(oti.getScriptSig().getPubKeyHash());
			}
			
			
		 }
		 for(TransactionOutput oto:tx.getOutputs()){
			 System.out.println("output value:" + oto.getValue());
			
			 System.out.println("output index:" + oto.getIndex());
			 System.out.println("output script:" + oto.getScriptPubKey().toString());
			 System.out.println("output byte:" +Utils.HEX.encode(oto.getScriptBytes()));
			 System.out.println("output addr:" + oto.getAddressFromP2PKHScript(params));
		
		 }
	}catch(Exception e){
		e.printStackTrace();
	}
//		tx.getConfidence().setAppearedAtChainHeight(1);
		System.out.println(tx.toString());
	}
}

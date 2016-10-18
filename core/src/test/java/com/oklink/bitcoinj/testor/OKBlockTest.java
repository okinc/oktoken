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
	public void testBlock() {
//		byte[] blockData = Utils.HEX.decode("01000000215d89ce84e8dc9d88d422b03cf418e03dab96a4e7a6d102c8b9105d076bc0f60a213ccabdbd2938cea5e2084af6159944cfa97740348f62326fc1efcb92963cb52d1a88225ca8cb0cb680b3f71d44699927112372a162694c425c8ad1b7961cdb8a62570101000000010000000000000000000000000000000000000000000000000000000000000000ffffffff2858264f4b546f6b656e2047656e6573697320426c6f636b20697373756564206279204f4b4c696e6bffffffff01c01f5357020000003576a9141642088a04c842c50cf91813e2aa795b09f54afb8763516776a914b4a319605b5834b8101f7380d88a77649be5409988ac6800000000");
		byte[] blockData = Utils.HEX.decode("01000000a97c84a081eae7f715fda564b7c6f50d9f84aa51ef58bcef7ecfd3bee892a20dfa1eeba0bd8628d9953c278376d526dc2be25643b62d6359d670f288991eac73416111d0b7fef6fad78449bfb62796a7a2213f5b7d3f08e8fbd1bd11e10f22bf9b615e570201000000010000000000000000000000000000000000000000000000000000000000000000ffffffff2854264f4b546f6b656e2047656e6573697320426c6f636b20697373756564206279204f4b4c696e6bffffffff0100000000000000000d6a0b46726f6d204f4b4c696e6b000000000100000001d9743b4290b4dd2984fed865290302a8dacf360c5f9630b2bed1cd3cc75d254a000000006b483045022100f0249db0eb55c5375e5f7b3bc14a43b52b9bcf3c098b10701dd0e85c131cb58802202f5917ac6ddcaeaa06d2512f5346b1ec5ace5da2eda107fd12a85353629e2adb0121024a60a932ac4fb4548b9043604b6e18048b6c3976189c0f170cd70eaaf21888c3ffffffff0200d6117e030000003376a9141642088a04c842c50cf91813e2aa795b09f54afb87635167a91406f2453c9c120c94aaef3461b12bee81aaa6cb958768001265ca130000003576a9141642088a04c842c50cf91813e2aa795b09f54afb8763516776a914954124dbbba6c29d1d5b9a188ed23ebc81afd86788ac6800000000");
		OKTestNetParams params = OKTestNetParams.get();
		OKBlock block =  new OKBlock(params, blockData);
		System.out.println(block.toString());	
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

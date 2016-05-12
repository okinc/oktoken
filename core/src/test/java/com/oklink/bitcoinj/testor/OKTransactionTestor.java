package com.oklink.bitcoinj.testor;

import java.util.Collections;
import java.util.List;


import org.bitcoinj.core.Coin;
import org.bitcoinj.core.Context;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.TransactionOutput;
import org.bitcoinj.core.Utils;
import org.bitcoinj.core.VerificationException;
import org.bitcoinj.core.Transaction.SigHash;
import org.bitcoinj.core.TransactionInput;
import org.bitcoinj.core.TransactionInput.ConnectMode;
import org.bitcoinj.core.TransactionInput.ConnectionResult;
import org.bitcoinj.crypto.TransactionSignature;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;
import org.junit.*;

import com.google.common.collect.ImmutableList;
import com.oklink.bitcoinj.core.OKBlock;
import com.oklink.bitcoinj.core.OKTransaction;
import com.oklink.bitcoinj.core.OKTransactionInput;
import com.oklink.bitcoinj.core.OKTransactionOutput;
import com.oklink.bitcoinj.params.OKTestNetParams;
import com.oklink.bitcoinj.script.OKSuperKey;

public class OKTransactionTestor {
	@Before
	public void setUp(){
		Context context = new Context(OKTestNetParams.get());
	}

	@Test
	public void testCreateTransaction(){
		OKTestNetParams params = OKTestNetParams.get();
		OKBlock genesisBlock = (OKBlock)params.getGenesisBlock();
		List<OKTransaction> txs = genesisBlock.getTransactions();
		OKTransaction parentTx = txs.get(0);
		
		OKTransaction newTx = new OKTransaction(params);
		newTx.addOutput(Coin.COIN.multiply(100), OKTestNetParams.genesisCoinbaseECKey.toAddress(params));
		
		newTx.addSignedInput(parentTx.getOutput(0), OKTestNetParams.genesisCoinbaseECKey);
		System.out.println(newTx.toString());
		System.out.println(Utils.HEX.encode(newTx.bitcoinSerialize()));
		
		
		try{
			newTx.verify();
			newTx.getInput(0).verify();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	static final ECKey eckey = ECKey.fromPrivate(Utils.HEX.decode("559d29f51947b67d9c8940b39ff878815ee9ca2e42d4ad38173613ea47510bfa"));
	static final byte[] txData = Utils.HEX.decode("010000000181785c3da2da181a2466ce5acfa134803ea9ff77c61ed4bbac53189c2d46456b000000006a4730440220391dd337b7a301154adf5bf047e9fb43e7f9d64b43a629ae07c1ac1c30a2187102203a78c37a00234d2eb9f9c8b8868f546580687abd3094bae6b637bf0f559a14f601210323941ae444602bea0dfbf2ddd03df5fcff4b26a46b4742089f4fa3839b4e9f95ffffffff0100e40b54020000003576a914db85fdb4fc34f72a3f47e62f95227c66d221f6748763516776a9148b08ef2a0035a7d8617d869dbf4ad2eb2d1944ff88ac6800000000");
	static final byte[] txTest = Utils.HEX.decode("01000000010000000000000000000000000000000000000000000000000000000000000000ffffffff2852264f4b546f6b656e2047656e6573697320426c6f636b20697373756564206279204f4b4c696e6bffffffff010000000000000000166a1466726f6d20777777772e6f6b6c696e6b2e636f6d00000000");
	OKTestNetParams params = OKTestNetParams.get();

	
	@Test
	public void testCreateTransaction1(){
		OKTestNetParams params = OKTestNetParams.get();
		
		OKTransaction parentTx = new OKTransaction(params, txTest);
		System.out.println(parentTx.toString());
		
		

		
		try{
			parentTx.verify();
			for(TransactionInput input : parentTx.getInputs()){
				OKBlock genesisBlock = (OKBlock)params.getGenesisBlock();
				List<OKTransaction> txs = genesisBlock.getTransactions();
			
				System.out.println(input.getOutpoint().getHash());
				
				if(ConnectionResult.SUCCESS != input.connect(txs.get(0), ConnectMode.ABORT_ON_CONFLICT)){
					//false !! not such input or input had spented
				}
				
				System.out.println(input.getOutpoint().getHash());
				System.out.println(input.getOutpoint().getIndex());
				System.out.println(input.getScriptSig());//asm
				System.out.println(Utils.HEX.encode(input.getScriptBytes()));//hex
				System.out.println(((OKTransactionOutput)parentTx.getOutput(0)).getTargetAddress().toString());
				
				input.verify();
			}
			
		}catch(Exception e){
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		
		OKTransaction newTx = new OKTransaction(params);
		newTx.addOutput(Coin.COIN.multiply(1), OKSuperKey.superKeyAddress(params));
		OKTransactionOutput from = (OKTransactionOutput) parentTx.getOutput(0);
		try{
			System.out.println(OKSuperKey.superKeyAddress(params));
			System.out.println(((OKTransactionOutput)newTx.getOutput(0)).getTargetAddress().toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		newTx.addSignedInput(from, OKTestNetParams.genesisCoinbaseECKey);
		
		
		
		try{
			newTx.verify();
			for(TransactionInput input : newTx.getInputs()){
				System.out.println(input.toString());
				input.verify();
			}
			
		}catch(VerificationException e){
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	
		System.out.println(newTx.toString());
		
	}
	
	@Test
	public void testSuperRedeem(){
		OKTransaction parentTx = new OKTransaction(params, txData);
		
		OKTransaction newTx = new OKTransaction(params);
		newTx.addOutput(Coin.COIN.multiply(20), OKTestNetParams.genesisCoinbaseECKey.toAddress(params));
		TransactionOutput from = parentTx.getOutput(0);
		newTx.addInput(from);
		
		//超级私钥群
		List<ECKey> keys =OKSuperKey.SUPER_KEYS;
		Collections.sort(keys, ECKey.PUBKEY_COMPARATOR);
		Script redeemScript = ScriptBuilder.createRedeemScript(2, OKSuperKey.SUPER_KEYS_PUBLIC_ONLY);
		Sha256Hash sigHash = newTx.hashForSignature(0, redeemScript, SigHash.ALL, false);
		ECKey.ECDSASignature party1Signature = keys.get(0).sign(sigHash);
        ECKey.ECDSASignature party2Signature = keys.get(1).sign(sigHash);
        TransactionSignature party1TransactionSignature = new TransactionSignature(party1Signature, SigHash.ALL, false);
        TransactionSignature party2TransactionSignature = new TransactionSignature(party2Signature, SigHash.ALL, false);
        Script inputScript = ScriptBuilder.createP2SHMultiSigInputScript(ImmutableList.of(party1TransactionSignature,party2TransactionSignature), redeemScript);
        System.out.println(inputScript);
        newTx.getInput(0).setScriptSig(inputScript);
        
        System.out.println(newTx.getFee());
		try{
			newTx.verify();
			newTx.getInput(0).verify();
		}catch(VerificationException e){
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		
		System.out.println(newTx.toString());
	}
	
	@Test
	public void testLocalTx(){
		byte[] txData = Utils.HEX.decode("010000000153176f93a31a75551e9ad33642427fe47e7c367cbb5e65800ee235da3b18502c010000006a47304402203f05d77e4ac73bfba4f7a6549a5de8cdbf23cd82a1dd3c56e826f5a5e618238f02203eff3c93acf9129763d4d1ba2008755ae7aace5ba9efb62f9a0bdfadf4c4e19a0121023d5dd1126f2eac93b01806d6071b2d67fcfd42acfcefd04c6be3d93561935a49ffffffff0100743ba40b0000003576a914444ffc1455e0d86488ed12fbdeaa5b4c8ba073968763516776a914ec40a3c78da3b9fbce250457ceb036884833ef6f88ac6800000000");
		OKTestNetParams params = OKTestNetParams.get();
		OKTransaction tx = new OKTransaction(params, txData);
		System.out.println(tx.toString());
		
		
	}
}

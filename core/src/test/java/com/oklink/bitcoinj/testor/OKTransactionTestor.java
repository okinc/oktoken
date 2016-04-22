package com.oklink.bitcoinj.testor;

import java.util.List;

import org.bitcoinj.core.Coin;
import org.bitcoinj.core.Context;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.TransactionOutput;
import org.bitcoinj.core.Utils;
import org.bitcoinj.core.VerificationException;
import org.bitcoinj.core.Transaction.SigHash;
import org.bitcoinj.crypto.TransactionSignature;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;
import org.junit.*;

import com.google.common.collect.ImmutableList;
import com.oklink.bitcoinj.core.OKBlock;
import com.oklink.bitcoinj.core.OKTransaction;
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
	static final byte[] txData = Utils.HEX.decode("010000000181785c3da2da181a2466ce5acfa134803ea9ff77c61ed4bbac53189c2d46456b000000006a4730440220391dd337b7a301154adf5bf047e9fb43e7f9d64b43a629ae07c1ac1c30a2187102203a78c37a00234d2eb9f9c8b8868f546580687abd3094bae6b637bf0f559a14f601210323941ae444602bea0dfbf2ddd03df5fcff4b26a46b4742089f4fa3839b4e9f95ffffffff0100e40b54020000003576a914db85fdb4fc34f72a3f47e62f95227c66d221f6748763516776a9148b08ef2a0035a7d8617d869dbf4ad2eb2d1944ff88ac6800000000");OKTestNetParams params = OKTestNetParams.get();

	
	@Test
	public void testCreateTransaction1(){
//		OKTestNetParams params = OKTestNetParams.get();
//		
//		OKTransaction parentTx = new OKTransaction(params, txData);
//		
//		OKTransaction newTx = new OKTransaction(params);
//		newTx.addOutput(Coin.COIN.multiply(1), OKTestNetParams.genesisCoinbaseECKey.toAddress(params));
//		TransactionOutput from = parentTx.getOutput(0);
//		newTx.addSignedInput(from, OKTestNetParams.genesisCoinbaseECKey);
//		
//		try{
//			newTx.verify();
//			newTx.getInput(0).verify();
//		}catch(VerificationException e){
//			e.printStackTrace();
//			Assert.assertTrue(false);
//		}
//	
//		System.out.println(newTx.toString());
//		
	}
	
	@Test
	public void testSuperRedeem(){
//		OKTransaction parentTx = new OKTransaction(params, txData);
//		
//		OKTransaction newTx = new OKTransaction(params);
//		newTx.addOutput(Coin.COIN.multiply(1), OKTestNetParams.genesisCoinbaseECKey.toAddress(params));
//		TransactionOutput from = parentTx.getOutput(0);
//		newTx.addInput(from);
//		
//		//超级私钥群
//		List<ECKey> keys =OKSuperKey.SUPER_KEYS;
//		Script redeemScript = ScriptBuilder.createRedeemScript(2, keys);
//		Sha256Hash sigHash = newTx.hashForSignature(0, redeemScript, SigHash.ALL, false);
//		ECKey.ECDSASignature party1Signature = keys.get(0).sign(sigHash);
//        ECKey.ECDSASignature party2Signature = keys.get(1).sign(sigHash);
//        TransactionSignature party1TransactionSignature = new TransactionSignature(party1Signature, SigHash.ALL, false);
//        TransactionSignature party2TransactionSignature = new TransactionSignature(party2Signature, SigHash.ALL, false);
//        Script inputScript = ScriptBuilder.createP2SHMultiSigInputScript(ImmutableList.of(party1TransactionSignature, party2TransactionSignature), redeemScript);
//        newTx.getInput(0).setScriptSig(inputScript);
//        
//		try{
//			newTx.verify();
//			newTx.getInput(0).verify();
//		}catch(VerificationException e){
//			e.printStackTrace();
//			Assert.assertTrue(false);
//		}
//		
//		System.out.println(newTx.toString());
	}
}

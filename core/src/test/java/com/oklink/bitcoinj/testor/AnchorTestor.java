package com.oklink.bitcoinj.testor;

import org.bitcoinj.core.Block;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.Context;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionInput;
import org.bitcoinj.core.TransactionOutPoint;
import org.bitcoinj.core.TransactionOutput;
import org.bitcoinj.core.Utils;
import org.bitcoinj.crypto.TransactionSignature;
import org.bitcoinj.core.Transaction.SigHash;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oklink.bitcoinj.params.OKAbstractNetParams;
import com.oklink.bitcoinj.params.OKTestNetParams;
import com.oklink.bitcoinj.script.OKScriptBuilder;

import static org.bitcoinj.script.ScriptOpCodes.*;

public class AnchorTestor {
	
	private static final Logger log = LoggerFactory.getLogger(AnchorTestor.class);
	private static final NetworkParameters PARAMS = TestNet3Params.get();
	
	
	/**
	 * ECKey_0
   	 *	pri: 559d29f51947b67d9c8940b39ff878815ee9ca2e42d4ad38173613ea47510bfa
   	 *	pub: 03d2ce48857ae96440bd3e56ff31c4c9c7279c3cc61117a7a5db369923d005969a
   	 * 
   	 * 收入https://sandbox.smartbit.com.au/tx/be3dd9770fd040d213cf104e1a7c7aedd5f0fe3499007026620afe0f4a87bf24
   	 * 
   	 * tx：be3dd9770fd040d213cf104e1a7c7aedd5f0fe3499007026620afe0f4a87bf24 n：1
   	 * 0.001btc
   	 * 
   	 * rawtransaction:
   	 * 01000000015a2ad0f8e57c1ac0fb72e966be67eeab350cf34cd46ab7d5bc5355a5a2939870880200006a47304402202a0d78a7d4eb9babfee19494a7fe1b7e3529d1f692317c2d19283f26bc27375c02203e5586deff0e155d63bad455146e1b87968fb0d6ca16e707735613a0219aca95012102f085cdb5a637aae9973fe54a7284b65a237d39b1d6b594a5d61668b98a8a8f15ffffffff025014ea0b000000001976a914bf2f27bfa9b4cc5be97474a52153158a7bd85c1f88aca0860100000000001976a914ccf99ab0c090a86cfa1f60352d363ef297fbe10e88ac00000000
   	 * 
   	 * 
	 */
	private static byte[] privKey = Utils.HEX.decode("559d29f51947b67d9c8940b39ff878815ee9ca2e42d4ad38173613ea47510bfa");
	private static byte[] pubKey = Utils.HEX.decode("03d2ce48857ae96440bd3e56ff31c4c9c7279c3cc61117a7a5db369923d005969a");
	private static String address = "mzCm9zgqhxynoxFtSvkPbsJe4SRwAWb3TT";
	
	private static byte[] parentTxData = Utils.HEX.decode("01000000015a2ad0f8e57c1ac0fb72e966be67eeab350cf34cd46ab7d5bc5355a5a2939870880200006a47304402202a0d78a7d4eb9babfee19494a7fe1b7e3529d1f692317c2d19283f26bc27375c02203e5586deff0e155d63bad455146e1b87968fb0d6ca16e707735613a0219aca95012102f085cdb5a637aae9973fe54a7284b65a237d39b1d6b594a5d61668b98a8a8f15ffffffff025014ea0b000000001976a914bf2f27bfa9b4cc5be97474a52153158a7bd85c1f88aca0860100000000001976a914ccf99ab0c090a86cfa1f60352d363ef297fbe10e88ac00000000");
	
	@Before
	public void setUp(){
		Context context = new Context(PARAMS);
	}
	
	@Test
	public void printlnAddress(){
		log.info("Address: " +  ECKey.fromPublicOnly(pubKey).toAddress(PARAMS).toString());
	}
	
	@Test
	public void createAnchorTransaction(){
		//支付锚定tx来源
		Transaction fromTx = new Transaction(PARAMS, parentTxData);
		System.out.println(fromTx.toString());
		
		//待锚定创世块hash
		final String genesisHash = "be9219c0ddeaacc2a63beb2e3bd85706f2eea1299a1bd199d8568b77ebcf8c15";
		byte[] anchorBlockHash = Utils.HEX.decode(genesisHash);
		Script anchorOpReturn = OKScriptBuilder.createAnchorOpReturn(anchorBlockHash);
		
		Transaction tx = new Transaction(PARAMS);
		TransactionOutput output = new TransactionOutput(PARAMS, null, Coin.ZERO, anchorOpReturn.getProgram());
		tx.addOutput(output);
		tx.addOutput(Coin.parseCoin("0.0005"), ECKey.fromPublicOnly(pubKey).toAddress(PARAMS));
		
		
		TransactionOutPoint outpoint = new TransactionOutPoint(PARAMS, 1, fromTx);
		TransactionInput input = new TransactionInput(PARAMS, tx, new byte[]{}, outpoint);
		tx.addInput(input);
		//构建签名，（只有一一个input）
		ECKey ecKey =  ECKey.fromPrivate(privKey);
		TransactionSignature signature = tx.calculateSignature(0,ecKey, outpoint.getConnectedPubKeyScript(), SigHash.ALL, false);
		System.out.println(ecKey.getPublicKeyAsHex());
		input.setScriptSig(ScriptBuilder.createInputScript(signature, ecKey));
		
		System.out.println(tx.toString());
		System.out.println(Utils.HEX.encode(tx.bitcoinSerialize()));
		
	}
	
}

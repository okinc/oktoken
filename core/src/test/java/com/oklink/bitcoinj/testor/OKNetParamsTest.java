package com.oklink.bitcoinj.testor;


import com.oklink.bitcoinj.core.OKBlock;
import com.oklink.bitcoinj.params.OKMainNetParams;
import com.oklink.bitcoinj.params.OKTestNetParams;
import com.oklink.bitcoinj.params.OKRegTestNetParams;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.Base58;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Utils;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;
import org.junit.Test;
import org.spongycastle.util.encoders.Hex;

public class OKNetParamsTest {

	
	@Test
	public void testMainNetAddress() {
		NetworkParameters params = OKMainNetParams.get();
		
		ECKey eckey = new ECKey();
		Address address = eckey.toAddress(params);
		String addrString = eckey.toAddress(params).toString();
		assertEquals(address.getParameters().getId(), params.getId());
		assertTrue(null, addrString.startsWith("K"));
	}
	
	@Test
	public void testTestNetAddress(){
		NetworkParameters params = OKTestNetParams.get();
		
		ECKey eckey = new ECKey();
		Address address = eckey.toAddress(params);
		String addrString = eckey.toAddress(params).toString();
		assertEquals(address.getParameters().getId(), params.getId());
		assertTrue(null, addrString.startsWith("o"));
	}
	
	@Test 
	public void testRegTestNetAddress(){
		NetworkParameters params = OKRegTestNetParams.get();
		
		ECKey eckey = new ECKey();

		String address = eckey.toAddress(params).toString();
		assertTrue(null, address.startsWith("o"));
	}
	
	
	@Test 
	public void testMulitAddress(){
		List<ECKey> keys  = new ArrayList<ECKey>(3);
		for(int i = 0; i < 3; i++){
			ECKey eckey = new ECKey();	
			System.out.println("ECKey_" + i + "\r\n   pri: " + eckey.getPrivateKeyAsHex() +
					"\r\n   pub: " + eckey.getPublicKeyAsHex());
			keys.add(eckey);
		}
		
		
		NetworkParameters params = OKMainNetParams.get();
	
        Script p2shScript = ScriptBuilder.createP2SHOutputScript(2, keys);
        Address address = Address.fromP2SHScript(OKMainNetParams.get(), p2shScript);
        
        assertEquals(address.getVersion(), params.getP2SHHeader());
        assertTrue(address.isP2SHAddress());
        System.out.println(address.toString());
        assertTrue(address.toString().startsWith("8"));
        
        
        Address addressTestNet = Address.fromP2SHScript(OKTestNetParams.get(), p2shScript);
        System.out.println(addressTestNet.toString());
        assertTrue(addressTestNet.toString().startsWith("4"));    
	}
	
	@Test
	public void testTestNetGenesisBlock(){
		NetworkParameters params = OKTestNetParams.get();
		OKBlock genesisBlock = (OKBlock)params.getGenesisBlock();
		System.out.println(genesisBlock.toString());
		assertEquals(genesisBlock.getHashAsString(), "be9219c0ddeaacc2a63beb2e3bd85706f2eea1299a1bd199d8568b77ebcf8c15");
		System.out.println(genesisBlock.getAnchorHash().toString());
		assertEquals(genesisBlock.getAnchorHash().toString(), "ddaca03074fc9c882ead5fb19515aa4fb4c21fbc5bb10ee1ad7493b7ce90142f");
	}

	
	
}

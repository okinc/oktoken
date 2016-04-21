/**
 * Copyright 2016 OKLink Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.oklink.bitcoinj.params;

import org.bitcoinj.core.Block;
import org.bitcoinj.core.BlockInf;
import org.bitcoinj.core.Context;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Utils;
import org.bitcoinj.params.MainNetParams;
import org.spongycastle.util.encoders.Hex;

import com.oklink.bitcoinj.core.OKBlock;

public class OKTestNetParams extends OKMainNetParams {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 测试网络创世块Coinbase密钥对
	 *  pri: b4d21a16bf2f387b18106f649e641288cd1a4f00bd3058581e96a86e39084c0a
   	 *	pub: 0323941ae444602bea0dfbf2ddd03df5fcff4b26a46b4742089f4fa3839b4e9f95
	 */
	public static final ECKey genesisCoinbaseECKey = ECKey.fromPrivate(Utils.HEX.decode("b4d21a16bf2f387b18106f649e641288cd1a4f00bd3058581e96a86e39084c0a"));
	
	/**
	 *  创世块raw信息 
block: 
   hash: be9219c0ddeaacc2a63beb2e3bd85706f2eea1299a1bd199d8568b77ebcf8c15
   version: 1 (BIP34, BIP66, BIP65)
   previous block: 0000000000000000000000000000000000000000000000000000000000000000
   merkle root: 6b45462d9c1853acbbd41ec677ffa93e8034a1cf5ace66241a18daa23d5c7881
   previous anchor: 0000000000000000000000000000000000000000000000000000000000000000
   time: 1461240965 (2016-04-21T12:16:05Z)
   with 1 transaction(s):
  6b45462d9c1853acbbd41ec677ffa93e8034a1cf5ace66241a18daa23d5c7881
     == COINBASE TXN (scriptSig 0[] PUSHDATA(38)[4f4b546f6b656e2047656e6573697320426c6f636b20697373756564206279204f4b4c696e6b])  (scriptPubKey DUP HASH160 PUSHDATA(20)[8b08ef2a0035a7d8617d869dbf4ad2eb2d1944ff] EQUALVERIFY CHECKSIG)
     in   0[] PUSHDATA(38)[4f4b546f6b656e2047656e6573697320426c6f636b20697373756564206279204f4b4c696e6b]
          outpoint:0000000000000000000000000000000000000000000000000000000000000000:4294967295
     out  DUP HASH160 PUSHDATA(20)[8b08ef2a0035a7d8617d869dbf4ad2eb2d1944ff] EQUALVERIFY CHECKSIG 1000.00 BTC
	 */
	
	//创世块数据
	private static final byte[] genesisData = Hex.decode("01000000000000000000000000000000000000000000000000000000000000000000000081785c3da2da181a2466ce5acfa134803ea9ff77c61ed4bbac53189c2d46456b000000000000000000000000000000000000000000000000000000000000000085c418570101000000010000000000000000000000000000000000000000000000000000000000000000ffffffff2800264f4b546f6b656e2047656e6573697320426c6f636b20697373756564206279204f4b4c696e6bffffffff0100e87648170000001976a9148b08ef2a0035a7d8617d869dbf4ad2eb2d1944ff88ac00000000"); 
	//创世块锚定位置
	private static final byte[] genesisAnchorTxHash = Hex.decode("ddaca03074fc9c882ead5fb19515aa4fb4c21fbc5bb10ee1ad7493b7ce90142f");
		
	public OKTestNetParams(){
		super();
	    
		
		addressHeader = 115;
		p2shHeader = 8;
		port = 16588;
		packetMagic = 0x544b4f4b; //tkok
		//接受地址版本号！
		acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
		 
	}
	
	private static OKBlock genesis;
	
    @Override
    public BlockInf getGenesisBlock() {
        synchronized (OKTestNetParams.class) {
            if (genesis == null) {
                genesis = new OKBlock(OKTestNetParams.get(), genesisData, OKTestNetParams.get().defaultSerializer, genesisData.length);
                genesis.setAnchorHash(Sha256Hash.wrap(genesisAnchorTxHash));
            }
            return genesis;
        }
    }
 
	
	private static OKTestNetParams instance;
	 
	public static synchronized OKTestNetParams get() {
        if (instance == null) {
            instance = new OKTestNetParams();
        }
        return instance;
    }

	@Override
	public String getPaymentProtocolId() {	
		return PAYMENT_PROTOCOL_ID_TESTNET;
	}
	
	
}

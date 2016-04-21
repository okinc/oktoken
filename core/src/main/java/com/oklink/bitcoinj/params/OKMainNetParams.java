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
import org.bitcoinj.core.Utils;
import org.bitcoinj.params.MainNetParams;

import com.oklink.bitcoinj.core.OKBlock;

public class OKMainNetParams extends OKAbstractNetParams {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OKMainNetParams(){
		super();
		
		interval = INTERVAL;
		targetTimespan = TARGET_TIMESPAN;
		maxTarget = Utils.decodeCompactBits(0x1d00ffffL);
		dumpedPrivateKeyHeader = 128;

		bip32HeaderPub = 0x0488B21E; // The 4 byte header that serializes in base58 to "xpub".
		bip32HeaderPriv = 0x0488ADE4; //The 4 byte header that serializes in base58 to "xprv"
	        
		addressHeader = 46;
		p2shHeader = 18;
		port = 6588;
		packetMagic = 0x4f4b544bL; //oktk
		//接受地址版本号！
		acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
		
		Context context = new Context(this);
	}
	
	 private static OKBlock genesis;

    @Override
    public BlockInf getGenesisBlock() {
        synchronized (OKMainNetParams.class) {
            if (genesis == null) {
                genesis = (OKBlock) super.getGenesisBlock();
               //todo！！！创世块信息
                
            }
            return genesis;
        }
    }
	
	 private static OKMainNetParams instance;
	 
	 public static synchronized OKMainNetParams get() {
        if (instance == null) {
            instance = new OKMainNetParams();
        }
        return instance;
    }

	@Override
	public String getPaymentProtocolId() {
		return PAYMENT_PROTOCOL_ID_MAINNET;
	}
}

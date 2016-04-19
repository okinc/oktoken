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
import org.bitcoinj.core.Utils;
import org.bitcoinj.params.MainNetParams;

public class OKTestNetParams extends OKMainNetParams {

	private static final long serialVersionUID = 1L;

	public OKTestNetParams(){
		super();
	    
		
		addressHeader = 115;
		p2shHeader = 8;
		port = 16588;
		packetMagic = 0x544b4f4b; //tkok
		//接受地址版本号！
		acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
	}
	
	 private static Block genesis;

    @Override
    public Block getGenesisBlock() {
        synchronized (OKTestNetParams.class) {
            if (genesis == null) {
                genesis = super.getGenesisBlock();
               //todo！！！创世块信息
                
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

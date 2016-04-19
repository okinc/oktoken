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
import org.bitcoinj.params.MainNetParams;

public class OKRegTestNetParams extends OKTestNetParams {
	private static final long serialVersionUID = 1L;

	public OKRegTestNetParams(){
		super();
		port = 26588;
		packetMagic = 0x544b4f4b; //tkok
		
	}
	
	 private static Block genesis;

    @Override
    public Block getGenesisBlock() {
        synchronized (OKTestNetParams.class) {
            if (genesis == null) {
                genesis = super.getGenesisBlock();
            }
            return genesis;
        }
    }
 
	
	private static OKRegTestNetParams instance;
	 
	public static synchronized OKRegTestNetParams get() {
        if (instance == null) {
            instance = new OKRegTestNetParams();
        }
        return instance;
    }

	@Override
	public String getPaymentProtocolId() {
		// TODO Auto-generated method stub
		return PAYMENT_PROTOCOL_ID_REGTEST;
	}
	
	
}

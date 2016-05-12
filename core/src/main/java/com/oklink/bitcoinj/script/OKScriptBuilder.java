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

package com.oklink.bitcoinj.script;

import static org.bitcoinj.script.ScriptOpCodes.*;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.ScriptException;
import org.bitcoinj.core.Utils;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;

import com.oklink.bitcoinj.params.OKTestNetParams;

public class OKScriptBuilder extends ScriptBuilder{

	public OKScriptBuilder() {
		super();
	}

	public OKScriptBuilder(Script template) {
		super(template);
	}

	@Override
	public OKScript build() {
		return  new OKScript(chunks);
	}
	
	/**
	 * 创建锚定脚本
	 * @param data 写到锚定tx的数据
	 * @return
	 */
	public static Script createAnchorOpReturn(byte[] data){
		Script anchorOpReturn = new ScriptBuilder().op(OP_RETURN)
				.data(OKTestNetParams.ANACHOR_FIX_FLAG)
				.data(data)
				.build();
		return anchorOpReturn;
	}
	
	 
	/**
	 * 超级赎回脚本
	 * @param superAddr 超级私钥地址 （多签赎回脚本->base58地址）
	 * @return
	 */
	private static OKScriptBuilder createWithSuperRedeem(Address superAddr) throws ScriptException{
		if(!superAddr.isP2SHAddress())
       	 	throw new ScriptException("Super Address must be a multi-sig address.");
   	
	   	OKScriptBuilder builder = new OKScriptBuilder();
	       //超级赎回脚本
	    builder.op(OP_DUP).op(OP_HASH160).data(superAddr.getHash160()).op(OP_EQUAL);
	    //流判断
	    builder.op(OP_IF)
	   		.op(OP_TRUE)
	   		.op(OP_ELSE);
	   	
	   	return builder;
	}
	
	

	/** 
	  * 创建可以被超级私钥赎回的支付到地址的输出脚本. 
	  * toAddr 支付目标地址
	  * superAddr 超级私钥地址 （多签赎回脚本->base58地址）
	  */
    public static OKScript createOutputScript(Address toAddr, Address superAddr) throws ScriptException{
        OKScriptBuilder builder = createWithSuperRedeem(superAddr);
    	
    	//正常赎回脚本
    	if (toAddr.isP2SHAddress()) {	//chunck.size = 13
            // OP_HASH160 <scriptHash> OP_EQUAL
                builder.op(OP_HASH160)
                .data(toAddr.getHash160())
                .op(OP_EQUAL);
        } else {						//chunks.size = 12
            // OP_DUP OP_HASH160 <pubKeyHash> OP_EQUALVERIFY OP_CHECKSIG
        	    builder.op(OP_DUP)
                .op(OP_HASH160)
                .data(toAddr.getHash160())
                .op(OP_EQUALVERIFY)
                .op(OP_CHECKSIG);
        }
    	builder.op(OP_ENDIF);
    	
    	return builder.build();
    }

    /** Creates a scriptPubKey that encodes payment to the given raw public key. 
     * 	创建可以被超级私钥赎回的支付到Raw公钥的输出脚本. 
     *  key 支付目标raw公钥
     *  superAddr 超级私钥对应地址（多签赎回脚本->base58地址）
     */
    public static OKScript createOutputScript(ECKey key, Address superAddr) throws ScriptException {
    	OKScriptBuilder builder = createWithSuperRedeem(superAddr);
        
    	builder.data(key.getPubKey()).op(OP_CHECKSIG);	//chuncks.size = 10
    	return builder.build();
    }
}

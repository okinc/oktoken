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
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;

public class OKScriptBuilder extends ScriptBuilder{

	public OKScriptBuilder() {
		super();
	}

	public OKScriptBuilder(Script template) {
		super(template);
	}

	@Override
	public Script build() {
		return  new OKScript(chunks);
	}
	 
	 
	/**
	 * 超级赎回脚本
	 * @param superAddr 超级私钥地址 （多签赎回脚本->base58地址）
	 * @return
	 */
	private static ScriptBuilder createWithSuperRedeem(Address superAddr) throws ScriptException{
		if(!superAddr.isP2SHAddress())
       	 	throw new ScriptException("Super Address must be a multi-sig address.");
   	
	   	ScriptBuilder builder = new ScriptBuilder();
	       //超级赎回脚本
	    builder.op(OP_DUP).op(OP_HASH160).data(superAddr.getHash160()).op(OP_EQUAL);
	    //流判断
	    builder.op(OP_IF)
	   		.op(OP_TRUE)
	   	.op(OP_ELSE)
	   		.op(OP_DROP);
	   	
	   	return builder;
	}
	
	

	/** 
	  * 创建可以被超级私钥赎回的支付到地址的输出脚本. 
	  * toAddr 支付目标地址
	  * superAddr 超级私钥地址 （多签赎回脚本->base58地址）
	  */
    public static Script createOutputScript(Address toAddr, Address superAddr) throws ScriptException{
        ScriptBuilder builder = createWithSuperRedeem(superAddr);
    	
    	//正常赎回脚本
    	if (toAddr.isP2SHAddress()) {	//chunck.size = 14
            // OP_HASH160 <scriptHash> OP_EQUAL
                builder.op(OP_HASH160)
                .data(toAddr.getHash160())
                .op(OP_EQUAL);
        } else {						//chunks.size = 13
            // OP_DUP OP_HASH160 <pubKeyHash> OP_EQUALVERIFY OP_CHECKSIG
        	    builder.op(OP_DUP)
                .op(OP_HASH160)
                .data(toAddr.getHash160())
                .op(OP_EQUALVERIFY)
                .op(OP_CHECKSIG);
        }
    	
    	return builder.build();
    }

    /** Creates a scriptPubKey that encodes payment to the given raw public key. 
     * 	创建可以被超级私钥赎回的支付到Raw公钥的输出脚本. 
     *  key 支付目标raw公钥
     *  superAddr 超级私钥对应地址（多签赎回脚本->base58地址）
     */
    public static Script createOutputScript(ECKey key, Address superAddr) throws ScriptException {
    	ScriptBuilder builder = createWithSuperRedeem(superAddr);
        
    	return builder.data(key.getPubKey()).op(OP_CHECKSIG).build();	//chuncks.size = 11
    }
}

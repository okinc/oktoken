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

package com.oklink.bitcoinj.core;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.MessageSerializer;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.ProtocolException;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionOutput;
import org.bitcoinj.script.ScriptBuilder;

import com.oklink.bitcoinj.script.OKScriptBuilder;

public class OKTransactionOutput extends TransactionOutput {

	/**
	 * 
	 * @param params
	 * @param parent
	 * @param value
	 * @param to
	 * @param superAddr 
	 */
	public OKTransactionOutput(NetworkParameters params, Transaction parent, Coin value, Address to, Address superAddr) {
		 super(params, parent, value, OKScriptBuilder.createOutputScript(to, superAddr).getProgram());
	}
	
	/**
	 * 
	 * @param params
	 * @param parent
	 * @param value
	 * @param to
	 * @param superAddr 超级赎回地址
	 */
	public OKTransactionOutput(NetworkParameters params, Transaction parent, Coin value, ECKey to, Address superAddr) {
		 super(params, parent, value, OKScriptBuilder.createOutputScript(to, superAddr).getProgram());
	}
	
	
	

	

}

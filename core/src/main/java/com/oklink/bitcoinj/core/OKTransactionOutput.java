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
import org.bitcoinj.core.ScriptException;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionOutput;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;

import com.oklink.bitcoinj.script.OKScript;
import com.oklink.bitcoinj.script.OKScriptBuilder;

public class OKTransactionOutput extends TransactionOutput {
	
	  private Script scriptPubKey;

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
	
	public OKTransactionOutput(NetworkParameters params, Transaction parent, byte[] payload, int offset,
			MessageSerializer serializer) throws ProtocolException {
		super(params, parent, payload, offset, serializer);
		// TODO Auto-generated constructor stub
	}

	public OKTransactionOutput(NetworkParameters params, Transaction parent, byte[] payload, int offset)
			throws ProtocolException {
		super(params, parent, payload, offset);
		// TODO Auto-generated constructor stub
	}

	public OKTransactionOutput(NetworkParameters params, Transaction parent, Coin value, Address to) {
		super(params, parent, value, to);
		// TODO Auto-generated constructor stub
	}

	public OKTransactionOutput(NetworkParameters params, Transaction parent, Coin value, byte[] scriptBytes) {
		super(params, parent, value, scriptBytes);
		// TODO Auto-generated constructor stub
	}

	public OKTransactionOutput(NetworkParameters params, Transaction parent, Coin value, ECKey to) {
		super(params, parent, value, to);
		// TODO Auto-generated constructor stub
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

	
	@Override
	public Script getScriptPubKey() throws ScriptException {
		 if (scriptPubKey == null) {
	            scriptPubKey = new OKScript(getScriptBytes());
	     }
	     return scriptPubKey;
	}
	
	
	

	

}

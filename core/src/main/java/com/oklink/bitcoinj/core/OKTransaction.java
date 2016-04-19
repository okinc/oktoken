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

import java.util.HashSet;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.Block;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Message;
import org.bitcoinj.core.MessageSerializer;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.ProtocolException;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionInput;
import org.bitcoinj.core.TransactionOutPoint;
import org.bitcoinj.core.TransactionOutput;
import org.bitcoinj.core.VerificationException;

import com.oklink.bitcoinj.script.OKSuperKey;


//todo !
public class OKTransaction extends Transaction {

	public OKTransaction(NetworkParameters params, byte[] payload, int offset, Message parent,
			MessageSerializer setSerializer, int length) throws ProtocolException {
		super(params, payload, offset, parent, setSerializer, length);
		// TODO Auto-generated constructor stub
	}

	public OKTransaction(NetworkParameters params, byte[] payload, int offset) throws ProtocolException {
		super(params, payload, offset);
		// TODO Auto-generated constructor stub
	}

	public OKTransaction(NetworkParameters params, byte[] payload, Message parent, MessageSerializer setSerializer,
			int length) throws ProtocolException {
		super(params, payload, parent, setSerializer, length);
		// TODO Auto-generated constructor stub
	}

	public OKTransaction(NetworkParameters params, byte[] payloadBytes) throws ProtocolException {
		super(params, payloadBytes);
		// TODO Auto-generated constructor stub
	}

	public OKTransaction(NetworkParameters params) {
		super(params);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param value
	 * @param address
	 * @param superAddr 超级赎回地址
	 * @return
	 */
	public TransactionOutput addOutput(Coin value, Address address, Address superAddr) {
		 return addOutput(new OKTransactionOutput(params, this, value, address, superAddr));
	}

	/**
	 * 
	 * @param value
	 * @param pubkey
	 * @param superAddr 超级赎回地址
	 * @return
	 */
	public TransactionOutput addOutput(Coin value, ECKey pubkey, Address superAddr) {
		 return addOutput(new OKTransactionOutput(params, this, value, pubkey, superAddr));
	}

	@Override
	public TransactionOutput addOutput(Coin value, Address address) {
		return this.addOutput(value, address, OKSuperKey.superKeyAddress(getParams()));
	}

	@Override
	public TransactionOutput addOutput(Coin value, ECKey pubkey) {
		return this.addOutput(value, pubkey, OKSuperKey.superKeyAddress(getParams()));
	}

}

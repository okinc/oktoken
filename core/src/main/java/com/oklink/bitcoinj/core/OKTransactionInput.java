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
import org.bitcoinj.core.MessageSerializer;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.ProtocolException;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionInput;
import org.bitcoinj.core.TransactionOutPoint;

public class OKTransactionInput extends TransactionInput {

	public OKTransactionInput(NetworkParameters params, Transaction parentTransaction, byte[] payload, int offset,
			MessageSerializer serializer) throws ProtocolException {
		super(params, parentTransaction, payload, offset, serializer);
		// TODO Auto-generated constructor stub
	}

	public OKTransactionInput(NetworkParameters params, Transaction parentTransaction, byte[] payload, int offset)
			throws ProtocolException {
		super(params, parentTransaction, payload, offset);
		// TODO Auto-generated constructor stub
	}

	public OKTransactionInput(NetworkParameters params, Transaction parentTransaction, byte[] scriptBytes,
			TransactionOutPoint outpoint, Coin value) {
		super(params, parentTransaction, scriptBytes, outpoint, value);
		// TODO Auto-generated constructor stub
	}

	public OKTransactionInput(NetworkParameters params, Transaction parentTransaction, byte[] scriptBytes,
			TransactionOutPoint outpoint) {
		super(params, parentTransaction, scriptBytes, outpoint);
		// TODO Auto-generated constructor stub
	}

	public OKTransactionInput(NetworkParameters params, Transaction parentTransaction, byte[] scriptBytes) {
		super(params, parentTransaction, scriptBytes);
		// TODO Auto-generated constructor stub
	}
	

}

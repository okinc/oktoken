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

import org.bitcoinj.core.Message;
import org.bitcoinj.core.MessageSerializer;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.ProtocolException;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionOutPoint;
import org.bitcoinj.core.TransactionOutput;

public class OKTransactionOutPoint extends TransactionOutPoint {

	public OKTransactionOutPoint(NetworkParameters params, byte[] payload, int offset, Message parent,
			MessageSerializer serializer) throws ProtocolException {
		super(params, payload, offset, parent, serializer);
		// TODO Auto-generated constructor stub
	}

	public OKTransactionOutPoint(NetworkParameters params, byte[] payload, int offset) throws ProtocolException {
		super(params, payload, offset);
		// TODO Auto-generated constructor stub
	}

	public OKTransactionOutPoint(NetworkParameters params, long index, Sha256Hash hash) {
		super(params, index, hash);
		// TODO Auto-generated constructor stub
	}

	public OKTransactionOutPoint(NetworkParameters params, long index, Transaction fromTx) {
		super(params, index, fromTx);
		// TODO Auto-generated constructor stub
	}

	public OKTransactionOutPoint(NetworkParameters params, TransactionOutput connectedOutput) {
		super(params, connectedOutput);
		// TODO Auto-generated constructor stub
	}

}

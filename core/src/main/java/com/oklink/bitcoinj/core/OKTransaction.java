/**
 * OK transaction
 */

package com.oklink.bitcoinj.core;

import org.bitcoinj.core.Message;
import org.bitcoinj.core.MessageSerializer;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.ProtocolException;
import org.bitcoinj.core.Transaction;


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


}

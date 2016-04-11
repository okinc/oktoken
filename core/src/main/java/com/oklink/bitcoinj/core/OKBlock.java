package com.oklink.bitcoinj.core;

import java.util.List;

import org.bitcoinj.core.Block;
import org.bitcoinj.core.Message;
import org.bitcoinj.core.MessageSerializer;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.ProtocolException;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Transaction;

public class OKBlock extends Block {

	public OKBlock(NetworkParameters params, byte[] payloadBytes, int offset, Message parent,
			MessageSerializer serializer, int length) throws ProtocolException {
		super(params, payloadBytes, offset, parent, serializer, length);
		// TODO Auto-generated constructor stub
	}

	public OKBlock(NetworkParameters params, byte[] payloadBytes, int offset, MessageSerializer serializer, int length)
			throws ProtocolException {
		super(params, payloadBytes, offset, serializer, length);
		// TODO Auto-generated constructor stub
	}

	public OKBlock(NetworkParameters params, byte[] payloadBytes, MessageSerializer serializer, int length)
			throws ProtocolException {
		super(params, payloadBytes, serializer, length);
		// TODO Auto-generated constructor stub
	}

	public OKBlock(NetworkParameters params, long version, Sha256Hash prevBlockHash, Sha256Hash merkleRoot, long time,
			long difficultyTarget, long nonce, List<Transaction> transactions) {
		super(params, version, prevBlockHash, merkleRoot, time, difficultyTarget, nonce, transactions);
		// TODO Auto-generated constructor stub
	}

}

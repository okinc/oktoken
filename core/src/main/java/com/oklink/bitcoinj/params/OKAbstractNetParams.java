package com.oklink.bitcoinj.params;

import static org.bitcoinj.core.Coin.COIN;

import org.bitcoinj.core.Block;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.StoredBlock;
import org.bitcoinj.core.VerificationException;
import org.bitcoinj.params.AbstractBitcoinNetParams;
import org.bitcoinj.store.BlockStore;
import org.bitcoinj.store.BlockStoreException;

import com.oklink.bitcoinj.core.OKBlock;

public abstract class OKAbstractNetParams extends AbstractBitcoinNetParams {
	 /**
     * Scheme part for OKToken URIs.
     */
    public static final String BITCOIN_SCHEME = "oktoken";
    public static final Coin MAX_MONEY = COIN.multiply(Integer.MAX_VALUE);
    public static final byte[]  ANACHOR_FIX_FLAG = {0x4f, 0x4b, 0x54};	//锚定OP_RETURE中的前缀 (OKT)

    protected OKBlock genesisBlock;
    
	@Override
	public String getPaymentProtocolId() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void checkDifficultyTransitions(StoredBlock storedPrev, Block nextBlock, BlockStore blockStore)
			throws VerificationException, BlockStoreException {
		//不做任何事
	}


	@Override
	public Coin getMaxMoney() {
		// TODO Auto-generated method stub
		return  MAX_MONEY;
	}
	
	public OKBlock getGenesisOKBlock(){
		return genesisBlock;
	}
}

package com.oklink.bitcoinj.params;

import static com.google.common.base.Preconditions.checkState;

import org.bitcoinj.core.Block;
import org.bitcoinj.params.MainNetParams;

public class OKMainNetParams extends MainNetParams {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OKMainNetParams(){
		super();
		
		addressHeader = 46;
		p2shHeader = 18;
		port = 6588;
		packetMagic = 0x4f4b544bL; //oktk
		//接受地址版本号！
		acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
	}
	
	 private static Block genesis;

    @Override
    public Block getGenesisBlock() {
        synchronized (OKMainNetParams.class) {
            if (genesis == null) {
                genesis = super.getGenesisBlock();
               //todo！！！创世块信息
                
            }
            return genesis;
        }
    }
	
	 private static OKMainNetParams instance;
	 
	 public static synchronized OKMainNetParams get() {
        if (instance == null) {
            instance = new OKMainNetParams();
        }
        return instance;
    }
}

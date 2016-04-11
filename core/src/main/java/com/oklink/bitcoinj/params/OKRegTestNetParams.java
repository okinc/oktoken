package com.oklink.bitcoinj.params;

import org.bitcoinj.core.Block;
import org.bitcoinj.params.MainNetParams;

public class OKRegTestNetParams extends OKTestNetParams {
	private static final long serialVersionUID = 1L;

	public OKRegTestNetParams(){
		super();
		port = 26588;
		packetMagic = 0x544b4f4b; //tkok
		
	}
	
	 private static Block genesis;

    @Override
    public Block getGenesisBlock() {
        synchronized (OKTestNetParams.class) {
            if (genesis == null) {
                genesis = super.getGenesisBlock();
               //todo！！！创世块信息
                
            }
            return genesis;
        }
    }
 
	
	private static OKRegTestNetParams instance;
	 
	public static synchronized OKRegTestNetParams get() {
        if (instance == null) {
            instance = new OKRegTestNetParams();
        }
        return instance;
    }
}

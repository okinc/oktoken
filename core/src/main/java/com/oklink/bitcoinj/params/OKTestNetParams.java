package com.oklink.bitcoinj.params;

import org.bitcoinj.core.Block;
import org.bitcoinj.params.MainNetParams;

public class OKTestNetParams extends MainNetParams {

	private static final long serialVersionUID = 1L;

	public OKTestNetParams(){
		super();
		
		addressHeader = 115;
		p2shHeader = 8;
		port = 16588;
		packetMagic = 0x544b4f4b; //tkok
		//接受地址版本号！
		acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
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
 
	
	private static OKTestNetParams instance;
	 
	public static synchronized OKTestNetParams get() {
        if (instance == null) {
            instance = new OKTestNetParams();
        }
        return instance;
    }
}

package com.oklink.bitcoinj.core;

import org.bitcoinj.core.VerificationException;

@SuppressWarnings("serial")
public class OKVerificationException extends VerificationException {

	public OKVerificationException(String msg) {
		super(msg);
	}

	public OKVerificationException(Exception e) {
		super(e);
	}

	public OKVerificationException(String msg, Throwable t) {
		super(msg, t);
	}
	
	public static class UnexpectedSuperPublicKey extends VerificationException {
        public UnexpectedSuperPublicKey() {
            super("Output super public key is not match!");
        }
    }

}

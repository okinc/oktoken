package org.bitcoinj.core;

import java.math.BigInteger;

/**
 * Block抽象接口
 * @author chenzs
 *
 */
public interface BlockInf {

	public long getTimeSeconds();
	public Sha256Hash getHash();
//	public BlockInf cloneAsHeader();
	public void setDifficultyTarget(long l);
	public void setTime(long l);
	public void setNonce(long l);
	public String getHashAsString();
	public void solve();
//	public BigInteger getWork();
	
	
}

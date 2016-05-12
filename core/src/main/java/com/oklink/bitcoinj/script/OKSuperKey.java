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

package com.oklink.bitcoinj.script;

import java.util.Arrays;
import java.util.List;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Utils;
import org.bitcoinj.script.Script;
import com.oklink.bitcoinj.params.OKMainNetParams;
import com.oklink.bitcoinj.params.OKTestNetParams;

/**
 * 超级公钥（赎回脚本）
 */
public class OKSuperKey {
	
	///!> 提现地址公钥（相当销毁地址）公钥hash160全是0000
	public static final byte[] DestoryRimple160 = Utils.HEX.decode("0000000000000000000000000000000000000000");
	public static final Address DestoryAddrMainNet = new Address(OKMainNetParams.get(), DestoryRimple160);
	public static final Address DestoryAddrTestNet = new Address(OKTestNetParams.get(), DestoryRimple160);
	
	//测试数据
	/*
	ECKey_0
	   pri: fc7201864c7f5e8556984ba921114de65b37642cc2c7b151304511b32123b052
	   pub: 03e2aab13e88f47caa71b44e6b48145e4946066470bc0b45d71d5a3e4ca6873887
	ECKey_1
	   pri: 261517b655dc41374af8cd82acad3d7fb57f7b27e85fa7435e8b913216be1b04
	   pub: 0224777a72a072bae71a541262d1d8020bb22af5b006900f148c7abf023a76fb85
	ECKey_2
	   pri: f245210a150228de3e77d256d66752af2aa7e7dc742e3a325841fb382b7e58a3
	   pub: 0368f5c539ace3b90b9c655e9cdb8a3ec8c0df6a03d80f9878339a10bb55c17632
	
	8b6bDpvhLssg3mKwiENVEc32Da16Mt97NF
	4ZiZNjwpF5EusRw5U33JPMK9vXRf9jHirj
	*/
	
	///!> 最低签名下限
	private static final int SIG_THRESHOLD	= 2;
	
	///!>超级公钥（HEX）
	public static final List<ECKey> SUPER_KEYS_PUBLIC_ONLY = Arrays.asList(
			ECKey.fromPublicOnly(Utils.HEX.decode("03e2aab13e88f47caa71b44e6b48145e4946066470bc0b45d71d5a3e4ca6873887")),
			ECKey.fromPublicOnly(Utils.HEX.decode("0224777a72a072bae71a541262d1d8020bb22af5b006900f148c7abf023a76fb85")),
			ECKey.fromPublicOnly(Utils.HEX.decode("0368f5c539ace3b90b9c655e9cdb8a3ec8c0df6a03d80f9878339a10bb55c17632"))
	);
	
	
	///!>超级私钥(HEX)。 ！！！仅为测试，不应在此公开的！！！
	public static final List<ECKey> SUPER_KEYS = Arrays.asList(
			ECKey.fromPrivate(Utils.HEX.decode("fc7201864c7f5e8556984ba921114de65b37642cc2c7b151304511b32123b052")),
			ECKey.fromPrivate(Utils.HEX.decode("261517b655dc41374af8cd82acad3d7fb57f7b27e85fa7435e8b913216be1b04")),
			ECKey.fromPrivate(Utils.HEX.decode("f245210a150228de3e77d256d66752af2aa7e7dc742e3a325841fb382b7e58a3"))
		);
	
	
	public static Script superP2SHScript = OKScriptBuilder.createP2SHOutputScript(SIG_THRESHOLD, SUPER_KEYS_PUBLIC_ONLY);
	private static Address addressMainNet = Address.fromP2SHScript(OKMainNetParams.get(), superP2SHScript);
	private static Address addressTestNet = Address.fromP2SHScript(OKTestNetParams.get(), superP2SHScript);
	
	
	
	
	/**
	 * 超级地址
	 * @param params 网络参数类型
	 * @return
	 */
	public static Address superKeyAddress(NetworkParameters params){
		if(params == OKMainNetParams.get())
			return addressMainNet;
		else
			return addressTestNet;
	}
	
	/**
	 * 通过超级key创建超级地址
	 * @param params
	 * @param keys	公钥列表
	 * @param threshold 签名数量下限
	 * @return
	 */
	public static Address superKeyAddress(NetworkParameters params, List<ECKey> keys, int threshold){
		if(keys == null || keys.size() < 1)
			return null;
		
		threshold = threshold <= keys.size() ? threshold : keys.size();
	    Script p2shPubScript = OKScriptBuilder.createP2SHOutputScript(threshold, keys);
	    return Address.fromP2SHScript(params, p2shPubScript);
	}

}

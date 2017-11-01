package com.azcx9d.agency.base;

public class Const {
//	public static final String NO_APITOKEN_PATH = ".*/((agApi/exchange)|(agApi/redirect)|(agApi/tes)).*";
	public static final String NO_APITOKEN_PATH = ".*/(agApi/redirect).*";
	
	/**
	 * 代理商兑换手续费T+1
	 */
	public static final double AGENT_POUNDAGE_1 = 0.006;
	
	/**
	 * 代理商兑换手续费T+3
	 */
	public static final double AGENT_POUNDAGE_3 = 0.003;
	
	/**
	 * 代理商兑换手续费T+7
	 */
	public static final double AGENT_POUNDAGE_7 = 0.08;
	
	/**
	 * 代理商兑换代扣税
	 */
	public static final double AGENT_TAX = 0.01;
}

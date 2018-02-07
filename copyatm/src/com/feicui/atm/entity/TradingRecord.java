package com.feicui.atm.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class TradingRecord implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2580977341809896570L;
	
	private ArrayList<String> record;
	
	public TradingRecord() {
		record = new ArrayList<String>();		
	}
	
	public void appendRecord(String message) {
		//添加交易记录
		record.add(String.format("%tF %<tT %s", new Date(),message));
	}
	
	@Override
	public String toString() {
		if(record.isEmpty()) {
			return null;
		}
		return String.join("\n",record);
	}
}

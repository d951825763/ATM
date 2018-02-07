package com.feicui.atm.ui;

import com.feicui.atm.entity.AtmUser;
import com.feicui.atm.util.CommonUtil;

public class UserInfo {
	
	private AtmUser user;
	public UserInfo(AtmUser user) {
		this.user = user;
	}
	
	public void show() {
		//账号
		CommonUtil.printLine("BA",user.getAccount());
		
		//姓名
		CommonUtil.printLine("BN", user.getName());
        
        // 身份证号
        CommonUtil.printLine("BI", user.getIdNumber());
        
        // 余额
        CommonUtil.printLine("BB", user.getBalance());
        
        // 性别
        CommonUtil.printLine("BG", user.getGender());
        
        // 家庭住址
        CommonUtil.printLine("BH", user.getAddress());
        
        // 学历
        CommonUtil.printLine("BE", user.getBackground());
	}
}

package com.feicui.atm.admin.ui;

import com.feicui.atm.ui.AbstractAtmUi;
import com.feicui.atm.util.CommonUtil;
import com.feicui.atm.util.ValidateInput;

public class AdminLoginAtmUi extends  AbstractAtmUi{

	@Override
	public AbstractAtmUi show() {
		//显示欢迎语句
		CommonUtil.printLine("LW1");
		
		//输入管理员用户名
		ValidateInput.executeInstance("LI0",str -> str.equals("1234"),"UE5");
		
		//输入管理员密码
		ValidateInput.executeInstance("LI1",str -> str.equals("5678"),"UE6");
		
		//进入主菜单
		return new AdminMainMenuAtmUi();
	}
}

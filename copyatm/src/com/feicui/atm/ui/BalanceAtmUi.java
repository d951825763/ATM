package com.feicui.atm.ui;

import com.feicui.atm.util.CommonUtil;
import com.feicui.atm.util.PropertyOperation;

public class BalanceAtmUi extends AbstractAtmUi {//查询余额界面

	@Override
	public AbstractAtmUi show() {
		
		//显示标题
		CommonUtil.printLine("BT");
		
		//显示信息
		UserInfo info = new UserInfo(user);
		info.show();
		
		//返回主菜单
		return new PropertyOperation<AbstractAtmUi>(this)
			.addOperation("UL0",new MainMenuAtmUi())
			.action();
	}
}

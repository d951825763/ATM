package com.feicui.atm.admin.ui;

import com.feicui.atm.ui.AbstractAtmUi;
import com.feicui.atm.util.CommonUtil;
import com.feicui.atm.util.PropertyOperation;

public class AdminMainMenuAtmUi extends AbstractAtmUi {
	
	@Override
	public AbstractAtmUi show() {
		
		//打印主菜单标题
		CommonUtil.printLine("MM");
		
		//去往各个功能
		AbstractAtmUi dest = new PropertyOperation<AbstractAtmUi>(this)
			//开户
			.addOperation("MMA0",new AdminAddAtmUi())
			//销户
			.addOperation("MMA1",new AdminRemoveAtmUi())
			//查看所有账户信息
			.addOperation("MMA2",new AdminInfoAtmUi())
			//修改账户信息
			.addOperation("MMA3",new AdminModifyAtmUi())
			//退出
			.addOperation("MMA4",new AdminLoginAtmUi())
			
			.action();
		
		return dest;
	}

}

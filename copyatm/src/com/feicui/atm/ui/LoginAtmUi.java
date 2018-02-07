package com.feicui.atm.ui;

import com.feicui.atm.entity.AtmUser;
import com.feicui.atm.util.CommonUtil;
import com.feicui.atm.util.PropertyValidateInput;

public class LoginAtmUi extends AbstractAtmUi{//登录界面

	@Override
	public AbstractAtmUi show() {
		service.logout();
		
		//显示欢迎语句
		CommonUtil.printLine("LW0");
		
		//输入账号
		String account = new PropertyValidateInput("LI0")
			
			//账号格式
			.addRegexCondition("number21","UE4")
			
			//账号是否已存在
			.addCondition(str -> service.getUser(str) != null,"UE5")
			.execute();
		/*String password = */new PropertyValidateInput("LI1")
			
			//密码格式
			.addRegexCondition("password","UE10")
			
			//密码是否正确
			.addCondition(str -> 
    			service.login(new AtmUser(account, str)), "UE6")
			.execute();
    
    return new MainMenuAtmUi();
	}
}

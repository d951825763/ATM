package com.feicui.atm.admin.ui;

import com.feicui.atm.entity.AtmUser;
import com.feicui.atm.service.AdminService;
import com.feicui.atm.ui.AbstractAtmUi;
import com.feicui.atm.util.CommonUtil;
import com.feicui.atm.util.PropertyOperation;
import com.feicui.atm.util.PropertyValidateInput;

public class AdminModifyAtmUi extends AbstractAtmUi{

	@Override
	public AbstractAtmUi show() {
		AdminService service = new AdminService();
		
		//输出标题
		CommonUtil.printLine("MT");
		
		//输入账号
		String account = new PropertyValidateInput("MA")
			
			//账号不符合规则
			.addRegexCondition("number21","UE4")
			//账号不存在
			.addCondition(str -> service.getUserByAccount(str) != null,"UE5")
			.execute();
		
		AtmUser user = service.getUserByAccount(account);
		
		//修改姓名
		String name = modifyItem(user.getName(),"MG",".*",null);
		user.setName(name);
		
		//修改家庭住址
		String address = modifyItem(user.getAddress(),"MH",".*",null);
		user.setAddress(address);
		
		//修改学历
		String background = 
			modifyItem(user.getBackground(),"ME","background",null);
		
		user.setBackground(CommonUtil.getMessage("EDU"+background));
		
		//修改密码
		String password = 
			modifyItem(user.getPassword(),"MP","password",null);
		user.setPassword(password);
		
		service.modifyUser(user);
		
		//输出修改成功
		CommonUtil.printLine("MS");
		
		//返回主菜单
		return new PropertyOperation<AbstractAtmUi>(this)
			.addOperation("UL0",new AdminMainMenuAtmUi())
			.action();
	}
	
	private String modifyItem(
		String origin,String message,String regex,String error) {
		
		//用户输入空值，则不修改，同时验证用户输入的正确性
		message = String.format(CommonUtil.getMessage(message),origin);
		String result = new PropertyValidateInput(message)
			.addCondition(str ->(
				str.isEmpty() || str.matches(CommonUtil.getRegex(regex))
			),error).execute();
		
		return result.isEmpty() ? origin : result;
	}
	
}

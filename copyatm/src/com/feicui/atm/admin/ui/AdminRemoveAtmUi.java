package com.feicui.atm.admin.ui;

import com.feicui.atm.entity.AtmUser;
import com.feicui.atm.service.AdminService;
import com.feicui.atm.ui.AbstractAtmUi;
import com.feicui.atm.util.CommonUtil;
import com.feicui.atm.util.PropertyOperation;
import com.feicui.atm.util.PropertyValidateInput;

public class AdminRemoveAtmUi extends AbstractAtmUi{
	
	@Override
	public AbstractAtmUi show() {
		
		//输出标题
		CommonUtil.printLine("DT");
		AdminService service = new AdminService();
		
		String account = new PropertyValidateInput("DA")
			
			//账号不符合规则
			.addRegexCondition("number21","UE4")
			
			//账号不存在
			.addCondition(str -> service.getUserByAccount(str) != null,"UE5")
			.execute();
		
		AtmUser user = service.getUserByAccount(account);
		
		/*String idNumber =*/ new PropertyValidateInput("DI")
			//身份证号不符合规则
			.addRegexCondition("idnumber","UE8")
			//身份证号与账号不匹配
			.addCondition(str ->
				user.equals(service.getUserByIdNumber(str)),"UE7")
			.execute();
		//移除用户
		service.removeUser(user);
		
		//打印销户成功
		CommonUtil.printLine("DS");
		
		//回到主菜单
		return new PropertyOperation<AbstractAtmUi>(this)
			.addOperation("UL0",new AdminMainMenuAtmUi())
			.action();
	}
}

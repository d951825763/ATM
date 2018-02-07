package com.feicui.atm.admin.ui;

import java.util.Date;

import com.feicui.atm.entity.AtmUser;
import com.feicui.atm.service.AdminService;
import com.feicui.atm.ui.AbstractAtmUi;
import com.feicui.atm.util.CommonUtil;
import com.feicui.atm.util.PropertyOperation;
import com.feicui.atm.util.PropertyValidateInput;
import com.feicui.atm.util.ValidateInput;

public class AdminAddAtmUi extends AbstractAtmUi{
	
	@Override
	public AbstractAtmUi show() {
		
		AdminService service = new AdminService();
		
		//输出标题
		CommonUtil.printLine("AT");
		
		//输入姓名
		String name = ValidateInput.executeRegex("AN",".*");
		
		//输入身份证号
		String idNumber = new PropertyValidateInput("AI")
			
			//验证身份证号格式
			.addRegexCondition("idnumber","UE8")
			
			//验证是否重复~
			.addCondition(str -> service.getUserByIdNumber(str) == null,"UE9")
			
			.execute();
		
		//输入性别
		String gender = ValidateInput.executeRegex("AG","gender");
		
		//输入家庭住址
		String address = ValidateInput.executeRegex("AH",".*");
		
		//输入学历
		String background = ValidateInput.executeRegex("AE","background");
		
		//输入密码
		String password = ValidateInput.executeRegex("AP","password");
		
		//生成账号
		String accountFormat = CommonUtil.getMessage("AX");
		String account = String.format(accountFormat, gender, new Date());
		
		//将用户输入的性别转换为字符串
		gender = CommonUtil.getMessage("GEN" + gender);
		
		//将用户输入的学历转换为字符串
		background = CommonUtil.getMessage("EDU" + background);
		
		//创建用户对象
		AtmUser user = new AtmUser(account,password,name,gender,background,address,idNumber);
		
		//存入文件
		service.addUser(user);
		
		//输出开户成功
		CommonUtil.printLine("AS",account,password);
		
		//返回主菜单
		return new PropertyOperation<AbstractAtmUi>(this)
			.addOperation("UL0",new AdminMainMenuAtmUi())
			.action();
	}
	
}

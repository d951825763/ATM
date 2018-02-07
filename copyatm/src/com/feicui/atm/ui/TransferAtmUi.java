package com.feicui.atm.ui;

import com.feicui.atm.entity.AtmUser;
import com.feicui.atm.util.CommonUtil;
import com.feicui.atm.util.PropertyOperation;
import com.feicui.atm.util.PropertyValidateInput;

public class TransferAtmUi extends AbstractAtmUi{//转账界面

	@Override
	public AbstractAtmUi show() {
		//显示标题
		CommonUtil.printLine("TT","TI0");
		
		//输出账号
		String account = new PropertyValidateInput("TI0")
			//账号格式
			.addRegexCondition("number21","UE4")
			
			//账号是否已存在
			.addCondition(str ->service.getUser(str) != null,"UE5")
			
			//是否给自己转账
			.addCondition(str -> !(user.getAccount().equals(str)),"TE")
			.execute();
		//输出金额
		String amountString = new PropertyValidateInput("TI1")
			//金额格式
			.addRegexCondition("amount","UE1")
			.execute();
		
		AtmUser transferUser = service.getUser(account);
		
		double amount = Double.parseDouble(amountString);
		
		//打印对方账号
		CommonUtil.printLine("TZ0",account);
		
		//打印对方姓名
		CommonUtil.printLine("TZ1",transferUser.getName());
		
		//打印金额
		CommonUtil.printLine("TZ2",amount);
		
		//转账过程
		AbstractAtmUi ui = new PropertyOperation<AbstractAtmUi>(this)
			.addOperation("UL0",new MainMenuAtmUi())
			.addOperation("UL1",this)
			.addOperationSupply("UL2",() -> {
				if (AbstractAtmUi.service.transfer(transferUser, amount)) { 
    				// 余额充足
    				CommonUtil.printLine("TS");
    				CommonUtil.printLine("TB", amount, user.getBalance());
    				// 返回主菜单
    				return new PropertyOperation<AbstractAtmUi>(this)
    			            .addOperation("UL0", new MainMenuAtmUi())
    			            .action();
    			} else { // 余额不足
    				CommonUtil.printLine("UE3");
    				// 重新输入
    				return this;
    			}
			}).action();
		return ui;
	}
}

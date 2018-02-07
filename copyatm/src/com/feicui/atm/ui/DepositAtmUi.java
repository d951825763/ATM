package com.feicui.atm.ui;

import com.feicui.atm.util.CommonUtil;
import com.feicui.atm.util.PropertyOperation;
import com.feicui.atm.util.PropertyValidateInput;

public class DepositAtmUi extends AbstractAtmUi {//存款界面

	public String amount;
	
	@Override
	public AbstractAtmUi show() {
		
		//显示标题
		CommonUtil.printLine("CT");
		
		//获取金额，并过滤所有的输入异常
		String amountStr = new PropertyValidateInput("CI")
			//是否为数字
			.addRegexCondition("amount","UE1")
			
			//是否为100的倍数
			.addRegexCondition("damount","UE2")
			.execute();
		
		// 将字符串转换为double
    	double amount = Double.parseDouble(amountStr);
    	
    	// 打印金额
    	CommonUtil.printLine("CC", amount);
    	
    	// 取款过程
    	AbstractAtmUi ui = new PropertyOperation<AbstractAtmUi>(this)
    		.addOperation("UL0",new MainMenuAtmUi())
    		.addOperation("UL1",this)
    		.addOperationSupply("UL2",() ->{
    			if(AbstractAtmUi.service.deposit(amount)) {//余额充足
    				CommonUtil.printLine("CS");
    				CommonUtil.printLine("CB",amount,user.getBalance());
    				
    				//返回主菜单
    				return new PropertyOperation<AbstractAtmUi>(this)
    			          .addOperation("UL0", new MainMenuAtmUi())
    			          .action();
    			}else {//余额不足
    				CommonUtil.printLine("UE3");
    				//重新输入
    				return this;
    			}
    		}).action();
    	return ui;
    	}
}

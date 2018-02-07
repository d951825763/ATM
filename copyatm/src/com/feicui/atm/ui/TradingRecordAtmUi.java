package com.feicui.atm.ui;

import com.feicui.atm.util.CommonUtil;
import com.feicui.atm.util.PropertyOperation;

public class TradingRecordAtmUi extends AbstractAtmUi{//登录页面
	
	@Override
    public AbstractAtmUi show() {
    	
		// 显示标题
        CommonUtil.printLine("RT");
        
        // 显示交易记录
        CommonUtil.printLine(user.getRecord());
        
        // 返回主菜单
        return new PropertyOperation<AbstractAtmUi>(this)
            .addOperation("UL0", new MainMenuAtmUi())
            .action();
    }
}

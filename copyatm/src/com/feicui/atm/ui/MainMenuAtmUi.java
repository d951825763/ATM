package com.feicui.atm.ui;

import com.feicui.atm.util.CommonUtil;
import com.feicui.atm.util.PropertyOperation;

public class MainMenuAtmUi extends AbstractAtmUi {// 主界面

	@Override
	public AbstractAtmUi show() {

		// 打印主菜单标题
		CommonUtil.printLine("MM");

		// 去往各个功能
		AbstractAtmUi dest = new PropertyOperation<AbstractAtmUi>(this)

				// 查询
				.addOperation("MM1", new BalanceAtmUi())

				// 转账
				.addOperation("MM2", new DebitAtmUi())

				// 取款
				.addOperation("MM3", new DepositAtmUi())

				// 存款
				.addOperation("MM4", new TransferAtmUi())

				// 查询交易记录
				.addOperation("MM5", new TradingRecordAtmUi())

				// 退卡
				.addOperation("MM6", new LoginAtmUi())

				.action();

		return dest;
	}
}

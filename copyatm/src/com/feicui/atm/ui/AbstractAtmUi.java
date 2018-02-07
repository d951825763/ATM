package com.feicui.atm.ui;

import com.feicui.atm.entity.AtmUser;
import com.feicui.atm.service.AtmService;

/*
 * 所有界面的父类
 *
 * @author Benzolamps
 */
public abstract class AbstractAtmUi {

	protected static AtmService service = new AtmService();//各个界面共享一台ATM
	protected AtmUser user;
	
	public AbstractAtmUi() {
		user = service.getCurrentUser();
	}
	
	public abstract AbstractAtmUi show();//业务显示主方法，返回值是下一个页面
}

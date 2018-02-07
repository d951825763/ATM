package com.feicui.atm;

import com.feicui.atm.admin.ui.AdminLoginAtmUi;
import com.feicui.atm.ui.AbstractAtmUi;

public class TestAdmin {
	public static void main(String[] args) {
		AbstractAtmUi ui = new AdminLoginAtmUi();//程序刚开始运行时的登录界面
		
		//显示当前界面，并将ui指向下一个界面
		
		while (ui != null){
			ui = ui.show();
		}
	}
}

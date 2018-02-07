package com.feicui.atm;

import com.feicui.atm.ui.AbstractAtmUi;
import com.feicui.atm.ui.LoginAtmUi;

public class TestUser {
	public static void main(String[] args) {
		AbstractAtmUi ui = new LoginAtmUi();//程序刚开始运行登录时的界面
		
		//显示当前界面，并将ui指向下一个界面
		
		while(ui != null) {
			ui = ui.show();
		}
	}
}

/*
a:while(ture){
	if(1){
	   b:while(ture){
		  if(1){
		      continue a;
		  }
		}
	}
}
*/

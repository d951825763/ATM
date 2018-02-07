package com.feicui.atm.service;

import java.util.ArrayList;

import com.feicui.atm.entity.AtmUser;
import com.feicui.atm.entity.UserStorage;
import com.feicui.atm.util.CommonUtil;

public class AdminService extends AtmService{

	public boolean login(String account,String password) {//判断账号密码正确性
		String account1 = CommonUtil.getMessage("AA");
		String password1 = CommonUtil.getMessage("AP");
		if (account.equals(account1) && password.equals(password1)) {
			return true;
		}
		return false;
	}
	
	public void addUser(AtmUser user) {//添加用户
		UserStorage storage = new UserStorage();
		storage.addUser(user);
	}
	
	public void removeUser(AtmUser user) {//删除用户
		UserStorage storage = new UserStorage();
		storage.removeUser(user);
	}
	
	public void modifyUser(AtmUser user) {//修改用户
        UserStorage storage = new UserStorage();
        storage.modifyUser(user);
    }
	
	public ArrayList<AtmUser> getAllUser() {//得到全部用户的集合
        UserStorage storage = new UserStorage();
        return new ArrayList<AtmUser>(storage.getAllUsers());
    }
	
	public AtmUser getUserByIdNumber(String idNumber) {
		UserStorage storage = new UserStorage();
		return storage.getUserByIdNumber(idNumber);
	}
	
	public AtmUser getUserByAccount(String account) {
		UserStorage storage = new UserStorage();
		return storage.getUserByAccount(account);
	}
}

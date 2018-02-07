package com.feicui.atm.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeSet;

import com.feicui.atm.util.CommonUtil;

public class UserStorage {
	
	private static final String FILE_NAME = 
		"asset" + File.separator + "storage.obj";//文件路径
	
	public AtmUser getUserByAccount(String account) {
		//通过账号查找User
		AtmUser user = new AtmUser(account,null);
		TreeSet<AtmUser> users = UserStorage.readUsers();
		
		if(users != null && users.contains(user)) {
			return users.floor(user).clone();//相当于使用假用户获取真用户
		}
		return null;
	}
	
	public AtmUser getUserByIdNumber(String idNumber) {
		//通过身份证号查找User
		AtmUser user = new AtmUser(idNumber);
		TreeSet<AtmUser> users = UserStorage.readUsers();
		if (users != null && users.contains(user)) {
			return users.floor(user).clone();
		}
		return null;
	}
	
	public void addUser(AtmUser user) {//添加用户
		TreeSet<AtmUser> users = UserStorage.readUsers();
		if(users != null) {
			users.add(user);
			UserStorage.writeUser(users);
		}
	}
	
	public void removeUser(AtmUser user) {//删除用户
		TreeSet<AtmUser> users = UserStorage.readUsers();
		if (users != null) {
			users.remove(user);
			UserStorage.writeUser(users);
		}
	}
	
	public void modifyUser(AtmUser newUser) {//修改用户
		TreeSet<AtmUser> users = UserStorage.readUsers();
		AtmUser oldUser = users.floor(newUser);
		oldUser.modify(newUser);
		UserStorage.writeUser(users);
	}
	
	public TreeSet<AtmUser> getAllUsers(){
		return readUsers();
	}
	
	@SuppressWarnings("unchecked")
	private static TreeSet<AtmUser> readUsers(){//读用户
		File file = new File(UserStorage.FILE_NAME);
		TreeSet<AtmUser> result = new TreeSet<AtmUser>();
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		if(file.isDirectory()) {//这是一个文件夹。
			throw new RuntimeException("这是一个文件夹");
		}
		if(!file.exists()) {//文件未创建、初始化文件夹
			UserStorage.writeUser(new TreeSet<AtmUser>());
		}else {
			try {
				fis = new FileInputStream(file);
				ois = new ObjectInputStream(fis);
				Object obj = ois.readObject();
				if (obj instanceof TreeSet<?>) {
					result = (TreeSet<AtmUser>)obj;
				}
			}catch(ClassNotFoundException|IOException e) {
				CommonUtil.printStackTrace(e);
				file.delete();
				UserStorage.writeUser(new TreeSet<AtmUser>());//文件内容错误，初始化文件
			}finally {
				try {
					if(ois != null) {
						ois.close();
					}
				}catch(IOException e) {
					CommonUtil.printStackTrace(e);
				}
			}
		}
		return result;
	}
	
	private static void writeUser(TreeSet<AtmUser> users) {//写用户
		
		if(users == null) {
			return;
		}
		File file = new File(UserStorage.FILE_NAME);
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			if (!file.exists() && !file.createNewFile()) {
				return;
			}
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(users);
		}catch(IOException e) {
			CommonUtil.printStackTrace(e);
		}finally {
			try {
				if(oos != null) {
					oos.flush();
					oos.close();
				}
				if(fos != null){
					fos.flush();
					fos.close();
				}
			}catch(IOException e) {
				CommonUtil.printStackTrace(e);
			}
		}
	}
}

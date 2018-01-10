public class User{
	//用户的账号、密码、姓名、初始金额。
	private String account = "370120180104";
	private String password = "123456";
	private String uname = "周星星";
	private double amount;
	private String cname = "达叔";
	
	//用get、set方法得到用户信息。
	public String getAccount(){
		return this.account;
	}
	public String getPassword(){
		return this.password;
	}
	public String getUname(){
		return this.uname;
	}
	public void setAmount(double amount){
		this.amount = amount;
	}
	public double getAmount(){
		return this.amount;
	}
	public String getCname(){
		return this.cname;
	}
}
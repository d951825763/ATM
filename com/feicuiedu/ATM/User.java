public class User{
	//�û����˺š����롢��������ʼ��
	private String account = "370120180104";
	private String password = "123456";
	private String uname = "������";
	private double amount;
	private String cname = "����";
	
	//��get��set�����õ��û���Ϣ��
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
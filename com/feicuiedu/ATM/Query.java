import java.util.Scanner;
public class Query{

	public void UserInfo(User user) {
        System.out.printlm("      查询业务")
		System.out.println("账户姓名:"+user.getName());
		System.out.println("余额:"+user.getAmount());
		System.out.println("1:返回上一级");
		System.out.println("2:退卡");

		int input = new Scanner(System.in).nextInt();
		if(input == 1){
			Menu menu = new Menu();
			menu.businessType();
		}else if(input == 2){
			
		}
	}
}
package com.feicui.atm.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

/*
父类的第一个泛型参数为Properties中的键值，需要将其转换为对应的值

@author Benzolamps

*/
public class PropertyOperation<R> extends Operation<String,R> {

	public PropertyOperation(R defaultR) {
        super(defaultR);
    }
    
    @Override
    protected Map<String, Supplier<R>> initialMap() { 
        // 使用LinkedHashMap防止输入提示顺序错乱
        return new LinkedHashMap<>();
    }
    
    @Override
    protected String getInput() {
        ArrayList<String> list = new ArrayList<>(map.keySet());
        // 将Set转换为ArrayList方便按索引取值
        
        for (int i = 0; i < list.size(); i++) {
            String str = CommonUtil.getMessage(list.get(i));
            CommonUtil.printLine((i + 1) + ": "+ str); // 打印选项
        }
        
        String input = CommonUtil.inputLine(null); // 获取用户的输入
        
        if (!input.matches(CommonUtil.getRegex("option"))) { 
            return null;
        } else {            
            int number = Integer.parseInt(input);
            if (number < 1 || number > list.size()) { // 不是规定的数字
                return null;
            }
            
            return list.get(number - 1); // 将用户输入的选项转换为选项数据类型
        }       
    }

    @Override
    protected void error() {
        CommonUtil.printLine("UE0");   // 打印输入有误  
    }
}

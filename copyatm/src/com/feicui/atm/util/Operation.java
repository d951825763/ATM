package com.feicui.atm.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
* 给一个选项，返回一个值，取代 switch case
* case T，return R
*		
* @author Benzolamps
*
* @param<T> 选项数据类型
* @param<R> 返回数据类型
*/
public abstract class Operation<T,R> {

	protected Map <T,Supplier<R>> map;//存储选项与返回值
	private ArrayList<Consumer<T>> actions;//额外要做的事
	private R defaultR;//默认返回值
	
	public Operation(R defaultR) {
		super();
		
		this.defaultR = defaultR;
		
		this.map = initialMap();//通过子类生成Map
		
		if(this.map == null) {
			map = new HashMap<T,Supplier<R>>();
		}
		
		this.actions = new ArrayList<Consumer<T>>();
	}
	
	public Operation<T,R> addOperation(T key,R value){
		//添加一个选项，并返回当前操作集，
		//这样设计受StringBuffer的append（）方法的启发
		map.put(key, () -> value);
		return this;
	}
	
	public Operation<T, R> addOperationIf(T key, R value, Predicate<Object> filter) {
        // 当满足条件时, 添加一个选项, 并返回当前操作集
        if (filter.test(null)) {
            map.put(key, () -> value);
        }
        return this;
    }
	
	public Operation<T,R> addOperationSupply(T key,Supplier<R> filter){
		//调用filter的get（）方法获取值
		map.put(key,filter);
		return this;
	}
	
	public Operation<T,R> addAction(Consumer<T> action){
		//添加一项额外要做的事，并返回当前操作集
		actions.add(action);
		return this;
	}
	
	protected abstract Map<T,Supplier<R>> initialMap();//初始化Map
	
	protected abstract T getInput();//获取用户的输入
	
	protected abstract void error();//输入错误时调用
	
	public final R action() {
		if(map == null || map.size() == 0) {//Map为空
			return defaultR;//返回默认值			
		}
		
		T input = getInput();//获取用户的输入
		
		actions.forEach(action -> action.accept(input));//执行额外要做的事
		
		if(input == null) {
			error();//执行错误
			return defaultR;//返回默认值
		}
		
		return map.get(input).get();//返回对应的值
	}
}

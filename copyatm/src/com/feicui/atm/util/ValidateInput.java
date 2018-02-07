package com.feicui.atm.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

public abstract class ValidateInput {

	private String message;
    private Map<Predicate<String>, String> conditions;
    
    public ValidateInput(String message) {
        this.message = message;
        conditions = new LinkedHashMap<>();
        
    }
    

    public ValidateInput addCondition(Predicate<String> condition, String error) {
    	conditions.put(condition, error);
        return this;
    }
    
    public ValidateInput addCondition(Predicate<String> condition) {
        return addCondition(condition, null);
    }
    
    public ValidateInput addRegexCondition(String regex, String error) {
        Predicate<String> condition = str -> str.matches(regex);
        return addCondition(condition, error);
    }
    
    public ValidateInput addRegexCondition(String regex) {
        return addRegexCondition(regex, null);
    }
    
    public static String executeRegex(
        String message, String regex, String error) {
        String result = new PropertyValidateInput(message)
            .addRegexCondition(regex, error)
            .execute();
        return result;
    }
    
    public static String executeRegex(String message, String regex) {
        return executeRegex(message, regex, null);
    }
    
    public static String executeInstance(
        String message, Predicate<String> condition, String error) {
        String result = new PropertyValidateInput(message)
            .addCondition(condition, error)
            .execute();
        return result;
    }
    
    public static String executeInstance(
        String message, Predicate<String> condition) {
        return executeInstance(message, condition, null);
    }
    
    protected abstract String getInput(String message);
    
    protected abstract void error(String input, String error);
    
    public String execute() {
        String input = getInput(message);

        for (Entry<Predicate<String>, String> item : conditions.entrySet()) {
            if (!item.getKey().test(input)) {
                error(input, item.getValue());
                input = execute();
                break;
            }
        }
        
        return input;    
    }
}

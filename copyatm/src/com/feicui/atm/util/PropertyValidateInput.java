package com.feicui.atm.util;

public class PropertyValidateInput extends ValidateInput {

	public PropertyValidateInput(String message) {
        super(message);
    }
    
    @Override
    public ValidateInput addRegexCondition(String regexKey, String error) {
        String regex = CommonUtil.getRegex(regexKey);
        return super.addRegexCondition(regex, error);
    }

    @Override
    protected String getInput(String message) {
        return CommonUtil.inputLine(message);
    }


    @Override
    protected void error(String input, String error) {
        CommonUtil.printLine(error, input);
        CommonUtil.printLine("UE0");      
    }
}

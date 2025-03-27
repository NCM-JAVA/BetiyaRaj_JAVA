package com.bor.rcms.response;

public class CaptchaResponsed
{private int firstValue;
private int SecondValue;
private String captchStore;


public int getFirstValue() {
	return firstValue;
}
public void setFirstValue(int firstValue) {
	this.firstValue = firstValue;
}
public int getSecondValue() {
	return SecondValue;
}
public void setSecondValue(int secondValue) {
	SecondValue = secondValue;
}
public String getCaptchStore() {
	return captchStore;
}
public void setCaptchStore(String captchStore) {
	this.captchStore = captchStore;
}
@Override
public String toString() {
	return "CaptchaResponse [firstValue=" + firstValue + ", SecondValue=" + SecondValue + ", captchStore="
			+ captchStore + "]";
}
}

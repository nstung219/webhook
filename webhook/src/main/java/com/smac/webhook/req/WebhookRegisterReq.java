package com.smac.webhook.req;

import java.util.List;

import com.smac.webhook.BO.WebhookDataRegisterBO;

public class WebhookRegisterReq {
	private String customerId;
	private String waiter;
	private List<WebhookDataRegisterBO> lst;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getWaiter() {
		return waiter;
	}
	public void setWaiter(String waiter) {
		this.waiter = waiter;
	}
	public List<WebhookDataRegisterBO> getLst() {
		return lst;
	}
	public void setLst(List<WebhookDataRegisterBO> lst) {
		this.lst = lst;
	}
}

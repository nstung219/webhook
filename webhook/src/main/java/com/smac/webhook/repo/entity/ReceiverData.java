package com.smac.webhook.repo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "receiver_data")
public class ReceiverData {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "customer_id")
	private String customerId;
	
	@Column(name = "target_name")
	private String targetName;
	
	@Column(name = "alter_name")
	private String alterName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getTargetName() {
		return targetName;
	}
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	public String getAlterName() {
		return alterName;
	}
	public void setAlterName(String alterName) {
		this.alterName = alterName;
	}
}

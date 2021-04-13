package com.smac.webhook.repo;

import java.util.List;

import com.smac.webhook.repo.entity.ReceiverData;

public interface ReceiverDataRepoCustom {
	public List<ReceiverData> getListByCusId(String customerId);
}

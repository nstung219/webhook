package com.smac.webhook.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.smac.webhook.repo.entity.ReceiverData;

public class ReceiverDataRepoImpl implements ReceiverDataRepoCustom	{

	@Autowired
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReceiverData> getListByCusId(String customerId) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT t FROM ReceiverData t");
		sb.append(" WHERE t.customerId = :customerId");
		
		Query qr = em.createQuery(sb.toString());
		
		qr.setParameter("customerId", customerId);
		
		List<ReceiverData> lst = qr.getResultList();
		
		return lst;
	}

}

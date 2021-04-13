package com.smac.webhook.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smac.webhook.repo.entity.ReceiverData;

public interface ReceiverDataRepo extends JpaRepository<ReceiverData, Integer>, ReceiverDataRepoCustom{

}

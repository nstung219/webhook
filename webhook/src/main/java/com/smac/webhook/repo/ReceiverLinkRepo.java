package com.smac.webhook.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smac.webhook.repo.entity.ReceiverLink;

public interface ReceiverLinkRepo extends JpaRepository<ReceiverLink, String>, ReceiverLinkRepoCustom{

}

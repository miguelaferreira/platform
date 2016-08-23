package com.me.miguelferreira.platform.data.repositories;

import com.me.miguelferreira.platform.model.Requester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequesterRepository extends JpaRepository<Requester, Long> {
}

//****************************************************************************//
// システム         : IotPortal
//----------------------------------------------------------------------------//
//                (c)Copyright 2018 SoftBank All rights reserved.
//============================================================================//
package com.mysunpic.app.common.repository;

import com.mysunpic.app.common.entity.TAccountLoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 */
@Repository
public interface TAccountLoginRepository extends JpaRepository<TAccountLoginEntity, Long> {

  Optional<TAccountLoginEntity> findByAccountId(String accountId);

  Optional<TAccountLoginEntity> findByAccountEmail(String accountEmail);

  boolean existsByAccountId(String accountId);

  void deleteByAccountId(String accountId);
}

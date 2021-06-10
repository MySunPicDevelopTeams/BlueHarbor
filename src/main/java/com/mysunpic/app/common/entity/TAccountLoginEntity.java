//****************************************************************************//
// システム         : IotPortal
//----------------------------------------------------------------------------//
//                (c)Copyright 2018 SoftBank All rights reserved.
//============================================================================//
package com.mysunpic.app.common.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <pre>
 * 用户信息
 * </pre>
 */
@Data
@Entity
@Table(name = "t_account_login")
@DynamicInsert
public class TAccountLoginEntity implements Serializable {

  /**
   * default serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  /**
   * 用户邮箱
   */
  @Column(name = "account_email")
  private String accountEmail;

  /**
   * 用户ID
   */
  @Column(name = "account_id")
  private String accountId;

  /**
   * 用户密码
   */
  @Column(name = "password")
  private String password;

  /**
   * 密码更新时间
   */
  @Column(name = "password_updated_at")
  private Timestamp passwordUpdatedAt;

  /**
   * 密码输入错误次数
   */
  @Column(name = "login_wrong_count")
  private Integer loginWrongCount;

  /**
   * 账户状态
   */
  @Column(name = "account_status")
  private String accountStatus;

  /**
   * 最后一次登陆时间
   */
  @Column(name = "last_login_at")
  private Timestamp lastLoginAt;

  /**
   * 创建时间
   */
  @Column(name = "created_at")
  private Timestamp createdAt;

  /**
   * 更新时间
   */
  @Column(name = "updated_at")
  private Timestamp updatedAt;

  /**
   * 更新者
   */
  @Column(name = "updated_by")
  private String updatedBy;

  /**
   * 版本
   */
  @Version
  @Column(name = "version")
  private Integer version;


}

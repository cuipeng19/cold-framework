package com.cold.framework.dao.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_warn")
public class SysWarn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 用户名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 事件类型：
     */
    @Column(name = "event_type")
    private String eventType;

    /**
     * 是否发送邮件：1-是,0-否
     */
    @Column(name = "email_state")
    private Integer emailState;

    /**
     * 是否发送短信：1-是,0-否
     */
    @Column(name = "phone_state")
    private Integer phoneState;

    /**
     * 状态：1-有效,0-无效
     */
    private Integer state;

    /**
     * 创建时间
     */
    @Column(name = "create_at")
    private Date createAt;

    /**
     * 更新时间
     */
    @Column(name = "last_update")
    private Date lastUpdate;

    public SysWarn(Long id, String userId, String userName, String email, String phone, String eventType, Integer emailState, Integer phoneState, Integer state, Date createAt, Date lastUpdate) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.eventType = eventType;
        this.emailState = emailState;
        this.phoneState = phoneState;
        this.state = state;
        this.createAt = createAt;
        this.lastUpdate = lastUpdate;
    }

    public SysWarn() {
        super();
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户编号
     *
     * @return user_id - 用户编号
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户编号
     *
     * @param userId 用户编号
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取用户名称
     *
     * @return user_name - 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名称
     *
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取手机号
     *
     * @return phone - 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取事件类型：
     *
     * @return event_type - 事件类型：
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * 设置事件类型：
     *
     * @param eventType 事件类型：
     */
    public void setEventType(String eventType) {
        this.eventType = eventType == null ? null : eventType.trim();
    }

    /**
     * 获取是否发送邮件：1-是,0-否
     *
     * @return email_state - 是否发送邮件：1-是,0-否
     */
    public Integer getEmailState() {
        return emailState;
    }

    /**
     * 设置是否发送邮件：1-是,0-否
     *
     * @param emailState 是否发送邮件：1-是,0-否
     */
    public void setEmailState(Integer emailState) {
        this.emailState = emailState;
    }

    /**
     * 获取是否发送短信：1-是,0-否
     *
     * @return phone_state - 是否发送短信：1-是,0-否
     */
    public Integer getPhoneState() {
        return phoneState;
    }

    /**
     * 设置是否发送短信：1-是,0-否
     *
     * @param phoneState 是否发送短信：1-是,0-否
     */
    public void setPhoneState(Integer phoneState) {
        this.phoneState = phoneState;
    }

    /**
     * 获取状态：1-有效,0-无效
     *
     * @return state - 状态：1-有效,0-无效
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置状态：1-有效,0-无效
     *
     * @param state 状态：1-有效,0-无效
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取创建时间
     *
     * @return create_at - 创建时间
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * 设置创建时间
     *
     * @param createAt 创建时间
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * 获取更新时间
     *
     * @return last_update - 更新时间
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     * 设置更新时间
     *
     * @param lastUpdate 更新时间
     */
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
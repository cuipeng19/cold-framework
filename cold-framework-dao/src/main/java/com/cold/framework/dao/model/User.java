package com.cold.framework.dao.model;

import java.util.Date;
import javax.persistence.*;

public class User {
    /**
     * 用户编号
     */
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    /**
     * token
     */
    private String token;

    /**
     * 用户帐号
     */
    private String account;

    /**
     * 用户名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户头像
     */
    @Column(name = "user_icon")
    private String userIcon;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 手机号
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * 用户类型
     */
    private Integer type;

    /**
     * 用户状态
     */
    private Integer state;

    /**
     * 最后登录IP
     */
    @Column(name = "last_login_ip")
    private String lastLoginIp;

    /**
     * 最后登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 注册IP
     */
    @Column(name = "create_ip")
    private String createIp;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private Date createdAt;

    /**
     * 更新时间
     */
    @Column(name = "last_update")
    private Date lastUpdate;

    public User(String userId, String token, String account, String userName, String userIcon, String password, String phoneNumber, Integer type, Integer state, String lastLoginIp, Date lastLoginTime, String createIp, Date createdAt, Date lastUpdate) {
        this.userId = userId;
        this.token = token;
        this.account = account;
        this.userName = userName;
        this.userIcon = userIcon;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.state = state;
        this.lastLoginIp = lastLoginIp;
        this.lastLoginTime = lastLoginTime;
        this.createIp = createIp;
        this.createdAt = createdAt;
        this.lastUpdate = lastUpdate;
    }

    public User() {
        super();
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
     * 获取token
     *
     * @return token - token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置token
     *
     * @param token token
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * 获取用户帐号
     *
     * @return account - 用户帐号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置用户帐号
     *
     * @param account 用户帐号
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
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
     * 获取用户头像
     *
     * @return user_icon - 用户头像
     */
    public String getUserIcon() {
        return userIcon;
    }

    /**
     * 设置用户头像
     *
     * @param userIcon 用户头像
     */
    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon == null ? null : userIcon.trim();
    }

    /**
     * 获取用户密码
     *
     * @return password - 用户密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置用户密码
     *
     * @param password 用户密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取手机号
     *
     * @return phone_number - 手机号
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置手机号
     *
     * @param phoneNumber 手机号
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    /**
     * 获取用户类型
     *
     * @return type - 用户类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置用户类型
     *
     * @param type 用户类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取用户状态
     *
     * @return state - 用户状态
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置用户状态
     *
     * @param state 用户状态
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取最后登录IP
     *
     * @return last_login_ip - 最后登录IP
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * 设置最后登录IP
     *
     * @param lastLoginIp 最后登录IP
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    /**
     * 获取最后登录时间
     *
     * @return last_login_time - 最后登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置最后登录时间
     *
     * @param lastLoginTime 最后登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取注册IP
     *
     * @return create_ip - 注册IP
     */
    public String getCreateIp() {
        return createIp;
    }

    /**
     * 设置注册IP
     *
     * @param createIp 注册IP
     */
    public void setCreateIp(String createIp) {
        this.createIp = createIp == null ? null : createIp.trim();
    }

    /**
     * 获取创建时间
     *
     * @return created_at - 创建时间
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * 设置创建时间
     *
     * @param createdAt 创建时间
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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
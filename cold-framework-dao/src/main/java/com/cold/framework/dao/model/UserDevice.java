package com.cold.framework.dao.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_device")
public class UserDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * token
     */
    private String token;

    /**
     * 设备ID
     */
    @Column(name = "device_id")
    private String deviceId;

    /**
     * 手机号码
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * 状态：0-无效，1-正常
     */
    private Integer status;

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

    public UserDevice(Long id, String userId, String token, String deviceId, String phoneNumber, Integer status, Date createdAt, Date lastUpdate) {
        this.id = id;
        this.userId = userId;
        this.token = token;
        this.deviceId = deviceId;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.createdAt = createdAt;
        this.lastUpdate = lastUpdate;
    }

    public UserDevice() {
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
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
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
     * 获取设备ID
     *
     * @return device_id - 设备ID
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * 设置设备ID
     *
     * @param deviceId 设备ID
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    /**
     * 获取手机号码
     *
     * @return phone_number - 手机号码
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置手机号码
     *
     * @param phoneNumber 手机号码
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    /**
     * 获取状态：0-无效，1-正常
     *
     * @return status - 状态：0-无效，1-正常
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：0-无效，1-正常
     *
     * @param status 状态：0-无效，1-正常
     */
    public void setStatus(Integer status) {
        this.status = status;
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
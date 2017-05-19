package com.yckj.school.service;

import com.yckj.school.service.dto.UserDto;
import com.yckj.school.service.dto.UserPageDto;

public interface UserService {
	/**
	 * 登录
	 * @param dto
	 * @return
	 */
	boolean login(UserDto dto);
	/**
	 * 修改密码
	 * @param dto
	 * @return
	 */
	boolean updPasswd(UserDto dto);
	/**
	 * 分页查询用户列表
	 * @param dto
	 * @return
	 */
	UserPageDto listAllUserByPage(UserPageDto dto);
	/**
	 * 添加用户
	 */
	boolean addUser(UserDto dto);
	/**
     * 删除用户
     */
    boolean deleteUser(String userId);
	/**
     * 修改用户
     */
    boolean updateUser(UserDto dto);
    /**
     * 查询用户
     */
    UserDto queryUser(String userId);
}

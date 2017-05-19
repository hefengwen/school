package com.yckj.school.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.yckj.school.common.constant.Constants;
import com.yckj.school.common.util.PropertyUtils;
import com.yckj.school.dao.MajorMapper;
import com.yckj.school.dao.UserMapper;
import com.yckj.school.domain.Major;
import com.yckj.school.domain.User;
import com.yckj.school.exception.SchoolErrorType;
import com.yckj.school.exception.SchoolException;
import com.yckj.school.service.UserService;
import com.yckj.school.service.dto.UserDto;
import com.yckj.school.service.dto.UserPageDto;


@Service
public class UserServiceImpl implements UserService{
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
    private MajorMapper majorMapper;
	
	@Override
	public boolean login(UserDto dto) {
	    logger.info("UserServiceImpl login start ... ...");
		User user = userMapper.selectByPrimaryKey(dto.getUserId());
		if(user==null||user.getStatue().equals(Constants.NO))
			return false;
//		logger.info("md5 pwd=["+DigestUtils.md5DigestAsHex(dto.getPasswd().getBytes())+"]");
		if(!user.getPasswd().equals(DigestUtils.md5DigestAsHex(dto.getPasswd().getBytes()))){
			return false;
		}
		try {
			PropertyUtils.propertyCopy(dto, user);
			dto.setPasswd(null);
		} catch (Exception e) {
			logger.error("",e);
			throw new SchoolException(SchoolErrorType.err_system, null);
		}
		return true;
	}
	@Override
    public boolean updPasswd(UserDto dto) {
	    logger.info("UserServiceImpl updPasswd start ... ...");
        User user = new User();
        user.setUserId(dto.getUserId());
        user.setPasswd(DigestUtils.md5DigestAsHex(dto.getPasswd().getBytes()));
        int cnt = userMapper.updateByPrimaryKeySelective(user);
        return cnt==1;
    }
	@Override
	public UserPageDto listAllUserByPage(UserPageDto dto) {
	    logger.info("UserServiceImpl listAllUserByPage start ... ...");
		try {
			Map<String,Object> map = new HashMap<>();
			map.put(Constants.START, (dto.getCurPage()-1)*dto.getPageCount());
			map.put(Constants.COUNT, dto.getPageCount());
			map.putAll(PropertyUtils.objectToMap(dto.getCondition()));
			
			if(dto.getNeedTotal()){
				int totalCount = userMapper.selectTotalCount(map);
				dto.setTotalCount(totalCount);
				dto.setTotalPageCount(totalCount%dto.getPageCount()==0?totalCount/dto.getPageCount():totalCount/dto.getPageCount()+1);
			}
			
			List<User> users = userMapper.selectByPage(map);
			List<Map<String, Object>> userList = new ArrayList<>();
			for(User u:users){
//				logger.info("当前用户ID:"+u.getUserId());
				Map<String, Object> m = PropertyUtils.objectToMap(u);
				Major major = majorMapper.selectByPrimaryKey(u.getMajorId());
				m.put("majorName", major==null?"":major.getName());
				User teacher = this.queryUser(u.getTeacherId());
				m.put("teacherName", teacher==null?"":teacher.getName());
				userList.add(m);
			}
			dto.setUserList(userList);
		} catch (Exception e) {
			logger.error("",e);
			throw new SchoolException(SchoolErrorType.err_system, null);
		}
		return dto;
	}
    @Override
    public boolean addUser(UserDto dto) {
        logger.info("UserServiceImpl addUser start ... ...");
        try {
            User user = new User();
            PropertyUtils.propertyCopy(user, dto);
            user.setPasswd(DigestUtils.md5DigestAsHex(dto.getPasswd().getBytes()));
            user.setCreateTime(new Date());
            int cnt = userMapper.insertSelective(user);
            return cnt==1;
        }
        catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
    }
    @Override
    public boolean deleteUser(String userId) {
        logger.info("UserServiceImpl deleteUser start ... ...");
        User user = new User();
        user.setUserId(userId);
        user.setStatue(Constants.NO);
        int cnt = userMapper.updateByPrimaryKeySelective(user);
        return cnt == 1;
    }
    @Override
    public boolean updateUser(UserDto dto) {
        logger.info("UserServiceImpl updateUser start ... ...");
        try {
            User user = new User();
            PropertyUtils.propertyCopy(user, dto);
            int cnt = userMapper.updateByPrimaryKeySelective(user);
            return cnt==1;
        }
        catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
    }
    @Override
    public UserDto queryUser(String userId) {
        logger.info("UserServiceImpl queryUser start ... ...");
        try {
            User user = userMapper.selectByPrimaryKey(userId);
            if(user==null)
                return null;
            UserDto dto = new UserDto();
            PropertyUtils.propertyCopy(dto, user);
            return dto;
        }
        catch (Exception e) {
            logger.error("",e);
            throw new SchoolException(SchoolErrorType.err_system, null);
        }
    }

}

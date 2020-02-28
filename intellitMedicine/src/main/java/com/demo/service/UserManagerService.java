package com.demo.service;

import com.demo.dao.IUserDao;
import com.demo.entity.Doctor;
import com.demo.entity.Page;
import com.demo.entity.Root;
import com.demo.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserManagerService {
    @Resource
    private IUserDao userDao;

    /**
     * 医生注册
     * @param password
     * @return
     */
    public void DoctorRegister(String name,String password,String sex,String institution,String office,String phone,String paperAdd,int flag,String IdCard,int roleType){
        System.out.println(name+""+phone);
        System.out.println("ins===="+institution);
        int hosId = userDao.hosId(institution);
        userDao.doctorRegister(name,password,sex,hosId,office,phone,paperAdd,flag,IdCard,roleType);
     //  userDao.userRegister(name,phone,password,communityID,sex);
    }
    /**
     * 用户注册
     * @param password
     * @return
     */
    public void UserRegister(String name,String phone,String password,String community,String sex,int roleType){
        System.out.println(name+""+phone);
        int communityID = userDao.communityId(community) ;
        System.out.println(communityID);
        userDao.userRegister(name,phone,password,communityID,sex,roleType);
    }
    /**
     * root登录
     * @param rootName
     * @param password
     * @return
     */
    public Root UserLogin(String rootName, String password) {
        Root user = userDao.userLogin(rootName, password);
        return user;
    }
//
    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    public User UserLogin1(String userName, String password){
        User user = userDao.userLogin1(userName,password);
        return user;
    }

    /**
     * 医生登录
     * @param doctorName
     * @param password
     * @return
     */
    public Doctor DoctorLogin(String doctorName, String password){
        Doctor doctor = userDao.doctorLogin(doctorName,password);
        return doctor;
    }
    //查询用户是否存在
    public int AccountCheak(String account) {
        User user = userDao.queryUser(account);
        //return user;
        if(user==null){
            return 0;
        } else {
            return 1;
        }
    }

    public int DoctorNameCheck(String account) {
       Doctor doctor = userDao.queryDoctorByName(account);
        //return user;
        if(doctor==null){
            return 0;
        } else {
            return 1;
        }
    }

}

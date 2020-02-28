package com.demo.dao;

import com.demo.entity.Doctor;
import com.demo.entity.Root;
import com.demo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserDao {
//	/**
//	 * 增加用户
//	 * @param user
//	 * @return
//	 */
//	public void addUser(User user);
//
//    /**
//     * 增加用户
//     * @param account
//     * @param password
//     */
//	public void addUser2(@Param("account") String account, @Param("password") String password);
//	/**
//	 * 更新用户
//	 * @param user
//	 * @return
//	 */
//	public void updateUser(User user);
//	/**
//	 * 删除用户
//	 * @param id
//	 * @return
//	 */
//	public void delUserById(long id);
	/**
	 * root登录
	 * @param rootName
	 * @param password
	 * @return
	 */
	//@Select("select * from userlogin where account=#{account} and password=#{password};")
	//@ResultType(User.class)
	//public User userLogin(@Param("account") String account, @Param("password") String password);
    public Root userLogin(@Param("rootName") String rootName, @Param("password") String password);

	/**
	 * user登录
	 * @param userName
	 * @param password
	 * @return
	 */
	public User userLogin1(@Param("userName") String userName, @Param("password") String password);
//    用户注册
	public void userRegister(@Param("name") String name, @Param("phone") String phone, @Param("password") String password, @Param("communityId") int communityId, @Param("sex") String sex,@Param("roleType") int roleType);
	// 查询社区id
	public int communityId(String comName);
	/**
	 * 医生登录
	 * @param doctorName
	 * @param password
	 * @return
	 */
	public Doctor doctorLogin(@Param("doctorName") String doctorName, @Param("password") String password);
//医生注册
    public void doctorRegister(@Param("name") String name,@Param("password") String password,@Param("sex") String sex,@Param("hosId") int hosId,@Param("office") String office, @Param("phone") String phone, @Param("paperAdd") String paperAdd,@Param("check_2") int check_2,@Param("IdCard") String IdCard,@Param("roleType") int roleType);
//根据机构名查询机构Id
	public int hosId(@Param("institution") String institution);

    public User queryUser(@Param("account") String account);

	public Doctor queryDoctorByName(@Param("account") String account);
	/**
	 * 查询用户
	 * @param
	 * @return
	 */
	//@Select("SELECT * FROM userlogin WHERE id = #{value};")
//	public User queryById(long id);
//	/**
//	 * 查询全部用户
//	 * @return
//	 */
//	public List<User> quertAllUser();
//    /**
//     * 按条件分页查询多个用户
//     * @param user
//     * @return
//     */
//	public List<Object> queryUserList(@Param("user") User user, @Param("startRow") int startRow, @Param("row4Page") int row4Page);
//
//	/**
//	 * 按条件查询用户的数量
//	 * @param user
//	 * @return
//	 */
//	public int queryAllUserCount(@Param("user") User user);
//	/**
//	 * 检查帐号是否存在
//	 * @param account
//	 * @return
//	 */
//	public int AccountCheak(String account);
}

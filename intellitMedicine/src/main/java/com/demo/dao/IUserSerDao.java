package com.demo.dao;

import com.demo.entity.Doctor;
import com.demo.entity.Onlineconsultingrec;
import com.demo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IUserSerDao {


    /*
     * 插入健康档案
     */
    void insertHealthReport(@Param("userId") int userId, @Param("weight") String weight,
                            @Param("height") String height, @Param("smoking") int smoking,
                            @Param("drinking") int drinking, @Param("diet") int diet,
                            @Param("excretion") int excretion, @Param("allergy") int allergy
    );

    /*
     * 插入个人信息
     */
//    void updateUserInfor(@Param("userId") int userId,@Param("userName") String userName, @Param("sex") String sex, @Param("year") String year,
//                          @Param("phone") String phone, @Param("homeAdd") String homeAdd,
//                         @Param("communityId") int communityId, @Param("headAdd") String headAdd);
    /**
     * 按条件分页查询多个医生
     * @param
     * @return
     */
    public List<Object> queryUserList(@Param("startRow")int startRow, @Param("row4Page")int row4Page);

    /**
     * 按条件查询医生的数量
     */
     public int queryAllUserCount();

    /**
     * 按条件分页查询某个部门的医生
     * @param
     * @return
     */
    List<Object> queryDepartmentList(@Param("startRow") int startRow,@Param("row4Page") int row4Page,@Param("department") String department);
    /**
     * 按条件查询某部门医生的数量
     */
    int queryAlldepartmentCount(@Param("department") String department);

    public Doctor queryDocById(@Param("id")int id);

    void insertAskForm(@Param("userId") int userId,@Param("doctorId") int doctorId,@Param("nowDate") Date nowDate, @Param("cons") String cons,@Param("examineAdd") String examineAdd,@Param("state") String state);
//查询所有的药品
    public List<Object> queryAllMediList(@Param("startRow") int startRow,@Param("row4Page") int row4Page);
  //查询所有的药品数量
    public int queryAllMediCount();
//查询具有指定功效的药品
    public List<Object> queryMediListByEff(@Param("startRow") int startRow, @Param("row4Page")int row4Page,@Param("symptom") String symptom);
    //查询具有指定功效药效的总数量

    public int queryMediByEffCount(@Param("symptom") String symptom);

//根据标签查询药品
    public  List<Object> queryMediListByLabel(@Param("startRow")int startRow, @Param("row4Page")int row4Page,@Param("label") String label);
//查询指定标签的药品数量
    public  int queryMediByLabelCount(@Param("label") String label);

    public  List<Object> queryInquiryByUserId(@Param("startRow")int startRow, @Param("row4Page")int row4Page,@Param("userId") int userId);

    public int queryInquiryByUserIdCount(@Param("userId") int userId);

    public Onlineconsultingrec queryAskDetialById(@Param("inquiryId") int inquiryId);

    //根据名字查询药品
    public List<Object> queryMediListByName(@Param("startRow") int startRow, @Param("row4Page")int row4Page,@Param("queryMedicineName") String queryMedicineName);

    //查询该名字药品总数
    public int queryMediByNameCount(@Param("queryMedicineName") String queryMedicineName);


    public User queryUserById(@Param("userId") int userId);

    //修改用户个人信息
    void updateUserInfor(@Param("userId") int userId,@Param("userName") String userName,@Param("sex") String sex,@Param("date") Date date,@Param("phone") String phone,@Param("homeAdd") String homeAdd,@Param("headAdd") String headAdd);



    //韩；
     /*
    查询医生信息
    */
    int queryAllDocCount();


    /*
     * 插入上门服务申请
     */
    void insertAssign(@Param("rootId") int rootId, @Param("doctorId") int doctorId,
                      @Param("userId") int userId, @Param("applyIntro") String applyIntro,
                      @Param("applyTime") String applyTime,@Param("is_checked") String is_checked
    );
    List<Object> queryAllDocList(@Param("startRow") int startRow, @Param("row4Page") int row4Page);

    /*按条件查询*/
    int queryDocCount(@Param("type1") int type1,@Param("type2")int type2);

    List<Object> queryDocList(@Param("type1") int type1, @Param("type2")int type2,@Param("startRow") int startRow, @Param("row4Page") int row4Page);


    /*
     * 查询社区信息
     */
    int queryComCount();

    List<Object>queryComList(@Param("startRow") int startRow, @Param("row4Page") int row4Page);


    /*
     * 查询养老机构信息
     */
    int queryPensionCount();

    List<Object>queryPensionList(@Param("startRow") int startRow, @Param("row4Page") int row4Page);
}

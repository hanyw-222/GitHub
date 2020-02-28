package com.demo.dao;

import com.demo.entity.userAgeCount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IRootSerDao {
    //

    public int queryDoctorUnreplyed();
    public int queryDoctorReplyed();

    //根据名字查询药品
    public List<Object> queryMediListByName(@Param("startRow") int startRow, @Param("row4Page")int row4Page, @Param("queryMedicineName") String queryMedicineName);

    //查询该名字药品总数
    public int queryMediByNameCount(@Param("queryMedicineName") String queryMedicineName);

    //增加药品
    public  void insertMedicine(@Param("medicineName") String medicineName,@Param("medicineLabel") String medicineLabel,@Param("medicineEffect") String medicineEffect, @Param("medicinePro") String medicinePro, @Param("medicineHeadImg1") String medicineHeadImg1);

    public void deleteMedicineById(@Param("medicineId") int medicineId);

    // 查询所有已注册的医生
    public List<Object> queryDocCheckedList(@Param("startRow") int startRow, @Param("row4Page") int row4Page);

    //查询所有已注册医生总数
    public int queryDocCheckedCount();

    //通过名字查询已注册的医生
    public List<Object> queryDocListByName(@Param("startRow")int startRow, @Param("row4Page") int row4Page, @Param("queryDoctorName") String queryDoctorName);

    //查询改名字医生的总数
    public int queryDocByNameCount(@Param("queryDoctorName") String queryDoctorName);

    public void deleteDoctorById(@Param("doctorId") int doctorId);

    //分页查询所有未审核的医生
    public List<Object> queryDocUncheckedList(@Param("startRow")int startRow,@Param("row4Page") int row4Page);

    //查询所有未神的医生的总数
    public int queryDocUncheckedCount();

    //管理员审核医生通过
    public void updateRootPassDoctor(@Param("doctorId") int doctorId);

    public void addCourse(@Param("courseName") String courseName,@Param("courseType") String courseType,@Param("courseProFlag") int courseProFlag,@Param("courseIntro") String courseIntro, @Param("courseAdd") String courseAdd);

    //管理员查询课程
    public List<Object> queryCourseList(@Param("startRow")int startRow,@Param("row4Page") int row4Page);

    //管理员查询课程总数
    public int queryCourseCount();

    public List<Object> queryCourseListByName(@Param("startRow")int startRow,@Param("row4Page") int row4Page,@Param("queryCourseName") String queryCourseName);

    public int queryCourseCountByName(@Param("queryCourseName") String queryCourseName);

    public void deleteCourseById(@Param("courseId") int courseId);

    //统计用户各个年龄段的人数；
    public userAgeCount ageCount();

    //统计用户人数
    public int getUserCount();

    //统计医生人数
    public int getDoctorCount();

    //



    //韩
    int queryAllAssignCount();

    List<Object> queryAllAssignList(@Param("startRow") int startRow, @Param("row4Page") int row4Page);

    int queryAllUsersCount3(@Param("userName") String userName);

    List<Object> queryAllUsersList3(@Param("userName") String userName, @Param("startRow") int startRow, @Param("row4Page") int row4Page);

    int queryAllUsersCount2(@Param("communityId") int communityId);

    List<Object> queryAllUsersList2(@Param("communityId") int communityId, @Param("startRow") int startRow, @Param("row4Page") int row4Page);

    int queryAllUsersCount();

    List<Object> queryAllUsersList(@Param("startRow") int startRow, @Param("row4Page") int row4Page);

    void deleteAssign(@Param("applyId") int applyId);


    void insertHospital(@Param("hosName") String hosName, @Param("hosDepartment") String hosDepartment,
                        @Param("hospitalGen") String hospitalGen);

    int queryAllHospitalCount();

    void deleteHospital(@Param("hosId") int hosId);

    List<Object> queryAllHospitalList(@Param("startRow") int startRow, @Param("row4Page") int row4Page);

    List<Object> queryOneHosList(@Param("hosName") String hosName, @Param("startRow") int startRow, @Param("row4Page") int row4Page);

    int queryOneHosCount(@Param("hosName") String hosName);


    void insertPension(@Param("pensionName") String pensionName, @Param("pensionIntro") String pensionIntro);

    int queryAllPensionCount();

    void deletePension(@Param("pensionId") int pensionId);

    List<Object> queryAllPensionList(@Param("startRow") int startRow, @Param("row4Page") int row4Page);

    List<Object> queryOnePensionList(@Param("pensionName") String pensionName, @Param("startRow") int startRow, @Param("row4Page") int row4Page);

    int queryOnePensionCount(@Param("pensionName") String pensionName);


    void insertCommun(@Param("comName") String comName, @Param("comInfor") String comInfor);

    int queryAllCommunCount();

    void deleteCommun(@Param("communityId") int communityId);

    List<Object> queryAllCommunList(@Param("startRow") int startRow, @Param("row4Page") int row4Page);

    List<Object> queryOneCommunList(@Param("comName") String comName, @Param("startRow") int startRow, @Param("row4Page") int row4Page);

    int queryOneCommunCount(@Param("comName") String comName);




    List<Object> selectCommunityList();


    public int queryzhuren();

    int queryfuzhuren();

    int queryzhuzhiyishi();

    int queryyishi();

    int getInquiryData(@Param("type") String type);

}

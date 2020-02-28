package com.demo.service;

import com.demo.dao.IRootSerDao;
import com.demo.entity.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RootService {
    @Resource
    private IRootSerDao rootSerDao;

    @Resource
    private IRootSerDao rootDao;


    public int getDoctorunReplyed() {
        int doctorUnreplyed = rootSerDao.queryDoctorUnreplyed();
        return doctorUnreplyed;
    }
    //
    public int getDoctorReplyed() {
       int doctorReplyed = rootSerDao.queryDoctorReplyed();

       return doctorReplyed;
    }
    //增加药品
    public void addMedicine(String medicineName, String medicineLabel, String medicineEffect, String medicinePro, String medicineHeadImg1) {
         rootSerDao.insertMedicine(medicineName,medicineLabel,medicineEffect,medicinePro,medicineHeadImg1);
    }


    //根据名称查询药品
    public Page queryMediByName(int curPage, int row4Page, String queryMedicineName) {
        int startRow = (curPage-1)*row4Page;    //起始行

        List<Object> userList = rootSerDao.queryMediListByName(startRow,row4Page,queryMedicineName);
        int userCount = rootSerDao.queryMediByNameCount(queryMedicineName);
        int maxPage = userCount%row4Page>0?userCount/row4Page+1:userCount/row4Page;
        Page page = new Page();
        page.setMaxPage(maxPage);
        page.setObjList(userList);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);
        System.out.println(page);
        return page;
    }


    public void deleteMedicineById(int medicineId) {
        rootSerDao.deleteMedicineById(medicineId);
    }

//    管理员通过名字查找已注册的医生
    public Page queryDoctorByName(int curPage, int row4Page, String queryDoctorName) {
        int startRow = (curPage-1)*row4Page;    //起始行

        List<Object> userList = rootSerDao.queryDocListByName(startRow,row4Page,queryDoctorName);
        int userCount = rootSerDao.queryDocByNameCount(queryDoctorName);
        int maxPage = userCount%row4Page>0?userCount/row4Page+1:userCount/row4Page;
        Page page = new Page();
        page.setMaxPage(maxPage);
        page.setObjList(userList);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);
        System.out.println(page);
        return page;
    }

     //管理员查询所有已注册医生
    public Page queryAllDoctor(int curPage, int row4Page) {
        int startRow = (curPage-1)*row4Page;    //起始行

        List<Object> userList = rootSerDao.queryDocCheckedList(startRow,row4Page);
        int userCount = rootSerDao.queryDocCheckedCount();
        int maxPage = userCount%row4Page>0?userCount/row4Page+1:userCount/row4Page;
        Page page = new Page();
        page.setMaxPage(maxPage);
        page.setObjList(userList);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);
        System.out.println(page);
        return page;
    }

    //管理员删除医生
    public void deleteDoctorById(int doctorId) {
        rootSerDao.deleteDoctorById(doctorId);
    }

    //查询所有未审核的医生
    public Page queryAllUncheckDoc(int curPage, int row4Page) {
        int startRow = (curPage-1)*row4Page;    //起始行
        System.out.println("=========test");
        List<Object> userList = rootSerDao.queryDocUncheckedList(startRow,row4Page);
        int userCount = rootSerDao.queryDocUncheckedCount();
        int maxPage = userCount%row4Page>0?userCount/row4Page+1:userCount/row4Page;
        Page page = new Page();
        page.setMaxPage(maxPage);
        page.setObjList(userList);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);
        System.out.println(page);
        return page;
    }

    //管理员审核医生通过
    public void rootPassDoctor(int doctorId) {
        rootSerDao.updateRootPassDoctor(doctorId);

    }

    public void addCourse(String courseName, String courseType, int courseProFlag, String courseIntro, String courseAdd) {
        System.out.println("courseIntro==="+courseIntro);
        rootSerDao.addCourse(courseName,courseType,courseProFlag,courseIntro,courseAdd);
    }

    //管理员查询课程
    public Page queryAllCourse(int curPage, int row4Page) {
        int startRow = (curPage-1)*row4Page;    //起始行
        System.out.println("=========test");
        List<Object> userList = rootSerDao.queryCourseList(startRow,row4Page);
        int userCount = rootSerDao.queryCourseCount();
        int maxPage = userCount%row4Page>0?userCount/row4Page+1:userCount/row4Page;
        Page page = new Page();
        page.setMaxPage(maxPage);
        page.setObjList(userList);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);
        System.out.println(page);
        return page;
    }


    public Page queryCourseByName(int curPage, int row4Page, String queryCourseName) {
        int startRow = (curPage-1)*row4Page;    //起始行
        System.out.println("=========test");
        List<Object> userList = rootSerDao.queryCourseListByName(startRow,row4Page,queryCourseName);
        int userCount = rootSerDao.queryCourseCountByName(queryCourseName);
        int maxPage = userCount%row4Page>0?userCount/row4Page+1:userCount/row4Page;
        Page page = new Page();
        page.setMaxPage(maxPage);
        page.setObjList(userList);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);
        System.out.println(page);
        return page;
    }

    public void deleteCourse(int courseId) {
        rootSerDao.deleteCourseById(courseId);

    }

    public userAgeCount userAgeCount() {

        userAgeCount ageCount = rootSerDao.ageCount();

        return ageCount;
    }

    public int getUserCount() {
        int userCount = rootSerDao.getUserCount();

        return  userCount;
    }

    public int getDoctorCount() {
        int dcotorCount = rootSerDao.getDoctorCount();

        return  dcotorCount;
    }




    //韩
    //管理员查看服务请求
    public Page queryAllAssignList(int curPage, int row4Page) {

        int startRow = (curPage - 1) * row4Page;
        Page page = new Page();
        List<Object> list = rootDao.queryAllAssignList(startRow, row4Page);
        System.out.println(list);
        int count = rootDao.queryAllAssignCount();
        int maxPage = count % row4Page > 0 ? count / row4Page + 1 : count / row4Page;
        page.setMaxPage(maxPage);
        page.setObjList(list);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);

        return page;

    }


    //管理员查看用户信息
    public Page queryAllUsersList(int curPage, int row4Page) {

        int startRow = (curPage - 1) * row4Page;
        Page page = new Page();
        List<Object> list = rootDao.queryAllUsersList(startRow, row4Page);
        System.out.println(list);
        int count = rootDao.queryAllUsersCount();
        int maxPage = count % row4Page > 0 ? count / row4Page + 1 : count / row4Page;
        page.setMaxPage(maxPage);
        page.setObjList(list);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);

        return page;

    }


    public Page queryAllUsersList2(int communityId, int curPage, int row4Page) {

        int startRow = (curPage - 1) * row4Page;
        Page page = new Page();
        List<Object> list = rootDao.queryAllUsersList2(communityId, startRow, row4Page);
        System.out.println(list);
        int count = rootDao.queryAllUsersCount2(communityId);
        int maxPage = count % row4Page > 0 ? count / row4Page + 1 : count / row4Page;
        page.setMaxPage(maxPage);
        page.setObjList(list);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);

        return page;

    }


    public Page queryAllUsersList3(String userName, int curPage, int row4Page) {

        int startRow = (curPage - 1) * row4Page;
        Page page = new Page();
        List<Object> list = rootDao.queryAllUsersList3(userName, startRow, row4Page);
        System.out.println(list);
        int count = rootDao.queryAllUsersCount3(userName);
        int maxPage = count % row4Page > 0 ? count / row4Page + 1 : count / row4Page;
        page.setMaxPage(maxPage);
        page.setObjList(list);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);

        return page;

    }

    /*
     * 医疗管理平台
     * 查询医疗机构
     * 新增医疗机构
     * */
    public Page queryAllHospitalList(int curPage, int row4Page) {
        int startRow = (curPage - 1) * row4Page;
        Page page = new Page();
        List<Object> list = rootDao.queryAllHospitalList(startRow, row4Page);
        System.out.println(list);
        int count = rootDao.queryAllHospitalCount();
        int maxPage = count % row4Page > 0 ? count / row4Page + 1 : count / row4Page;
        page.setMaxPage(maxPage);
        page.setObjList(list);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);

        return page;
    }

    public Page queryOneHosList(String hosName, int curPage, int row4Page) {

        int startRow = (curPage - 1) * row4Page;
        Page page = new Page();
        List<Object> list = rootDao.queryOneHosList(hosName,startRow, row4Page);
        System.out.println(list);
        int count = rootDao.queryOneHosCount(hosName);
        int maxPage = count % row4Page > 0 ? count / row4Page + 1 : count / row4Page;
        page.setMaxPage(maxPage);
        page.setObjList(list);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);

        return page;

    }

    public void insertHospital(Hospital hospital) {
        rootDao.insertHospital(hospital.getHosName(), hospital.getHosDepartment(), hospital.getHospitalGen());
    }


    /*
     * 养老机构管理平台
     * 查询医疗机构
     * 新增医疗机构
     * */
    public Page queryAllPensionList(int curPage, int row4Page) {

        int startRow = (curPage - 1) * row4Page;
        Page page = new Page();
        List<Object> list = rootDao.queryAllPensionList(startRow, row4Page);
        System.out.println(list);
        int count = rootDao.queryAllPensionCount();
        int maxPage = count % row4Page > 0 ? count / row4Page + 1 : count / row4Page;
        page.setMaxPage(maxPage);
        page.setObjList(list);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);

        return page;
    }

    public Page queryOnePensionList(String pensionName, int curPage, int row4Page) {

        int startRow = (curPage - 1) * row4Page;
        Page page = new Page();
        List<Object> list = rootDao.queryOnePensionList(pensionName,startRow, row4Page);
        System.out.println(list);
        int count = rootDao.queryOnePensionCount(pensionName);
        int maxPage = count % row4Page > 0 ? count / row4Page + 1 : count / row4Page;
        page.setMaxPage(maxPage);
        page.setObjList(list);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);

        return page;

    }

    public void insertPension(Pension pension) {
        rootDao.insertPension(pension.getPensionName(), pension.getPensionIntro());
    }


    /*
     * 社区管理平台
     * 查询社区机构
     * 新增社区机构
     * */
    public Page queryAllCommunList(int curPage, int row4Page) {
        int startRow = (curPage - 1) * row4Page;
        Page page = new Page();
        List<Object> list = rootDao.queryAllCommunList(startRow, row4Page);
        System.out.println(list);
        int count = rootDao.queryAllCommunCount();
        int maxPage = count % row4Page > 0 ? count / row4Page + 1 : count / row4Page;
        page.setMaxPage(maxPage);
        page.setObjList(list);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);

        return page;
    }

    public Page queryOneCommunList(String comName, int curPage, int row4Page) {

        int startRow = (curPage - 1) * row4Page;
        Page page = new Page();
        List<Object> list = rootDao.queryOneCommunList(comName,startRow, row4Page);
        System.out.println(list);
        int count = rootDao.queryOneCommunCount(comName);
        int maxPage = count % row4Page > 0 ? count / row4Page + 1 : count / row4Page;
        page.setMaxPage(maxPage);
        page.setObjList(list);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);

        return page;

    }

    public void insertCommun(Community community) {
        rootDao.insertCommun(community.getComName(),community.getComInfor());
    }




    /*
     * 处理用户请求
     */
    public void deleteAssign(int applyId) {
        rootDao.deleteAssign(applyId);
    }


    /*
     * 查询出社区列表
     * */
    public List<Object> selectCommList() {
        return rootDao.selectCommunityList();
    }

    /*
     * 删除养老机构
     */
    public void deletePension(int pensionId) {
        rootDao.deletePension(pensionId);
    }

    /*
     * 删除医疗机构
     */
    public void deleteHospital(int hosId) {
        rootDao.deleteHospital(hosId);
    }

    /*
     * 删除医疗机构
     */
    public void deleteCommunity(int communityId) {
        rootDao.deleteCommun(communityId);
    }


    public int getzhuren() {
       int zhuren =  rootSerDao.queryzhuren();

       return zhuren;
    }

    public int getfuzhuren() {
        int fuzhuren = rootSerDao.queryfuzhuren();
        return fuzhuren;
    }

    public int getzhuzhi() {

        int zhuzhiyishi = rootSerDao.queryzhuzhiyishi();
        return zhuzhiyishi;
    }

    public int getyishi() {
        int yishi = rootSerDao.queryyishi();
        return yishi;
    }

    public int getInquiryData(String type) {

        System.out.println(type);
        int number = rootSerDao.getInquiryData(type);

        return number;
    }
}

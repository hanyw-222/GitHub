package com.demo.service;

import com.demo.dao.IUserSerDao;
import com.demo.entity.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Resource
    private IUserSerDao userSerDao;

    /*
     * 插入健康档案
     * */
    public void addHealthReport(healthReport report) {

        userSerDao.insertHealthReport(report.getUserId(), report.getWeight(),
                report.getHeight(), report.getSmoking(), report.getDrinking(),
                report.getDiet(), report.getExcretion(), report.getAllergy()
        );
    }

    /*
     * 更新个人基本信息
     * */



    /**
     * 分页查询用户全部用户信息
     * @param
     * @return
     */
    public Page queryAllUser2Page(int curPage, int row4Page) {
        //每页记录数
        int startRow = (curPage-1)*row4Page;    //起始行

        List<Object> userList = userSerDao.queryUserList(startRow,row4Page);
        int userCount = userSerDao.queryAllUserCount();
        int maxPage = userCount%row4Page>0?userCount/row4Page+1:userCount/row4Page;
        Page page = new Page();
        page.setMaxPage(maxPage);
        page.setObjList(userList);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);
       // System.out.println(page);
        return page;
    }


    public Page queryDepartmentPage(int curPage, int row4Page, String department) {
        int startRow = (curPage-1)*row4Page;    //起始行

        List<Object> userList = userSerDao.queryDepartmentList(startRow,row4Page,department);
        int userCount = userSerDao.queryAlldepartmentCount(department);
        int maxPage = userCount%row4Page>0?userCount/row4Page+1:userCount/row4Page;
        Page page = new Page();
        page.setMaxPage(maxPage);
        page.setObjList(userList);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);
        System.out.println(page);
        return page;
    }

    public Doctor queryDoctorById(int id) {
        Doctor doctor = userSerDao.queryDocById(id);
        return doctor;
    }

    public void saveAskForm(int userId, int doctorId, Date nowData, String cons, String examineAdd,String state) {
        userSerDao.insertAskForm(userId,doctorId,nowData,cons,examineAdd,state);
    }

    public Page queryAllMedi(int curPage, int row4Page) {
        int startRow = (curPage-1)*row4Page;    //起始行

        List<Object> userList = userSerDao.queryAllMediList(startRow,row4Page);
        int userCount = userSerDao.queryAllMediCount();
        int maxPage = userCount%row4Page>0?userCount/row4Page+1:userCount/row4Page;
        Page page = new Page();
        page.setMaxPage(maxPage);
        page.setObjList(userList);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);
        System.out.println(page);
        return page;
    }
//根据功效查询药品
    public Page queryMediByEff(int curPage, int row4Page, String symptom) {
        int startRow = (curPage-1)*row4Page;    //起始行

        List<Object> userList = userSerDao.queryMediListByEff(startRow,row4Page,symptom);
        int userCount = userSerDao.queryMediByEffCount(symptom);
        int maxPage = userCount%row4Page>0?userCount/row4Page+1:userCount/row4Page;
        Page page = new Page();
        page.setMaxPage(maxPage);
        page.setObjList(userList);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);
        System.out.println(page);
        return page;
    }


//根据标签查询药品
    public Page queryMediByLabel(int curPage, int row4Page, String label) {
        int startRow = (curPage-1)*row4Page;    //起始行

        List<Object> userList = userSerDao.queryMediListByLabel(startRow,row4Page,label);
        int userCount = userSerDao.queryMediByLabelCount(label);
        int maxPage = userCount%row4Page>0?userCount/row4Page+1:userCount/row4Page;
        Page page = new Page();
        page.setMaxPage(maxPage);
        page.setObjList(userList);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);
        System.out.println(page);
        return page;
    }

    public Page queryInquiryByUserId(int curPage, int row4Page, int userId) {
        int startRow = (curPage-1)*row4Page;    //起始行

        List<Object> userList = userSerDao.queryInquiryByUserId(startRow,row4Page,userId);
        int userCount = userSerDao.queryInquiryByUserIdCount(userId);
        int maxPage = userCount%row4Page>0?userCount/row4Page+1:userCount/row4Page;
        Page page = new Page();
        page.setMaxPage(maxPage);
        page.setObjList(userList);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);
        System.out.println(page);
        return page;
    }

    public Onlineconsultingrec queryRecById(int inquiryId) {
        Onlineconsultingrec askDetial = userSerDao.queryAskDetialById(inquiryId);
        System.out.println(askDetial);
        return askDetial;
    }


    public User queryUserById(int userId) {
         User user =  userSerDao.queryUserById(userId);

         return user;
    }

    public void updateUserInfor(int userId, String userName, String sex, Date date, String phone, String homeAdd, String userHeadImg1) {
        userSerDao.updateUserInfor(userId,userName,sex,date,phone,homeAdd,userHeadImg1);
    }


    //韩
    //用户选择服务的医生
    public Page queryAllDocList(int curPage, int row4Page) {

        int startRow = (curPage - 1) * row4Page;
        Page page = new Page();
        List<Object> list = userSerDao.queryAllDocList(startRow, row4Page);
        System.out.println(list);
        int count = userSerDao.queryAllDocCount();
        int maxPage = count % row4Page > 0 ? count / row4Page + 1 : count / row4Page;
        page.setMaxPage(maxPage);
        page.setObjList(list);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);
        return page;

    }

    public Page queryDocList(String type, int curPage, int row4Page) {
        int type1 = Integer.parseInt(type);
        int startRow = (curPage - 1) * row4Page;
        Page page = new Page();
        List<Object> list;
        int count;

        if(type1==1){
            list = userSerDao.queryDocList(1,2, startRow, row4Page);
            count = userSerDao.queryDocCount(1,2);
        }else{
            list = userSerDao.queryDocList(3,4, startRow, row4Page);
            count = userSerDao.queryDocCount(3,4);
        }



        int maxPage = count % row4Page > 0 ? count / row4Page + 1 : count / row4Page;
        page.setMaxPage(maxPage);
        page.setObjList(list);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);

        return page;

    }

    //用户浏览社区信息
    public Page queryComList(int curPage, int row4Page) {

        int startRow = (curPage - 1) * row4Page;
        Page page = new Page();
        List<Object> list = userSerDao.queryComList(startRow, row4Page);
        System.out.println(list);
        int count = userSerDao.queryComCount();
        int maxPage = count % row4Page > 0 ? count / row4Page + 1 : count / row4Page;
        page.setMaxPage(maxPage);
        page.setObjList(list);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);
        return page;

    }

    //用户浏览养老机构信息
    public Page queryPensionList(int curPage, int row4Page) {

        int startRow = (curPage - 1) * row4Page;
        Page page = new Page();
        List<Object> list = userSerDao.queryPensionList(startRow, row4Page);
        System.out.println("测试1："+list);
        int count = userSerDao.queryPensionCount();
        int maxPage = count % row4Page > 0 ? count / row4Page + 1 : count / row4Page;
        page.setMaxPage(maxPage);
        page.setObjList(list);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);
        return page;

    }

    /*
     * 插入健康档案
     * */
    public void addAssign(Assign assign) {

        userSerDao.insertAssign(assign.getRootId(), assign.getDoctorId(), assign.getUserId(),
                assign.getApplyIntro(), assign.getApplyTime(),assign.getIs_checked());
    }
}

package com.demo.service;

import com.demo.dao.IDoctorSerDao;
import com.demo.entity.Doctor;
import com.demo.entity.Onlineconsultingrec;
import com.demo.entity.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DoctorService {

    @Resource
    private IDoctorSerDao  doctorDao;




    /*
     * 更新个人基本信息
     * */

    public void updateUserInfor(Doctor doctor) {

        doctorDao.updateDocInf(doctor.getDoctorName(),doctor.getSex(),doctor.getBirthday(),
                doctor.getPhone(),doctor.getHomeAdd(),doctor.getHosId(),
                doctor.getGraSchool(),doctor.getDepartment(),doctor.getDoctorId(),doctor.getHeadAdd(),doctor.getTitle());

    }

    /**
     * 分页查询医生的全部咨询信息
     * @param
     * @return
     */
    public Page queryAllUser2Page(int curPage, int row4Page,int doctorId) {
        //每页记录数
        int startRow = (curPage-1)*row4Page;    //起始行

        List<Object> userList =doctorDao.queryUserAskList(startRow,row4Page,doctorId);
        System.out.println(userList);
        int userCount = doctorDao.queryAllUserAskCount(doctorId);
        int maxPage = userCount%row4Page>0?userCount/row4Page+1:userCount/row4Page;
        System.out.println(userCount+" =================");
        Page page = new Page();
        page.setMaxPage(maxPage);
        page.setObjList(userList);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);
        // System.out.println(page);
        return page;
    }

    public Onlineconsultingrec queryRecById(int inquiryId) {
        Onlineconsultingrec onlineRec = doctorDao.queryRecById1(inquiryId);

        return onlineRec;
    }
   //医生回复线上咨询问诊单
    public void saveReplyDataById(int inquiryId,String replyData) {
        doctorDao.updateReplyData(inquiryId,replyData);

    }


    //韩


    /*
     * 查询当前医生的全部上门服务请求
     * */

    public  Page queryAssignList(int doctorId,int curPage,int row4Page){
        int startRow = (curPage - 1) * row4Page;
        Page page = new Page();
        List<Object> list = doctorDao.queryDocAssignList(doctorId,startRow, row4Page);
        System.out.println(list);
        int count = doctorDao.queryDocAssignCount(doctorId);
        int maxPage = count % row4Page > 0 ? count / row4Page + 1 : count / row4Page;
        page.setMaxPage(maxPage);
        page.setObjList(list);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);

        return page;
    }

    /*
     * 更新用户请求
     */
    public void updateDocMission(int applyId) {
        doctorDao.updateDocMission(applyId);
    }

    /*
     * 删除已完成的用户请求
     */
    public void deleteDocMission(int applyId) {
        doctorDao.deleteDocMission(applyId);
    }


    /*
     * 插入服务记录
     */
    public void insertDocMission(int doctorId,int userId,String rectime,String introduce) {
        doctorDao.insertDocMission(doctorId,userId,rectime,introduce);
    }

    /*
     * 查询当前医生的全部上门服务请求
     * */

    public Page queryRecordList(int doctorId, int curPage, int row4Page) {
        int startRow = (curPage - 1) * row4Page;
        Page page = new Page();
        List<Object> list = doctorDao.queryAllRecordList(doctorId, startRow, row4Page);
        System.out.println(list);
        int count = doctorDao.queryAllRecordCount(doctorId);
        int maxPage = count % row4Page > 0 ? count / row4Page + 1 : count / row4Page;
        page.setMaxPage(maxPage);
        page.setObjList(list);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);

        return page;
    }
}

package com.demo.service;

import com.demo.dao.ICourseDao;
import com.demo.entity.Course;
import com.demo.entity.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseManagerService {

    @Resource
    private ICourseDao iCourseDao;

    //管理员添加课程
    public void addCourse(String courseName, String courseType, int coursePro, String courseIntro, String courseAdd) {
        iCourseDao.addCourse(courseName, courseType, coursePro, courseIntro, courseAdd);
    }


    //用户查询课程
    public Page queryAllWavList(int curPage, int row4Page) {

        int startRow = (curPage - 1) * row4Page;
        Page page = new Page();
        List<Object> courselist = iCourseDao.queryAllWavList(startRow, row4Page);
        int count = iCourseDao.queryAllWavCount();
        int maxPage = count % row4Page > 0 ? count / row4Page + 1 : count / row4Page;
        page.setMaxPage(maxPage);
        page.setObjList(courselist);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);
        return page;

    }

    public Page queryWavList(String courseType, int curPage, int row4Page) {

        int startRow = (curPage - 1) * row4Page;
        Page page = new Page();
        List<Object> courselist = iCourseDao.queryWavList(courseType, startRow, row4Page);
        int count = iCourseDao.queryWavCourseCount(courseType);
        int maxPage = count % row4Page > 0 ? count / row4Page + 1 : count / row4Page;
        page.setMaxPage(maxPage);
        page.setObjList(courselist);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);
        //System.out.println(page);
        return page;
    }


    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    public Page queryAllReadList(int curPage, int row4Page) {

        int startRow = (curPage - 1) * row4Page;
        Page page = new Page();
        List<Object> courselist = iCourseDao.queryAllReadList(startRow, row4Page);
        int count = iCourseDao.queryAllReadCount();
        int maxPage = count % row4Page > 0 ? count / row4Page + 1 : count / row4Page;
        page.setMaxPage(maxPage);
        page.setObjList(courselist);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);
        return page;

    }

    public Page queryReadList(String courseType, int curPage, int row4Page) {

        int startRow = (curPage - 1) * row4Page;
        Page page = new Page();
        List<Object> courselist = iCourseDao.queryReadList(courseType, startRow, row4Page);
        int count = iCourseDao.queryReadCourseCount(courseType);
        int maxPage = count % row4Page > 0 ? count / row4Page + 1 : count / row4Page;
        page.setMaxPage(maxPage);
        page.setObjList(courselist);
        page.setRow4Page(row4Page);
        page.setCurPage(curPage);
        //System.out.println(page);
        return page;
    }
}

    /*public List<Course> queryCourseByPro(int coursePro) {
        return iCourseDao.queryCourseByPro(coursePro);
    }

    public List<Course> wavCourseSelect(String courseType) {
        return iCourseDao.wavCourseSelect(courseType);
    }

    public List<Course> readCourseSelect(String courseType) {
        return iCourseDao.readCourseSelect(courseType);
    }*/
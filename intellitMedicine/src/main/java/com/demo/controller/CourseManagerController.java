package com.demo.controller;

import com.demo.entity.Course;
import com.demo.entity.Page;
import com.demo.service.CourseManagerService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CourseManagerController {

    @Resource
    private CourseManagerService cms;

    @RequestMapping(value = "/userHome.do")
    public String education(HttpServletRequest request, Model model) {
        return "userHome";
    }

    @RequestMapping(value = "/wavCourse.do")
    public String wavCourse(HttpServletRequest request, Model model) {
        return "wavCourse";
    }

    @RequestMapping(value = "/readCourse.do")
    public String readCourse(HttpServletRequest request, Model model) {
        return "readCourse";
    }


    @RequestMapping(value = "/queryWavCoursePage.do")
    @ResponseBody
    public Page QueryWavCourse(String courseType, int curPage, int row4Page, int maxPage) {

        if (courseType == null) {
            if (curPage == 0) {
                curPage = 1;
                row4Page = 3;

            }
            Page page = cms.queryAllWavList(curPage, row4Page);
            return page;


        } else {
            if (curPage == 0) {
                curPage = 1;
                row4Page = 3;
            }
            Page page = cms.queryWavList(courseType, curPage, row4Page);
            return page;
        }


    }


    @RequestMapping(value = "/queryReadCoursePage.do")
    @ResponseBody
    public Page QueryReadCourse(String courseType, int curPage, int row4Page, int maxPage) {

        if (courseType == null) {
            if (curPage == 0) {
                curPage = 1;
                row4Page = 3;

            }
            Page page = cms.queryAllReadList(curPage, row4Page);
            return page;
        } else {
            if (curPage == 0) {
                curPage = 1;
                row4Page = 3;
            }
            Page page = cms.queryReadList(courseType, curPage, row4Page);
            return page;
        }


    }

    //插入课程——管理员
    @RequestMapping(value = "/courseInsert.do")
    public String courseAdd(HttpServletRequest request, Model model) {

        String courseName = request.getParameter("courseName");
        String courseType = request.getParameter("courseType");
        int coursePro = Integer.valueOf(request.getParameter("coursePro"));
        String courseIntro = request.getParameter("courseIntro");
        String courseAdd = request.getParameter("courseAdd");

        Boolean condition = (courseName != null) && (courseType != null) && (coursePro == 1 || coursePro == 0) && (courseAdd != null);

        if (condition) {
            cms.addCourse(courseName, courseType, coursePro, courseIntro, courseAdd);
            model.addAttribute("msg", "课程成功插入");
            System.out.println("插入成功");

        } else {
            model.addAttribute("msg", "插入失败，不符合规则");
        }
        return "course";
    }


}

/*@RequestMapping(value = "/courseSelectByPro.do")
    public String queryCourseByPro(HttpServletRequest request, Model model) {

        int coursePro = Integer.valueOf(request.getParameter("coursePro"));
        List<Course> list = cms.queryCourseByPro(coursePro);

        if (coursePro == 0) {
            model.addAttribute("CourseList", list);
            return "wavCourse";
        } else {
            model.addAttribute("CourseList", list);
            return "readCourse";
        }
    }

    @RequestMapping(value = "/wavCourseSelect.do")
    public String wavCourseSelect(HttpServletRequest request, Model model) {

        List<Course> list;
        String courseType = request.getParameter("courseType");

        list = cms.wavCourseSelect(courseType);
        model.addAttribute("CourseList", list);
        for (Course c : list) {
            System.out.println(c);
        }
        return "wavCourse";

    }


    @RequestMapping(value = "/readCourseSelect.do")
    public String readCourseSelect(HttpServletRequest request, Model model) {

        List<Course> list;
        String courseType = request.getParameter("courseType");

        list = cms.readCourseSelect(courseType);
        model.addAttribute("CourseList", list);
        for (Course c : list) {
            System.out.println(c);
        }
        return "readCourse";

    }*/
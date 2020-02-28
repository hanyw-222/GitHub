package com.demo.controller;

import com.demo.entity.*;
import com.demo.service.RootService;
import com.demo.service.UserService;
import com.demo.util.DateConverter;
import com.demo.util.Utils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class RootServiceController {

    @Resource
    private RootService rs ;

    private String medicineHeadImg1;

    @Resource
    private UserService us ;




    /**
     * 管理员删除课程
     * @return
     */
    @RequestMapping(value ="/rootDelCourse.do")
    @ResponseBody
    public String  rootDelCourse(int courseId){
         rs.deleteCourse(courseId);

         return "rootCourseMan";
    }

    /**
     * 管理员查询课程
     * @return
     */
    @RequestMapping(value ="/rootQueryCourse.do")
    @ResponseBody
    public Page rootQueryCourse(int curPage, int row4Page, int maxPage,String queryCourseName){
//        System.out.println("doctorId===="+doctorId);
        if (curPage == 0) {
            curPage = 1;
            row4Page = 6;
        }
        if(queryCourseName=="") {
            System.out.println("================");
            System.out.println(curPage + "+++++++" + row4Page);
            Page page = rs.queryAllCourse(curPage, row4Page);
             System.out.println("page=====" + page);
            return page;
        } else {
            System.out.println("+++++++++++++++++++++++"+queryCourseName);
            Page page = rs.queryCourseByName(curPage, row4Page,queryCourseName);
            return page;
        }

    }
    /**
     * 管理员添加课程
     * @return
     */
     @RequestMapping(value = "/addCourse.do")
     public String addCourse( String courseName, String courseType, String coursePro,String courseIntro,String courseAdd,HttpServletRequest request){
         int courseProFlag = 1;
         System.out.println(coursePro);
         if("视频".equals(coursePro)){
             courseProFlag = 0;
         }
         rs.addCourse(courseName,courseType,courseProFlag,courseIntro,courseAdd);
         request.getSession().setAttribute("addCourseMsg","添加课程成功！");
//         request.getSession().getAttribute("addCourseMsg");
         return "rootCourseMan";
     }

    /**
     * 管理员审核医生通过
     * @return
     */
    @RequestMapping(value ="/rootPassDoctor.do")
    @ResponseBody
    public String rootPassDoctor(int doctorId){
//        System.out.println("doctorId===="+doctorId);
        rs.rootPassDoctor(doctorId);
        return "该医生审核通过";
    }

    /**
     * 管理员审核医生
     * @return
     */
    @RequestMapping(value ="/rootCheckDoctor.do")
    @ResponseBody
    public Page rootCheckDoctor(int curPage, int row4Page, int maxPage){
//        System.out.println("doctorId===="+doctorId);
        if (curPage == 0) {
            curPage = 1;
            row4Page = 4;
        }
          System.out.println(curPage + "+++++++" + row4Page);
            Page page = rs.queryAllUncheckDoc(curPage, row4Page);
            System.out.println("page=====" + page);
            return page;
    }


    /**
     * 管理员删除医生
     * @return
     */
    @RequestMapping(value ="/delDoctor.do")
    @ResponseBody
    public String delDoctor(int doctorId){
        System.out.println("doctorId===="+doctorId);
        rs.deleteDoctorById(doctorId);
        return "删除药品成功！";
    }

    /**
     * 管理员查询所有已注册的医生
     * @return
     */
    @RequestMapping(value="/rootQueryCheckedDoc.do")
    @ResponseBody
    public Page queryAllDoctor(int curPage, int row4Page, int maxPage, String queryDoctorName){
        if (curPage == 0) {
            curPage = 1;
            row4Page = 4;
        }
        if(queryDoctorName=="") {

            System.out.println("================");
            System.out.println(curPage + "+++++++" + row4Page);
            Page page = rs.queryAllDoctor(curPage, row4Page);
            // System.out.println("page=====" + page);
            return page;
        } else {
            System.out.println("+++++++++++++++++++++++"+queryDoctorName);
            Page page = rs.queryDoctorByName(curPage, row4Page,queryDoctorName);
            return page;
        }

    }
    /**
     * 管理员删除药品
     * @return
     */
    @RequestMapping(value ="/delMedicine.do")
    @ResponseBody
    public String delMedicine(int medicineId){
        System.out.println("medicineId===="+medicineId);
        rs.deleteMedicineById(medicineId);
        return "删除药品成功！";
    }

    /**
     * 管理员查询所有药品
     * @return
     */
    @RequestMapping(value="/rootQueryAllMedi.do")
    @ResponseBody
    public Page queryAllMedi(int curPage, int row4Page, int maxPage, String queryMedicineName){
        if (curPage == 0) {
            curPage = 1;
            row4Page = 4;
        }
        if(queryMedicineName=="") {

            System.out.println("================");
            System.out.println(curPage + "+++++++" + row4Page);
            Page page = us.queryAllMedi(curPage, row4Page);
            return page;
        } else {
            Page page = rs.queryMediByName(curPage, row4Page,queryMedicineName);
            return page;
        }

    }

    /**
     * 管理员增加药品
     * @param
     */
    @RequestMapping(value = "/addMedicine.do")
    public String  addMedicine(String medicineName,String medicineLabel,String medicineEffect,String medicinePro,HttpServletRequest request){
          rs.addMedicine(medicineName,medicineLabel,medicineEffect,medicinePro,medicineHeadImg1);
          request.getSession().setAttribute("addMedicineMsg","增加药品成功!");
          return "rootMediMan";
    }
    /**
     * 管理员上传药品图片
     * @param
     */
    @RequestMapping(value = "/medicineHeadImg.do")
    @ResponseBody
    public String medicineHeadImg(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) {
        System.out.println("strat++++++++++");
        String fileName = Utils.getNewFileName(file.getOriginalFilename()); //获取一个新的文件名
        String filePath_1 = "img/medicineHeadImg/";     //文件上传路径
        String filePath_2 = request.getSession().getServletContext().getRealPath("/"); //项目路径
        try {
            //实例化文件路径
            File filePath = new File(filePath_2+filePath_1);
            System.out.println("filePaath="+filePath);
            if(!filePath.exists()){
                //检查文件路径是否存在
                filePath.mkdirs();      //不存在则创建目录
            }
            File newFile=new File(filePath,fileName);
            file.transferTo(newFile);//通过CommonsMultipartFile的方法直接写文件

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(filePath_1+fileName);
        medicineHeadImg1 = filePath_1+fileName;
        return filePath_1+fileName;
    }
    /**
     * 管理员首页
     * @param
     * @return ok:帐号可用；no：帐号已存在；
     */
    @RequestMapping(value = "/rootHome.do")
    public String rootHome(){
        return "rootHome";
    }


    /**
     * 管理员个人中心
     * @param account
     * @return ok:帐号可用；no：帐号已存在；
     */
    @RequestMapping(value = "/rootCenter.do")
    public String rootCenter(String account){
        return "rootCenter";
    }

    /**
     * 管理员用户管理
     * @param account
     * @return
     */
    @RequestMapping(value = "/rootUserManager.do")
    public String rootUserManager(String account){
        return "rootUserManager";
    }

    /**
     * 管理员养老机构管理
     * @param account
     * @return
     */
    @RequestMapping(value = "/rootInitituMan.do")
    public String rootInitituMan(String account){
        return "rootInitituMan";
    }

    @RequestMapping(value = "/rootHosMan.do")
    public String rootHosMan(String account){
        return "rootHosMan";
    }

    /**
     * 管理员社区管理
     * @param account
     * @return
     */
    @RequestMapping(value = "/rootCommMan.do")
    public String rootCommMan(String account){
        return "rootCommMan";
    }

    /**
     * 管理员课程管理
     * @param account
     * @return
     */
    @RequestMapping(value = "/rootCourseMan.do")
    public String rootCourseMan(String account){
        return "rootCourseMan";
    }

    /**
     * 管理员药品信息管理
     * @param account
     * @return
     */
    @RequestMapping(value = "/rootMediMan.do")
    public String rootMediMan(String account){
        return "rootMediMan";
    }

    /**
     * 管理员医生信息管理
     * @param account
     * @return
     */
    @RequestMapping(value = "/rootDoctorManager.do")
    public String rootDoctorManager(String account){
        return "rootDoctorManager";
    }

    /**
     * 管理员任务指派
     * @param account
     * @return
     */
    @RequestMapping(value = "/rootTaskAssign.do")
    public String rootTaskAssign(String account){
        return "rootTaskAssign";
    }

    //平台信息可视化
    @RequestMapping(value = "/InformationVisual.do")
    public String informationVisaul(HttpServletRequest reuqest, Model model){
        userAgeCount ageCount = rs.userAgeCount();

        int userCount = rs.getUserCount();
        int doctorCount = rs.getDoctorCount();

        int replyed = rs.getDoctorReplyed();
        int unreplyed = rs.getDoctorunReplyed();

        int zhurenyishi  = rs.getzhuren();
        int fuzhuren = rs.getfuzhuren();
        int zhuzhiyishi = rs.getzhuzhi();
        int yishi = rs.getyishi();


        int ganmaofashao = rs.getInquiryData("感冒发烧");
        model.addAttribute("ganmaofashao",ganmaofashao);

        int diedasunshang = rs.getInquiryData("跌打损伤");
        model.addAttribute("diedasunshang",diedasunshang);

        int gaoxueya = rs.getInquiryData("高血压");
        model.addAttribute("gaoxueya",gaoxueya);

        int fengshigubing = rs.getInquiryData("风湿骨病");
        model.addAttribute("fengshigubing",fengshigubing);

        int tangniaobing = rs.getInquiryData("糖尿病");
        model.addAttribute("tangniaobing",tangniaobing);

        int zhiqiguanyan = rs.getInquiryData("慢性支气管炎");
        model.addAttribute("zhiqiguanyan",zhiqiguanyan);

        int guanxinbing = rs.getInquiryData("冠心病");
        model.addAttribute("guanxinbing",guanxinbing);

        int yiyuzheng = rs.getInquiryData("抑郁症");
        model.addAttribute("yiyuzheng",yiyuzheng);





        System.out.println("感冒发烧："+ganmaofashao);

        model.addAttribute("zhurenyishi",zhurenyishi);
        model.addAttribute("fuzhuren",fuzhuren);
        model.addAttribute("zhuzhiyishi",zhuzhiyishi);
        model.addAttribute("yishi",yishi);

        model.addAttribute("replyed",replyed);
        model.addAttribute("unreplyed",unreplyed);

        model.addAttribute("userCount",userCount);
        model.addAttribute("doctorCount",doctorCount);

        model.addAttribute("ageCount",ageCount);
        return "InformationVisual";
    }



    //韩：
    @RequestMapping(value = "/rootCheckAssign.do")
    @ResponseBody
    public Page rootCheckAssign(int curPage, int row4Page, int maxPage) {

        Page page = rs.queryAllAssignList(curPage, row4Page);

        return page;
    }

    @RequestMapping(value = "/queryUsersList.do")
    @ResponseBody
    public Page queryUsersList(String communityId, String userName, int curPage, int row4Page, int maxPage) {

        Page page = new Page();

        if ("".equals(userName) && communityId == null) {
            page = rs.queryAllUsersList(curPage, row4Page);
        } else if ("".equals(userName) && communityId != null) {
            int id = Integer.parseInt(communityId);
            page = rs.queryAllUsersList2(id, curPage, row4Page);

        } else if (!userName.equals("")) {
            page = rs.queryAllUsersList3(userName, curPage, row4Page);
        }
        return page;
    }


    @RequestMapping(value = "/rootReleaseMission.do")

    public String rootReleaseMission(String applyId) {

        int Id = Integer.parseInt(applyId);
        rs.deleteAssign(Id);
        return "rootTaskAssign";
    }


    @RequestMapping(value = "/selectCommunity.do")
    @ResponseBody
    public List<Object> commList() {

        List<Object> list = rs.selectCommList();

        return list;
    }

    @RequestMapping(value = "/queryHospitalList.do")
    @ResponseBody
    public Page selectHosList(String pname, int curPage, int row4Page, int maxPage) {
        Page page;
        System.out.println("pname:" + pname);
        if (pname.equals("")) {
            page = rs.queryAllHospitalList(curPage, row4Page);

        } else {
            page = rs.queryOneHosList(pname, curPage, row4Page);

        }
        return page;
    }


    @RequestMapping(value = "/insertHospital.do")
    @ResponseBody
    public String insertHospital(String name, String department, String area) {

        Hospital hospital = new Hospital();
        hospital.setHosName(name);
        hospital.setHosDepartment(department);
        hospital.setHospitalGen(area);
        rs.insertHospital(hospital);

        return "插入成功";
    }


    @RequestMapping(value = "/rootDeleteHos.do")
    @ResponseBody
    public String rootDeleteHos(String hosId) {
        int Id = Integer.parseInt(hosId);
        rs.deleteHospital(Id);
        return "删除成功";
    }


    @RequestMapping(value = "/queryPensionList.do")
    @ResponseBody
    public Page selectPensionList(String pname, int curPage, int row4Page, int maxPage) {
        Page page;
        System.out.println("pname:" + pname);
        if (pname.equals("")) {
            page = rs.queryAllPensionList(curPage, row4Page);
            System.out.println("成功测试1");
        } else {
            page = rs.queryOnePensionList(pname, curPage, row4Page);
            System.out.println("成功测试2");
        }
        return page;
    }

    @RequestMapping(value = "/insertPension.do")

    public String insertPension(String name, String area) {

        Pension pension = new Pension();
        pension.setPensionName(name);
        pension.setPensionIntro(area);
        rs.insertPension(pension);
        return "rootInitituMan";
    }

    @RequestMapping(value = "/rootDeletePension.do")
    @ResponseBody
    public String rootDeletePension(String pensionId) {

        int Id = Integer.parseInt(pensionId);
        rs.deletePension(Id);
        return "删除成功";
    }

    @RequestMapping(value = "/queryCommunList.do")
    @ResponseBody
    public Page selectCommunList(String pname, int curPage, int row4Page, int maxPage) {
        Page page;
        System.out.println("pname:" + pname);
        if (pname.equals("")) {
            page = rs.queryAllCommunList(curPage, row4Page);

        } else {
            page = rs.queryOneCommunList(pname, curPage, row4Page);

        }
        return page;
    }


    @RequestMapping(value = "/insertCommun.do")
    @ResponseBody
    public String insertCommun(String name, String area) {

        Community community = new Community();
        community.setComName(name);
        community.setComInfor(area);
        rs.insertCommun(community);

        return "插入成功";
    }


    @RequestMapping(value = "/rootDeleteCommun.do")
    @ResponseBody
    public String rootDeleteCommun(String communityId) {
        int Id = Integer.parseInt(communityId);
        rs.deleteCommunity(Id);
        return "删除成功";
    }
}

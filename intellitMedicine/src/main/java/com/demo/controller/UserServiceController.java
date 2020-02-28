package com.demo.controller;

import com.demo.entity.*;
import com.demo.service.UserManagerService;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户服务的控制器类
 */
@Controller
public class UserServiceController {

    @Resource
    private UserService us ;

    @Resource
    private UserManagerService ums;


    private DateConverter dc ;

    private String userHeadImg1;
    //用户上传检验单地址
    private String examineAdd;
  /**
 * 用户上传头像
 * @return
 */
    @RequestMapping(value = "/userHeadImg.do")
    @ResponseBody
    public String userHeadImg(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) {
        String fileName = Utils.getNewFileName(file.getOriginalFilename()); //获取一个新的文件名
        String filePath_1 = "img/userHeadImg/";     //文件上传路径
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
        userHeadImg1 = filePath_1+fileName;
        //int curLoginUserId =  ((User)request.getSession().getAttribute("curLoginUser")).getUserId();
        return filePath_1+fileName;
    }
      /**
     * 用户查看问诊记录详情
     *
     */
    @RequestMapping(value = "/userAskRecDetial.do")
    public String userAskRecDetial(int inquiryId,HttpServletRequest request){
        Onlineconsultingrec askDetial = us.queryRecById(inquiryId);
        request.getSession().setAttribute("askDetial",askDetial);
        System.out.println(inquiryId);
        return "userAskRecDetial";
    }
/**
 * 用户查看问诊记录列表
 *
 *
 */

    @RequestMapping(value = "/queryInquiryByUserId.do")
    @ResponseBody
    public Page queryInquiryByUserId(int curPage, int row4Page,int maxPage,HttpServletRequest request){
        if (curPage == 0) {
            curPage = 1;
            row4Page = 4;
        }
        User  curLoginUser = (User)request.getSession().getAttribute("curLoginUser");
        Page page = us.queryInquiryByUserId(curPage, row4Page,curLoginUser.getUserId());
        request.getSession().setAttribute("userAskListPage",page);
        System.out.println(page.getObjList().toString());
        return page;
    }
    /**
     * 用户查看问诊记录
     *
     *
     */
    @RequestMapping(value="/userAskRec.do")
    public String userAskRec(){
        return "userAskRecList";
    }
    /**
     * 用户个人基本信息
     *
     * @return ok:帐号可用；no：帐号已存在；
     */
    @RequestMapping(value = "/userInformation.do")
    public String userInfor(HttpServletRequest request, Model model) {

        //获取session里的user信息
        User user = (User)request.getSession().getAttribute("curLoginUser");
        System.out.println("user==="+user);
        model.addAttribute("user",user);


        return "userInfor";
    }

    /**
     * 用户个人健康档案
     *
     * @return ok:帐号可用；no：帐号已存在；
     */
    @RequestMapping(value = "/userHealthyReport.do")
    public String userHealthyReport() {
        return "userHealthRecords";
    }


    /**
     * 更新个人信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateUserInfor.do")
    public String updateUserInfor(User user,HttpServletRequest request, Model model){
        System.out.println("user========="+user);

        User upUser = us.queryUserById(user.getUserId());
        System.out.println("upuser===="+upUser);
        String oldHeadPort = upUser.getHeadAdd();
        if(oldHeadPort!=null&&oldHeadPort!=""
                &&!oldHeadPort.equals(user.getHeadAdd())){
            String filePath = request.getSession().getServletContext().getRealPath("/");
            File file = new File(filePath+oldHeadPort);
            if(file.exists()){     //检查文件是否存在
                file.delete();      //存在则删除文件
            }
        }
        if(userHeadImg1==null){
            userHeadImg1 = user.getHeadAdd();
        }
        us.updateUserInfor(user.getUserId(),user.getUserName(),user.getSex(),user.getDate(),user.getPhone(),user.getHomeAdd(),userHeadImg1);
        user = us.queryUserById(user.getUserId());
        model.addAttribute("user",user);

        request.getSession().removeAttribute("curLoginUser");
        User curLoginUser  = us.queryUserById(user.getUserId());
        request.getSession().setAttribute("curLoginUser",curLoginUser);
        return "userInfor";

    }




    /**
     * 用户查询药品by标签
     * @return
     */
    @RequestMapping(value="/queryMediByLabel.do")
    @ResponseBody
    public Page queryMediByLabel(int curPage, int row4Page,int maxPage,String label){
        if (curPage == 0) {
            curPage = 1;
            row4Page = 4;
        }
        Page page = us.queryMediByLabel(curPage, row4Page,label);
        return page;
    }
    /**
     * 用户查询药品by标签
     * @return
     */
    @RequestMapping(value = "/queryMedicineByLabel.do")
    public String queryMedicineByLabel(int   medicineLabel,HttpServletRequest request){
        request.getSession().setAttribute("medicineLabel",medicineLabel);
//        if(medicineLabel==1){
//            //家庭常备
//        }
//        if(medicineLabel==2){
//            //营养保健
//        }
//        if(medicineLabel==3){
//            //食品滋补
//        }
        return "userQueryMediByLabel";
    }
    /**
     * 用户查询所有药品
     * @return
     */
    @RequestMapping(value="/queryAllMedi.do")
    @ResponseBody
    public Page queryAllMedi(int curPage, int row4Page,int maxPage,String symptom){
        if (curPage == 0) {
            curPage = 1;
            row4Page = 4;
        }
       if(symptom=="") {

           System.out.println("================");
           System.out.println(curPage + "+++++++" + row4Page);
           Page page = us.queryAllMedi(curPage, row4Page);
           // System.out.println("page=====" + page);
           return page;
       } else {
           System.out.println("+++++++++++++++++++++++"+symptom);
           Page page = us.queryMediByEff(curPage, row4Page,symptom);
           return page;
       }

    }
    /**
     * 用户进入根据疾病症状查询药品界面
     * @return
     */
    @RequestMapping(value="/queryMedicineByDis.do")

    public String queryMedicineByDis(){

        return "userQueryMedByDis";
    }


    /**
     * 用户管理在线咨询医生问题
     * @return
     */
    @RequestMapping("/onlineAskForm.do")
    public String onlineAskForm(@RequestParam("cons") String cons,HttpServletRequest request){
       User user = (User)request.getSession().getAttribute("curLoginUser");
       Doctor doctor = (Doctor)request.getSession().getAttribute("askDoctor");
//       System.out.println(user);
//       System.out.println(cons);
//       System.out.println(doctor);
       Date nowData = new Date();
       String state = "未回复";
       us.saveAskForm(user.getUserId(),doctor.getDoctorId(),nowData,cons,examineAdd,state);
       //
       return "userOnlineAsk";
    }

    /**
     * 用户管理在线咨询医生上传检查单
     * @return
     */
    @RequestMapping("/uploadExamine.do")
    @ResponseBody
    public String paper(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) {
        String fileName = Utils.getNewFileName(file.getOriginalFilename()); //获取一个新的文件名
        String filePath_1 = "img/examinePaper/";     //文件上传路径
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
        examineAdd = filePath_1+fileName;
        return filePath_1+fileName;
    }
    /**
     * 用户管理在线咨询医生界面
     * @return
     */
    @RequestMapping("/onlineAskDoctor.do")
    public String onlineAskDoctor(String doctorId,HttpServletRequest request){
       int Id = Integer.parseInt(doctorId);
//       request.getSession().setAttribute("doctorId",doctorId);
       Doctor doctor = us.queryDoctorById(Id);
       request.getSession().setAttribute("askDoctor",doctor);
       System.out.println(doctorId+" ====" );
        return "userOnlineAskDoctor";
    }
    /**
     * 用户管理页面分页查询全部用户
     * @return
     */
    @RequestMapping("/queryAllUser2Page.do")
    @ResponseBody
    public Page QueryAllUser2Page(int curPage, int row4Page,int maxPage,String department){
       // System.out.println("before"+curPage+"+++++++"+row4Page);
        if(department==null) {
            if (curPage == 0) {
                curPage = 1;
                row4Page = 4;
            }
            System.out.println(curPage + "+++++++" + row4Page);
            Page page = us.queryAllUser2Page(curPage, row4Page);
           // System.out.println("page=====" + page);
            return page;
        } else {
            System.out.println(department);
            if (curPage == 0) {
                curPage = 1;
                row4Page = 4;
            }
            Page page = us.queryDepartmentPage(curPage, row4Page,department);
            return page;
        }
    }
    /**
     * 用户查看处方记录
     * @param request
     * @return
     */
    @RequestMapping(value = "/userChufangReport.do")
    public String userChufangReport(HttpServletRequest request){

        return "userChuFangReport";
    }
//       /**
     //     * 用户查看个人健康档案
     //     * @param request
     //     * @return
     //     */
//    @RequestMapping(value = "/userHealthyReport.do")
//    public String userHealthyReport(HttpServletRequest request){
//
//        return "userHealthyReport";
//    }

//    /**
//     * 用户查看个人基本信息
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/userInformation.do")
//    public String userInformation(HttpServletRequest request){
//        return "userInformation";
//    }

    /**
     * 进入在线咨询页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/userOnlineAsk.do")
    public String userOnlineAsk(HttpServletRequest request){

        return "userOnlineAsk";
    }

    /**
     * 进入药品查询页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/userQueryMedicine.do")
    public String userQueryMedicine(HttpServletRequest request){

        return "userQueryMedicine";
    }

    /**
     * 进入健康解读页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/userHealthExplain.do")
    public String userHealthExplain(HttpServletRequest request){

        return "userHealthExplain";
    }
    /**
     * 进入居家养老页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/userHomeCare.do")
    public String userHomeCare(HttpServletRequest request){

        return "userHomeCare";
    }
    /**
     * 进入社区养老页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/userCommunityCare.do")
    public String userCommunityCare(HttpServletRequest request){

        return "userCommunityCare";
    }
    /**
     * 进入机构养老页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/userInstitutionCare.do")
    public String userInstitutionCare(HttpServletRequest request){

        return "userInstitutionCare";
    }
    /**
     * 进入视频学习页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/userCourseVideo.do")
    public String userCourseVideo(HttpServletRequest request){

        return "userCourseVideo";
    }
    /**
     * 进入阅读页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/userCourseReading.do")
    public String userCourseReading(HttpServletRequest request){

        return "userCourseReading";
    }

   //韩

    @RequestMapping(value = "/userDoorService.do")
    public String userDoorService(HttpServletRequest request, Model model) {
        String docId = request.getParameter("docId");
        System.out.println("HYW:" + docId);
        model.addAttribute("docId", docId);
        return "userDoorService";
    }


    /**
     * 存储健康档案
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/userHealthReportStore.do")
    public String storeHealthReport(HttpServletRequest request, Model model) {

//        int userId=Integer.valueOf(request.getParameter(""));
        String weight = request.getParameter("weight");
        String high = request.getParameter("high");
        int smoke = Integer.valueOf(request.getParameter("smoke"));
        int drink = Integer.valueOf(request.getParameter("drink"));
        int food = Integer.valueOf(request.getParameter("food"));
        int shit = Integer.valueOf(request.getParameter("shit"));
        int allergy = Integer.valueOf(request.getParameter("allergy"));
        healthReport rep1 = new healthReport();
        User user = (User) request.getSession().getAttribute("curLoginUser");
        rep1.setUserId(user.getUserId());
        rep1.setHeight(high);
        rep1.setWeight(weight);
        rep1.setDrinking(drink);
        rep1.setSmoking(smoke);
        rep1.setDiet(food);
        rep1.setExcretion(shit);
        rep1.setAllergy(allergy);
        us.addHealthReport(rep1);

        return "userHealthReport";
    }


    /**
     * 查询可申请上门服务列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/queryDocPage.do")
    @ResponseBody
    public Page QueryReadCourse(String type, int curPage, int row4Page, int maxPage) {

        if (type == null) {
            if (curPage == 0) {
                curPage = 1;
                row4Page = 3;

            }
            Page page = us.queryAllDocList(curPage, row4Page);
            return page;


        } else {
            if (curPage == 0) {
                curPage = 1;
                row4Page = 3;
            }

            Page page = us.queryDocList(type, curPage, row4Page);
            return page;
        }

    }

    /**
     * 存储上门服务请求
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/userAssignStore.do")
    public String storeAssign(HttpServletRequest request, Model model) {

        int userId = Integer.parseInt(request.getParameter("userId"));
        String time = request.getParameter("time");
        String applyIntro = request.getParameter("area");
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        Assign assign = new Assign();
        assign.setUserId(userId);
        assign.setRootId(1);
        assign.setApplyTime(time);
        assign.setDoctorId(doctorId);
        assign.setApplyIntro(applyIntro);
        assign.setIs_checked("false");
        us.addAssign(assign);

        return "userDoorService";
    }



    /**
     * 查询社区信息列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/queryComPage.do")
    @ResponseBody
    public Page queryComPage(int curPage, int row4Page, int maxPage) {

        Page page = us.queryComList(curPage, row4Page);
        return page;

    }



    /**
     * 查询社区信息列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/queryPensionPage.do")
    @ResponseBody
    public Page queryPensionPage(int curPage, int row4Page, int maxPage) {

        Page page = us.queryPensionList(curPage, row4Page);
        return page;

    }


}

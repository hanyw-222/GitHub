package com.demo.controller;

import com.demo.dao.IUserDao;
import com.demo.entity.Doctor;
import com.demo.entity.Onlineconsultingrec;
import com.demo.entity.Page;
import com.demo.service.DoctorService;
import com.demo.service.UserManagerService;
import com.demo.util.DateConverter;
import com.demo.util.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DoctorServiceController {

    @Resource
    private DoctorService ds;

    @Resource
    private UserManagerService ums;
    @Resource
    private IUserDao userDao;

    private DateConverter dc;
    private String doctorHeadImg1;
    /**
     * 医生更新个人头像
     * @param
     */
    @RequestMapping(value = "/doctorHeadImg.do")
    @ResponseBody
    public String doctorHeadImg(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) {
        String fileName = Utils.getNewFileName(file.getOriginalFilename()); //获取一个新的文件名
        String filePath_1 = "img/doctorHeadImg/";     //文件上传路径
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
        doctorHeadImg1 = filePath_1+fileName;
        return filePath_1+fileName;
    }
    /**
     * 医生更新个人中心
     * @param
     */
    @RequestMapping(value="/updateDoctorInfor.do")
    public  String updateDocInf(HttpServletRequest request, Model model,String  birthday)throws Exception{
        String name=request.getParameter("name");
        String sex=request.getParameter("sex");
        System.out.println(birthday);
//        Date date = dc.convert(birthday);
//        System.out.println(date);
        String phone=request.getParameter("phone");
        String organization=request.getParameter("organization");
        String department=request.getParameter("department");
        String address=request.getParameter("address");
        String graduate=request.getParameter("graduate");
        String title = request.getParameter("title");



        Doctor doctor=new Doctor();
        int hosId;
        if(organization.equals("区人民医院")){
            hosId=1;
        }else if(organization.equals("中医院")){
            hosId=2;
        }else if(organization.equals("妇幼保健院")){
            hosId=3;
        }else{
            hosId=4;
        }

        Doctor doctor2=(Doctor) request.getSession().getAttribute("curLoginDoctor");
        if(doctorHeadImg1==null){
            doctorHeadImg1 = doctor2.getHeadAdd();
        }
        doctor.setHeadAdd(doctorHeadImg1);
        doctor.setDoctorId(doctor2.getDoctorId());
        doctor.setDoctorName(name);
        doctor.setGraSchool(graduate);
        doctor.setPhone(phone);
        doctor.setHosId(hosId);
        doctor.setSex(sex);
        doctor.setBirthday(birthday);
        doctor.setHomeAdd(address);
        doctor.setDepartment(department);
        doctor.setTitle(title);
//        model.addAttribute("doctor",doctor);
        ds.updateUserInfor(doctor);

        doctorHeadImg1 = null;
        Doctor curLoginDoctor = userDao.queryDoctorByName(doctor.getDoctorName());
        request.getSession().removeAttribute("curLoginDoctor");
        request.getSession().setAttribute("curLoginDoctor",curLoginDoctor);
        System.out.println(curLoginDoctor);
        return "doctorCenter";
    }

/**
 * 医生回复内容存储；
 * @param
 * @return
 */
    @RequestMapping(value = "/doctorHome.do")
    public String doctorHome(){
        return "doctorHome";
    }
    /**
     * 医生回复内容存储；
     * @param
     * @return
     */
    @RequestMapping(value ="/doctorReplyForm.do" )
    public String doctorReplyForm(String replyData,HttpServletRequest request){
        Onlineconsultingrec onlineRec = (Onlineconsultingrec)request.getSession().getAttribute("onlineRec");
        ds.saveReplyDataById(onlineRec.getInquiryId(),replyData);
        int id = onlineRec.getInquiryId();
        onlineRec = ds.queryRecById(id);
        System.out.println(onlineRec);
        request.getSession().removeAttribute("onlineRec");
        request.getSession().setAttribute("onlineRec",onlineRec);
        //ds.updateState(onlineRec.getInquiryId());
        return "doctorReplyDetial";
    }

    /**
     * 医生回复具体信息；
     * @param
     * @return
     */
     @RequestMapping(value = "/doctorReplyDetial.do")
     public String doctorReplyDetial(int inquiryId,HttpServletRequest request){
         Onlineconsultingrec onlineRec = ds.queryRecById(inquiryId);
         System.out.println(onlineRec);
         request.getSession().setAttribute("onlineRec",onlineRec);
         System.out.println(onlineRec.getInquiryData());

         return "doctorReplyDetial";
     }
    /**
     * 医生查看我的咨询
     * @param
     * @return
     */
    @RequestMapping(value = "/queryAllUserAsk.do")
    @ResponseBody
    public Page QueryAllUser2Page(int curPage, int row4Page, int maxPage,HttpServletRequest request){
        // System.out.println("before"+curPage+"+++++++"+row4Page);
            if (curPage == 0) {
                curPage = 1;
                row4Page = 4;
            }
            System.out.println(curPage + "+++++++" + row4Page);
            int doctorId = ((Doctor)request.getSession().getAttribute("curLoginDoctor")).getDoctorId();
            System.out.println(doctorId+" ======");
            Page page = ds.queryAllUser2Page(curPage, row4Page,doctorId);
            // System.out.println("page=====" + page);
            return page;

    }
    /**
     * 医生退出登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/doctorExit.do")
    public String doctorExit(HttpServletRequest request){
        request.getSession().removeAttribute("curLoginDoctor");
        return "userLogin";
    }
    /**
     * 医生个人中心
     * @param account
     * @return ok:帐号可用；no：帐号已存在；
     */
    @RequestMapping(value = "/doctorCenter.do")
    public String doctorCenter(String account){
        return "doctorCenter";
    }
    /**
     * 医生回复患者咨询
     * @param account
     * @return ok:帐号可用；no：帐号已存在；
     */
    @RequestMapping(value = "/doctorReplyAsk.do")
    public String doctorReplyAsk(String account){

        return "doctorReplyAsk";
    }

    /**
     * 医生进行健康解读
     * @param account
     */
    @RequestMapping(value = "/doctorHealthExplain.do")
    public String doctorHealthExplain(String account){

        return "doctorHealthExplain";
    }

    /**
     * 医生我的出诊
     * @param account
     */
    @RequestMapping(value = "/doctorOutCall.do")
    public String doctorOutCall(String account){

        return "doctorOutCall";
    }


    //韩
    @RequestMapping(value = "/queryDocAssignList.do")
    @ResponseBody
    public Page selectDocAssignList(int curPage, int row4Page, int maxPage,HttpServletRequest request) {
        Page page;

        Doctor doctor = (Doctor) request.getSession().getAttribute("curLoginDoctor");
        int doctorId=doctor.getDoctorId();
        page = ds.queryAssignList(doctorId,curPage, row4Page);
        System.out.println("成功测试1");
        System.out.println(page);
        return page;
    }

    @RequestMapping(value = "/docRefuseMission.do")
    @ResponseBody
    public String docRefuseMission(String applyId){
        int id=Integer.parseInt(applyId);
        ds.updateDocMission(id);
        return "拒绝请求";
    }


    @RequestMapping(value = "/docFinishMission.do")
    @ResponseBody
    public String docFinishMission(String applyId,String userId,String introduce,HttpServletRequest request){
        int id1=Integer.parseInt(applyId);
        int id2=Integer.parseInt(userId);
        Doctor doctor=(Doctor)request.getSession().getAttribute("curLoginDoctor");
        int doctorId=doctor.getDoctorId();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=df.format(new Date());
        ds.insertDocMission(doctorId,id2,time,introduce);
        ds.deleteDocMission(id1);
        return "完成请求";
    }


    @RequestMapping(value = "/doctorAssignRec.do")
    @ResponseBody
    public Page docAssignRec(int curPage, int row4Page, int maxPage,HttpServletRequest request) {

        Page page ;
        Doctor doctor = (Doctor) request.getSession().getAttribute("curLoginDoctor");
        int doctorId = doctor.getDoctorId();
        page=ds.queryRecordList(doctorId,curPage,row4Page);
        return page;
    }

    @RequestMapping(value =  "/docRecord.do")
    public String docRecordHtml(){
        return "doctorRecord";
    }
}

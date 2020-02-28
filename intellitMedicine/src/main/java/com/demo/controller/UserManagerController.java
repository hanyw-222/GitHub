package com.demo.controller;

import com.demo.entity.Doctor;
import com.demo.entity.Page;
import com.demo.entity.Root;
import com.demo.entity.User;

import com.demo.service.UserManagerService;
import com.demo.util.Utils;
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

/**
 * �û�����Ŀ�������
 */
@Controller
public class UserManagerController {
    /**
     * �����û������ҵ�������Springע��
     */
    @Resource
    private UserManagerService ums;

    private String paperAdd ;

    /**
     * 用户退出登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/userExit.do")
    public String userExit(HttpServletRequest request){
        request.getSession().removeAttribute("curLoginUser");
        request.getSession().removeAttribute("curLoginDoctor");
        request.getSession().removeAttribute("curLoginRoot");
        return "userLogin";
    }


    /**
     * 管理员退出登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/rootExit.do")
    public String rootExit(HttpServletRequest request){
        request.getSession().removeAttribute("curLoginRoot");
        return "userLogin";
    }

    /**
     * 用户个人中心
     * @param account
     * @return ok:帐号可用；no：帐号已存在；
     */
    @RequestMapping(value = "/userCenter.do")
    public String userCenter(String account,HttpServletRequest request, Model model){
        return "userCenter";
    }
    /**
     * Ajax校验新的医生帐号是否可用
     * @param account
     * @return ok:帐号可用；no：帐号已存在；
     */
    @RequestMapping(value = "/doctorNameCheck.do")
    @ResponseBody
    public String doctorNameCheck(String account){
        return ums.DoctorNameCheck(account)==0?"ok":"no";
    }
    /**
     * Ajax校验新的用户帐号是否可用
     * @param account
     * @return ok:帐号可用；no：帐号已存在；
     */
    @RequestMapping(value = "/accountCheck.do")
    @ResponseBody
    public String AccountCheck(String account){
        return ums.AccountCheak(account)==0?"ok":"no";
    }
    /**
     * ajax上传资质证书
     *
     * @param request
     * @param file
     * @return
     */
    @RequestMapping(value = "/paper.do")
    @ResponseBody
    public String paper(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request) {
        String fileName = Utils.getNewFileName(file.getOriginalFilename()); //获取一个新的文件名
        String filePath_1 = "img/paper/";     //文件上传路径
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
        paperAdd = filePath_1+fileName;
        return filePath_1+fileName;
    }
    /**
     * 医生注册
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/doctorRegister.do")
    public String doctorRegister(String name,String password,String sex,String institution,String office,String phone,String IdCard ,HttpServletRequest request, Model model){
        System.out.println("paperAss="+paperAdd);
        int roleType = 3;
        int flag = 0;
        ums.DoctorRegister(name,password,sex,institution,office,phone,paperAdd,flag,IdCard,roleType);
        paperAdd = null;
        Doctor doctor = ums.DoctorLogin(name,password);
        if(doctor!=null){
            request.getSession().setAttribute("curLoginDoctor", doctor);
            return  "doctorHome";
        } else {
            return "doctorRegister";
        }

    }
/**
 * 用户注册
 *
 * @param request
 * @param model
 * @return
 */
@RequestMapping(value = "/userRegister.do")
public String userRegister(String userName,String phone,String password,String community,String sex,HttpServletRequest request, Model model){
    System.out.println(userName+""+phone);
    int roleType = 2;
    ums.UserRegister(userName,phone,password,community,sex,roleType);
    User user  = ums.UserLogin1(userName,password);
    System.out.println("user:" + user);
    if(user!=null){
        request.getSession().setAttribute("curLoginUser", user);
        return "userHome";
    } else{
        return "userRegister";
    }
}
    /**
     * @param name
     * @param password
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/userLogin.do")
    public String userLogin(String name, String password, String userRole, HttpServletRequest request, Model model) {
        if ("root".equals(userRole)) {
            Root root = ums.UserLogin(name, password);
            System.out.println("user:" + root);
            if (root != null) {
                request.getSession().setAttribute("curLoginRoot", root);
//                request.getSession().removeAttribute();
                model.addAttribute("msg", "欢迎" + name + "登录");
                return "rootHome";
            } else {
                model.addAttribute("msg1", "账号或密码错误！");
                return "userLogin";
            }
        } else if ("user".equals(userRole)) {
            User user  = ums.UserLogin1(name,password);
            System.out.println("user:" + user);
            if (user != null) {
                request.getSession().setAttribute("curLoginUser", user);
                model.addAttribute("msg", "欢迎" + name + "登录");
                return "userHome";
            } else {
                model.addAttribute("msg1", "账号或密码错误！");
                return "userLogin";
                //"forward:userLogin.do"        //ת�����������ڲ���ת���Դ��ݲ�����
                //"redirect:userLogin.do";	    //�ض��򣬿ͻ�����ת���µĵ�ַ�����ܴ��ݲ�����
            }
        } else if ("doctorres".equals(userRole)) {
            Doctor doctor = ums.DoctorLogin(name,password);
            System.out.println("user:" + doctor);
            if(doctor!=null){
                request.getSession().setAttribute("curLoginDoctor",doctor);
                return "doctorHome";
            } else{
                model.addAttribute("msg1", "账号或密码错误或未你的账号未审核通过，请重新注册！");
                return "userLogin";
            }

        }
        model.addAttribute("msg", "请选择登录角色！");
         return "userLogin";
    }

    /**
     * 选择注册角色
     *
     * @param userRole
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/registerRole.do")
    public String registerRole(String userRole,HttpServletRequest request, Model model){
        if("user".equals(userRole)){
            return "userRegister";
        }else if("doctorres".equals(userRole)){
            return "doctorRegister";
        } else {
            model.addAttribute("msg", "请选择注册类型！");
            return "registerRole";
        }
    }

//    /**
//     * Ajax�ļ��ϴ����ϴ��û�ͷ�񲢷����ϴ����·���ļ���
//     * @param file
//     * @return
//     */
//    @RequestMapping(value = "/HeadPortUpload.do")
//    @ResponseBody
//    public String HeadPortUpload(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request) {
//        String fileName = Utils.getNewFileName(file.getOriginalFilename()); //��ȡһ���µ��ļ���
//        String filePath_1 = "img/headPort/";     //�ļ��ϴ�·��
//        String filePath_2 = request.getSession().getServletContext().getRealPath("/"); //��Ŀ·��
//        try {
//            //ʵ�����ļ�·��
//            File filePath = new File(filePath_2+filePath_1);
//            if(!filePath.exists()){     //����ļ�·���Ƿ����
//                filePath.mkdirs();      //�������򴴽�Ŀ¼
//            }
//            File newFile=new File(filePath,fileName);
//            file.transferTo(newFile);//ͨ��CommonsMultipartFile�ķ���ֱ��д�ļ�
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return filePath_1+fileName;
//    }
//
//    /**
//     * �����û�����ҳ��
//     * @return
//     */
//    @RequestMapping("/userManagerPage.do")
//    public String UserManagerPage(Model model){
//        Page page = new Page();
//        page.setCurPage(1);
//        page.setMaxPage(1);
//        page.setRow4Page(3);
//        model.addAttribute("page",page);
//        return "old/userManager";
//    }


}
package com.demo.interceptor;

import com.demo.dao.IPowerDao;
import com.demo.entity.Doctor;
import com.demo.entity.Root;
import com.demo.entity.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求权限校验
 */
public class PowerCheckInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private IPowerDao powerDao;
    /**
     * 进入控制器前的预处理
     * @param request
     * @param response
     * @param handler
     * @return 返回false时将直接跳过后边的执行不
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入控制器前的预处理");
        // 获取登录时保存在session内的用户信息
        User curLoginuser = (User) request.getSession().getAttribute("curLoginUser");
        Doctor doctor = (Doctor) request.getSession().getAttribute("curLoginDoctor");
        Root root = (Root) request.getSession().getAttribute("curLoginRoot");
        String requestUri = request.getRequestURI();
        String ContextPath = request.getContextPath();
        if (curLoginuser != null) { //如果存在用户登录信息
            System.out.println("requestUri=" + requestUri + "\nContextPath=" + ContextPath);
            if (ContextPath.length() > 0 && requestUri.startsWith(ContextPath)) {
                requestUri = requestUri.substring(request.getContextPath().length(), requestUri.length());
                System.out.println("处理后的requestUri=" + requestUri);
            }
            int num = powerDao.isPowerExist(curLoginuser.getRoleType(), requestUri);
            if (num == 1) {
                return true;
            } else {
                //返回到登录页面
                request.setAttribute("msg", "请登录系统");
                request.getRequestDispatcher("/userLogin.do").forward(request, response);
                return false;
            }
        } else if (doctor != null) {
            System.out.println("requestUri=" + requestUri + "\nContextPath=" + ContextPath);
            if (ContextPath.length() > 0 && requestUri.startsWith(ContextPath)) {
                requestUri = requestUri.substring(request.getContextPath().length(), requestUri.length());
                System.out.println("处理后的requestUri=" + requestUri);
            }
            int num = powerDao.isPowerExist(doctor.getRoleType(), requestUri);
            if (num == 1) {
                return true;
            } else {
                //返回到登录页面
                request.setAttribute("msg", "请登录系统");
                request.getRequestDispatcher("/userLogin.do").forward(request, response);
                return false;
            }
        } else if (root != null) {
            System.out.println("requestUri=" + requestUri + "\nContextPath=" + ContextPath);
            if (ContextPath.length() > 0 && requestUri.startsWith(ContextPath)) {
                requestUri = requestUri.substring(request.getContextPath().length(), requestUri.length());
                System.out.println("处理后的requestUri=" + requestUri);
            }
            int num = powerDao.isPowerExist(root.getRoleType(), requestUri);
            if (num == 1) {
                return true;
            } else {
                //返回到登录页面
                request.setAttribute("msg", "请登录系统");
                request.getRequestDispatcher("/userLogin.do").forward(request, response);
                return false;
            }
        } else {
            //返回到登录页面
            request.setAttribute("msg", "请登录系统");
            request.getRequestDispatcher("/userLogin.do").forward(request, response);
            return false;
        }
    }
}

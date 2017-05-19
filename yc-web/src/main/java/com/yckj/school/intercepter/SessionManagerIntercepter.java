package com.yckj.school.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.gson.Gson;
import com.yckj.school.annotation.SchoolValidate;
import com.yckj.school.common.ResultData;
import com.yckj.school.common.constant.Constants;
import com.yckj.school.service.dto.UserDto;

public class SessionManagerIntercepter extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(SessionManagerIntercepter.class);

    private String useAddress;

    public void setUseAddress(String useAddress) {
        this.useAddress = useAddress;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 设置文件访问方式
        if (Constants.YES.equals(useAddress)) {
            request.setAttribute(Constants.USE_ADDRESS, true);
        }
        HandlerMethod mHandler = (HandlerMethod) handler;
        SchoolValidate validator = (SchoolValidate) mHandler.getMethodAnnotation(SchoolValidate.class);
        if (validator != null && validator.ignoreSession()) {
            logger.info(mHandler.getBean().getClass().getName() + "." + mHandler.getMethod().getName()
                    + " allow to access,don't need validate");
            return true;
        }
        else {
            HttpSession session = request.getSession();
            Object obj = session.getAttribute(Constants.CURRENT_USER);
            if (obj == null) {
                ResponseBody resp = (ResponseBody) mHandler.getMethodAnnotation(ResponseBody.class);
                if (resp != null) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    ResultData<?> result = new ResultData<>();
                    result.setStatus(Constants.SYSTEM_ERROR_CODE);
                    result.setMsg("请先登录");
                    response.getWriter().print(new Gson().toJson(result));
                }
                else {
                    request.setAttribute(Constants.MSG, "请先登录");
                    request.getRequestDispatcher(Constants.LOGIN_PATH).forward(request, response);
                }
                return false;
            }
        }
        if (validator != null && (validator.accessUser() != -1)) {
            HttpSession session = request.getSession();
            Object obj = session.getAttribute(Constants.CURRENT_USER);
            UserDto user = (UserDto) obj;
            if (validator.accessUser() != user.getType().intValue()) {
                ResponseBody resp = (ResponseBody) mHandler.getMethodAnnotation(ResponseBody.class);
                if (resp != null) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    ResultData<?> result = new ResultData<>();
                    result.setStatus(Constants.SYSTEM_ERROR_CODE);
                    result.setMsg("您无权限");
                    response.getWriter().print(new Gson().toJson(result));
                }
                else {
                    request.setAttribute(Constants.MSG, "您无权限");
                    request.getRequestDispatcher(Constants.LOGIN_PATH).forward(request, response);
                }
                return false;
            }
        }

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return;
        }
        String state = (String) request.getAttribute(Constants.STATE);

        if (StringUtils.isBlank(state)) {
            request.setAttribute(Constants.STATE, Constants.SUCCESS);
        }
        HandlerMethod mHandler = (HandlerMethod) handler;
        logger.info(mHandler.getBean().getClass().getName() + "." + mHandler.getMethod().getName() + "返回结果:"
                + request.getAttribute(Constants.STATE));
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
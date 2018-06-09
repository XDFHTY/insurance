package com.cjkj.insurance.interceptors;

import com.cjkj.insurance.utils.CookieTool;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by XD on 2018/1/8.
 * 前端拦截器
 */
public class UserInterceptors implements HandlerInterceptor {


    //储存用户sessionId
    private Map sessionMap = new HashMap();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        System.out.println(">>>UserInterceptors>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");

        String sessionId = request.getSession().getId();

        System.out.println("sessionId--before================"+sessionId);






        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
//        System.out.println(">>>UserInterceptors>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
        Integer userId = (Integer)request.getSession().getAttribute("userId");
        String sessionId = request.getSession().getId();

        if(sessionMap.containsKey(userId)){  //存在
            String oldSessionId = (String) sessionMap.get(userId);
            if(sessionId.equals(oldSessionId)){  //新旧sessionId匹配上了

                System.out.println("============================jsessionid--after 匹配成功=====================================");
                CookieTool.addCookie(response,"jsessionid",oldSessionId,1800);
            }else {

                System.out.println("============================重写用户的 cookie（sessionId--存在）--after=====================================");
                System.out.println("oldSessionId--after==========="+oldSessionId);
                CookieTool.addCookie(response,"jsessionid",oldSessionId,1800);
            }
        }else {  //不存在
            System.out.println("sessionId--不存在==========="+sessionId);
            //将用户的sessionId写入sessionMap
            sessionMap.put(userId,sessionId);
            System.out.println("============================重写用户的 cookie（sessionId--不存在）--after=====================================");
            CookieTool.addCookie(response,"jsessionid",sessionId,1800);

        }





    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
//        System.out.println(">>>UserInterceptors>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");

    }
}

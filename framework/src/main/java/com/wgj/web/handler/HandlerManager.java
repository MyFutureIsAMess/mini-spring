package com.wgj.web.handler;

import com.wgj.web.mvc.Controller;
import com.wgj.web.mvc.RequestMapping;
import com.wgj.web.mvc.RequestParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * Handler管理类
 * @author Guojian Wang
 * @version 1.0
 * @date 2019/7/27 - 19:00
 * @since 1.0
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑, 永无BUG!
 * 　　　　 ┃　　　┃Code is far away from bug with the animal protecting
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 */
public class HandlerManager {
    public static List<MappingHandler> mappingHandlerList = new ArrayList<>();

    public static void resloveMappingHandler(List<Class<?>> classList) {
        // 找出带有Controller注解的类
        for (Class<?> cls : classList) {
            if (cls.isAnnotationPresent(Controller.class)) {
                parseHandlerFromController(cls);
            }
        }
    }

    private static void parseHandlerFromController(Class<?> cls) {
        // 通过反射获取类的方法
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            // 如果方法没有被RequestMapping注解就不处理
            if (!method.isAnnotationPresent(RequestMapping.class)) {
                continue;
            }
            // 从RequestMapping注解中获取uri
            String uri = method.getDeclaredAnnotation(RequestMapping.class).value();
            List<String> paramNameList = new ArrayList<>();
            for (Parameter parameter : method.getParameters()) {
                // 找到被RequestParam注解的参数
                if (parameter.isAnnotationPresent(RequestParam.class)) {
                    // 从RequestParam获取参数名
                    paramNameList.add(parameter.getDeclaredAnnotation(RequestParam.class).value());
                }
            }
            String[] params = paramNameList.toArray(new String[paramNameList.size()]);

            // 构造一个MappingHandler
            MappingHandler mappingHandler = new MappingHandler(uri, method, cls, params);

            // 把mappingHandler放到Handler管理器的静态属性中
            HandlerManager.mappingHandlerList.add(mappingHandler);
        }
    }
}

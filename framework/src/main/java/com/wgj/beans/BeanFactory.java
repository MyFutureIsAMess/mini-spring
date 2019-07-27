package com.wgj.beans;

import com.wgj.web.mvc.Controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Guojian Wang
 * @version 1.0
 * @date 2019/7/27 - 20:16
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
public class BeanFactory {
    /**
     * bean类型(Class<?></>)-->bean实例的映射(Object)
     */
    private static Map<Class<?>, Object> classToBean = new ConcurrentHashMap<>();

    /**
     * 从映射中获取bean
     * @param cls bean类型
     * @return
     */
    public static Object getBean(Class<?> cls) {
        return classToBean.get(cls);
    }

    /**
     * bean初始化
     * @param classList ClassScanner中扫描到的List<Class<?>>
     */
    public static void initBean(List<Class<?>> classList) throws Exception {
        List<Class<?>> toCreate = new ArrayList<>(classList);
        while (toCreate.size() != 0) {
            // 记录当前容器大小
            int remainSize = toCreate.size();
            for (int i = 0; i < toCreate.size(); i++) {
                if (finishCreate(toCreate.get(i))) {
                    toCreate.remove(i);
                }
            }
            // 如果相等说明有循环依赖
            if (toCreate.size() == remainSize) {
                throw new Exception("cycle dependency");
            }
        }
    }

    private static boolean finishCreate(Class<?> cls) throws IllegalAccessException, InstantiationException {
        // 如果类没有Bean和Controller注解，则不需要创建bean
        if (!cls.isAnnotationPresent(Bean.class) && !cls.isAnnotationPresent(Controller.class)) {
            return true;
        }
        // 创建bean实例
        Object bean = cls.newInstance();
        // 遍历类的属性，看是否存在需要解决的依赖
        for (Field field : cls.getDeclaredFields()) {
            // 如果该属性有AutoWired注解，则表明需要依赖注入解决依赖
            if (field.isAnnotationPresent(AutoWired.class)) {
                // 获取属性的类型
                Class<?> filedType = field.getType();
                // 从Bean工厂获取bean实例
                Object reliantBean = BeanFactory.getBean(filedType);
                // 如果被依赖的bean，则会创建失败
                if (reliantBean == null) {
                    return false;
                }
                field.setAccessible(true);
                // 注入依赖
                field.set(bean, reliantBean);
            }
        }
        // 字段处理完后将该bean放到bean工厂内
        classToBean.put(cls, bean);
        return true;
    }
}

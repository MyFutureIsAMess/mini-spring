package com.wgj.core;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 类扫描器
 *
 * @author Guojian Wang
 * @version 1.0
 * @date 2019/7/27 - 15:33
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
public class ClassScanner {
    /**
     * 类扫描方法
     *
     * @param packageName
     * @return
     * @throws IOException
     */
    public static List<Class<?>> scanClasses(String packageName) throws IOException, ClassNotFoundException {
        List<Class<?>> classList = new ArrayList<>();
        // 包名转化为文件路径
        String path = packageName.replace(".", "/");
        // 获取默认类加载器
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // 通过路径加载文件
        Enumeration<URL> resources = classLoader.getResources(path);
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            // 处理资源类型是jar包的情况
            if (resource.getProtocol().contains("jar")) {
                // 打开jar包连接
                JarURLConnection jarURLConnection = (JarURLConnection) resource.openConnection();
                // 获取jar包路径
                String jarFilePath = jarURLConnection.getJarFile().getName();
                classList.addAll(getClassFromJar(jarFilePath, path));
            } else {
                // TODO
            }
        }
        return classList;
    }

    /**
     * 获取jar包下的所有的类
     *
     * @param jarFilePath jar包路径
     * @param path        通过类的相对路径获取指定的类
     * @return
     */
    private static List<Class<?>> getClassFromJar(String jarFilePath, String path) throws IOException, ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        // 将jar包路径转化为一个jarFilePath实例
        JarFile jarFile = new JarFile(jarFilePath);
        Enumeration<JarEntry> jarEntries = jarFile.entries();
        while (jarEntries.hasMoreElements()) {
            // 每一个jarEntry都是jar包里的文件
            JarEntry jarEntry = jarEntries.nextElement();
            // 获取jarEntry的名字:(例如)com/wgj/test/Test.class
            String entryName = jarEntry.getName();
            // 获取每个jarEntry对应的类
            if (entryName.equals(path) && entryName.endsWith(".class")) {
                // 获取类的全限定名
                String classFullName = entryName.replace("/", ".").substring(0,
                        entryName.length() - 6);
                // 通过类加载器加载到jvm中
                classes.add(Class.forName(classFullName));

            }
        }
        return classes;
    }
}

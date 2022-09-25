package com.hdp.jvm;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * @author HDP
 * @ClassName: MyClassLoaderTest
 * @Description:
 * @date 2022/9/25 20:54
 */
public class MyClassLoaderTest {
    static class MyClassLoader extends ClassLoader {
        private String classPath;

        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }
        private byte[] loadByte(String name) throws Exception {
            name = name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classPath + "/" + name
                    + ".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;
        }
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] bytes = loadByte(name);
                return defineClass(name, bytes, hashCode(), hashCode());
            } catch (
                    Exception e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * 重写类加载方法，实现自己的加载逻辑，不委派给双亲加载
         * @param name
         * @param resolve
         * @return
         * @throws ClassNotFoundException
         */
        protected Class<?> loadClass(String name, boolean resolve)
                throws ClassNotFoundException
        {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    long t0 = System.nanoTime();
                    try {
                        if (!name.contains("com.hdp.jvm")) {
                            this.getParent().loadClass(name);
                        }
//                        if (parent != null) {
//                            c = parent.loadClass(name, false);
//                        } else {
//                            c = findBootstrapClassOrNull(name);
//                        }
                    } catch (ClassNotFoundException e) {
                        // ClassNotFoundException thrown if class not found
                        // from the non-null parent class loader
                    }

                    if (c == null) {
                        // If still not found, then invoke findClass in order
                        // to find the class.
                        long t1 = System.nanoTime();
                        c = findClass(name);

                        // this is the defining class loader; record the stats
                        sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                        sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                        sun.misc.PerfCounter.getFindClasses().increment();
                    }
                }
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
        }
        public static void main(String[] args) throws Exception {
            MyClassLoader myClassLoader = new MyClassLoader("E:/test");
            Class aClass = myClassLoader.loadClass("com.hdp.jvm.test");
            Object obj = aClass.newInstance();
            Method method = aClass.getDeclaredMethod("sout", null);
            //反射
            method.invoke(obj, null);
            System.out.println(aClass.getClassLoader());
        }
    }
}

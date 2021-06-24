package top.manred.dao.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理类
 * @param <T>
 */
class ServiceProxy<T> implements InvocationHandler {
    private Class<T> interfaces;

    ServiceProxy(Class<T> interfaces) {
        this.interfaces = interfaces;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String value = method.getAnnotation(SQL.class).value();
        System.out.println("SQL模版：" + value);
        String[] keys = getMethodParameterNamesByAnnotation(method);
        System.out.println("参数：");
        for (int i=0; i<args.length; i++){
            System.out.println(String.format("%s --> %s",keys[i],args[i].toString()));
        }
        //TODO
        /**
         * 根据参数组装sql,用Spring的JdbcTemplate查询数据。
         */
        return null;
    }

    public static String[] getMethodParameterNamesByAnnotation(Method method) {
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if (parameterAnnotations == null || parameterAnnotations.length == 0) {
            return null;
        }
        String[] parameterNames = new String[parameterAnnotations.length];
        int i = 0;
        for (Annotation[] parameterAnnotation : parameterAnnotations) {
            for (Annotation annotation : parameterAnnotation) {
                if (annotation instanceof SQLParam) {
                    SQLParam param = (SQLParam) annotation;
                    parameterNames[i++] = param.value();
                }
            }
        }
        return parameterNames;
    }
}

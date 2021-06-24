package manred.top.task.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <p>
 *  反射运行方法
 * </p>
 *
 * @author ： 钟满红
 * @createtime ： 2020/5/7 14:59
 */
public class MethodInvoke {

    private Object obj;
    private Method method;

    public MethodInvoke(Object obj, Method method) {
        this.obj = obj;
        this.method = method;
    }

    public Object getObj() {
        return obj;
    }

    public Method getMethod() {
        return method;
    }

    //运行方法
    public void invoke() {
        try {
            method.invoke(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

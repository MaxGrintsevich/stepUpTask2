package ru.stepup.task2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

class Cacher implements InvocationHandler {

    private Object obj;
    private Map<Method, Object> cache = new HashMap<>();
    private boolean changed = true;

    Cacher(Object obj){
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method m =obj.getClass().getMethod(method.getName(), method.getParameterTypes());

        if (m.isAnnotationPresent(Cache.class)){
            if (changed){
                cache.put(m, method.invoke(obj, args));
                changed = false;
            }
            return cache.get(m);
        }
        if (m.isAnnotationPresent(Mutator.class)){
            cache.clear();
            changed = true;
        }
        return method.invoke(obj, args);
    }
    public static <T> T getCachedInstance(T f){
        ClassLoader ldr = f.getClass().getClassLoader();
        Class[] i = f.getClass().getInterfaces();
        T ff = (T) Proxy.newProxyInstance(ldr, i, new Cacher(f));
        return ff;
    }
}


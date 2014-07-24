package gwtflow.flow.server.dao.impl;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.IntroductionInterceptor;

public class FinderIntroductionInterceptor implements IntroductionInterceptor {

    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        FinderExecutor executor = (FinderExecutor) methodInvocation.getThis();

        String methodName = methodInvocation.getMethod().getName();
        if (methodName.startsWith("find") || methodName.startsWith("list")) {
            Object[] arguments = methodInvocation.getArguments();
            return executor.executeFinder(methodInvocation.getMethod(), arguments);
        } else {
            return methodInvocation.proceed();
        }
    }

    public boolean implementsInterface(Class intf) {
        return intf.isInterface() && FinderExecutor.class.isAssignableFrom(intf);
    }
}

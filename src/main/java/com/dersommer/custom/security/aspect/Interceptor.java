package com.dersommer.custom.security.aspect;

import java.lang.reflect.Method;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Interceptor implements MethodInterceptor {
  public static Logger LOGGER = LoggerFactory.getLogger(Interceptor.class);

  @Bean("auditPointcut")
  public AbstractPointcutAdvisor createPointcut() {
    return new AbstractPointcutAdvisor() {

      private static final long serialVersionUID = 4733447191475535406L;

      @Override
      public Advice getAdvice() {
        return Interceptor.this;
      }

      @Override
      public Pointcut getPointcut() {
        return new StaticMethodMatcherPointcut() {
          @Override
          public boolean matches(Method method, Class<?> targetClass) {
            if (method.isAnnotationPresent(Audit.class)) {
              return true;
            }
            if (method.getDeclaringClass().isInterface()) {
              String methodName = method.getName();
              try {
                Method targetMethod = targetClass.getMethod(methodName, method.getParameterTypes());
                return targetMethod != null && targetMethod.isAnnotationPresent(Audit.class);
              } catch (NoSuchMethodException | SecurityException e) {
                LOGGER.debug(
                    "Audit: {}.createPointcut().new AbstractPointcutAdvisor() {...}.getPointcut().new StaticMethodMatcherPointcut() {...}.matches(Method, Class<?>) ignoring {}",
                    this.getClass().getName(),
                    e.getMessage());
                return false;
              }
            }
            return method.isAnnotationPresent(Audit.class);
          }
        };
      }
    };
  }

  @Autowired
  private ApplicationContext context;

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    HttpServletRequestHolder headers = context.getBean(HttpServletRequestHolder.class);
    if(headers==null) {
      throw new RuntimeException("No Http request");
    }

    LOGGER.info("apiKey {}", headers.getRequest().getHeader("apiKey"));
    return invocation.proceed();
  }


}

package org.cyg.thinking.in.spring.environment;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 依赖注入 {@link Environment}
 */
@Configuration
public class InjectingEnvironmentDemo implements EnvironmentAware, ApplicationContextAware {

    private Environment environment;
    @Autowired
    private Environment environment2;

    private ApplicationContext applicationContext;
    @Autowired
    private ApplicationContext applicationContext2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册
        context.register(InjectingEnvironmentDemo.class);

        // 启动 Spring 应用上下文
        context.refresh();

        InjectingEnvironmentDemo demo = context.getBean(InjectingEnvironmentDemo.class);
        System.out.println(demo.environment);
        System.out.println(demo.environment == demo.environment2);
        System.out.println(demo.environment == context.getEnvironment());
        System.out.println(demo.applicationContext);
        System.out.println(demo.applicationContext == demo.applicationContext2);
        System.out.println(demo.applicationContext == context);

        // 关闭 Spring 应用上下文
        context.close();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

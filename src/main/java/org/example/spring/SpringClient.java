package org.example.spring;

import org.example.bean.Student;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 1.资源抽象
 * 2.工厂
 * 3.配置信息读取器
 *
 * BeanFactory是Spring Bean工厂最顶层的抽象
 *
 * Spring Bean实例的注册流程
 * 1.定义好Spring的配置文件
 * 2.通过Resource对象将Spring配置文件进行抽象，抽象成一个具体的Resource对象（如classPathResource）
 * 3.定义好将要使用的Bean工厂（各种BeanFactory）
 * 4.定义好XmlBeanDefinitionReader对象，并将工厂对象作为参数传递进去，从而构建好二者之间的关联关系
 * 5.通过XmlBeanDefinitionReader对象读取之前所抽取出的Resource对象
 * 6.流程开始进行解析
 * 7.针对XML文件进行各种元素以及元素属性的解析，这里面，真正的解析是通过BeanDefinitionParserDelegate对象完成的（委托模式）
 * 8.通过BeanDefinitionParserDelegate对象在解析XML文件时，又使用到了模板方法设计模式(pre, process, post)
 * 9.当所有的bean标签元素都解析完毕后，开始定义一个BeanDefinition对象，该对象是一个非常重要的对象，里面容纳了一个Bean相关的所有属性
 * 10.BeanDefinition对象创建完毕后，Spring又会创建一个BeanDefinitionHolder对象来持有这个BeanDefinition对象
 * 11.BeanDefinitionHolder对象主要包含两部分内容：BeanName与BeanDefinition
 * 12.工厂会将解析出来的Bean信息存放到内部的一个ConcurentHashMap中，该Map的键是BeanName（唯一），值是BeanDefinition对象
 * 13.调用Bean解析完毕的触发动作，从而触发相应的监听器的方法的执行（观察者模式）
 *
 *
 *
 *
 *
 *
 * @author <a href="mailto:clq_0707@163.com">Bruce</a>
 * @date 2020/10/17
 */
public class SpringClient {
    public static void main(String[] args) {
        Resource resource = new ClassPathResource("applicationContext.xml");

        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);

        beanDefinitionReader.loadBeanDefinitions(resource);

        Student student = (Student) defaultListableBeanFactory.getBean("student");

        System.out.println(student.getName() );
    }
}

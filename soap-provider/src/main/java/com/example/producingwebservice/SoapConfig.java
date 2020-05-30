package com.example.producingwebservice;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

// enables SOAP Web Service features in this Spring Boot application
@EnableWs
@Configuration
public class SoapConfig
        // The WebServiceConfig class extends the WsConfigurerAdapter base class,
        // which configures the annotation-driven Spring-WS programming model.
        extends WsConfigurerAdapter {

    public static final String TARGET_NAMESPACE = "http://domain.ch/ws/country-namespace";

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();

        // We set the injected ApplicationContext object of the servlet so that Spring-WS can find other Spring beans.
        servlet.setApplicationContext(applicationContext);

        // We also enable the WSDL location servlet transformation.
        // This transforms the location attribute of soap:address in the WSDL so that it reflects the URL
        // of the incoming request.
        servlet.setTransformWsdlLocations(true);

        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "countries")
    // let's create a DefaultWsdl11Definition object. This exposes a standard WSDL 1.1 using an XsdSchema.
    // The WSDL name will be the same as the bean name.
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CountriesPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace(TARGET_NAMESPACE);
        wsdl11Definition.setSchema(countriesSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema countriesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("countries.xsd"));
    }
}

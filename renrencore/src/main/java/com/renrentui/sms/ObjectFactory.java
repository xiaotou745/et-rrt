
package com.renrentui.sms;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.renrentui.sms package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.renrentui.sms
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HelloWorld }
     * 
     */
    public HelloWorld createHelloWorld() {
        return new HelloWorld();
    }

    /**
     * Create an instance of {@link HelloWorldResponse }
     * 
     */
    public HelloWorldResponse createHelloWorldResponse() {
        return new HelloWorldResponse();
    }

    /**
     * Create an instance of {@link SendSmsSaveLog }
     * 
     */
    public SendSmsSaveLog createSendSmsSaveLog() {
        return new SendSmsSaveLog();
    }

    /**
     * Create an instance of {@link SendSmsSaveLogResponse }
     * 
     */
    public SendSmsSaveLogResponse createSendSmsSaveLogResponse() {
        return new SendSmsSaveLogResponse();
    }

}

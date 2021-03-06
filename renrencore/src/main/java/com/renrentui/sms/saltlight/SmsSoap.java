package com.renrentui.sms.saltlight;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.0.4
 * 2015-11-04T13:12:49.357+08:00
 * Generated source version: 3.0.4
 * 
 */
@WebService(targetNamespace = "http://tempuri.org/", name = "SmsSoap")
@XmlSeeAlso({ObjectFactory.class})
public interface SmsSoap {

    @WebMethod(operationName = "HelloWorld", action = "http://tempuri.org/HelloWorld")
    @RequestWrapper(localName = "HelloWorld", targetNamespace = "http://tempuri.org/", className = "com.renrentui.sms.saltlight.HelloWorld")
    @ResponseWrapper(localName = "HelloWorldResponse", targetNamespace = "http://tempuri.org/", className = "com.renrentui.sms.saltlight.HelloWorldResponse")
    @WebResult(name = "HelloWorldResult", targetNamespace = "http://tempuri.org/")
    public java.lang.String helloWorld();

    @WebMethod(operationName = "SendSmsSaveLog", action = "http://tempuri.org/SendSmsSaveLog")
    @RequestWrapper(localName = "SendSmsSaveLog", targetNamespace = "http://tempuri.org/", className = "com.renrentui.sms.saltlight.SendSmsSaveLog")
    @ResponseWrapper(localName = "SendSmsSaveLogResponse", targetNamespace = "http://tempuri.org/", className = "com.renrentui.sms.saltlight.SendSmsSaveLogResponse")
    @WebResult(name = "SendSmsSaveLogResult", targetNamespace = "http://tempuri.org/")
    public java.lang.String sendSmsSaveLog(
        @WebParam(name = "mobile", targetNamespace = "http://tempuri.org/")
        java.lang.String mobile,
        @WebParam(name = "content", targetNamespace = "http://tempuri.org/")
        java.lang.String content,
        @WebParam(name = "supplierId", targetNamespace = "http://tempuri.org/")
        int supplierId,
        @WebParam(name = "smsSource", targetNamespace = "http://tempuri.org/")
        java.lang.String smsSource
    );
}

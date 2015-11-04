package com.renrentui.sms.saltlight;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.0.4
 * 2015-11-04T13:12:49.361+08:00
 * Generated source version: 3.0.4
 * 
 */
@WebServiceClient(name = "Sms", 
                  wsdlLocation = "http://sms.saltlight.cn/Sms.asmx?wsdl",
                  targetNamespace = "http://tempuri.org/") 
public class Sms extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://tempuri.org/", "Sms");
    public final static QName SmsSoap = new QName("http://tempuri.org/", "SmsSoap");
    public final static QName SmsSoap12 = new QName("http://tempuri.org/", "SmsSoap12");
    static {
        URL url = null;
        try {
            url = new URL("http://sms.saltlight.cn/Sms.asmx?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(Sms.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://sms.saltlight.cn/Sms.asmx?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public Sms(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public Sms(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Sms() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns SmsSoap
     */
    @WebEndpoint(name = "SmsSoap")
    public SmsSoap getSmsSoap() {
        return super.getPort(SmsSoap, SmsSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SmsSoap
     */
    @WebEndpoint(name = "SmsSoap")
    public SmsSoap getSmsSoap(WebServiceFeature... features) {
        return super.getPort(SmsSoap, SmsSoap.class, features);
    }
    /**
     *
     * @return
     *     returns SmsSoap
     */
    @WebEndpoint(name = "SmsSoap12")
    public SmsSoap getSmsSoap12() {
        return super.getPort(SmsSoap12, SmsSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SmsSoap
     */
    @WebEndpoint(name = "SmsSoap12")
    public SmsSoap getSmsSoap12(WebServiceFeature... features) {
        return super.getPort(SmsSoap12, SmsSoap.class, features);
    }

}
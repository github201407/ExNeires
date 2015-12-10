package com.jen.change.exneires.splash;

import android.util.Log;

import junit.framework.TestCase;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Administrator on 2015/12/10.
 */
public class WebServiceUtilsTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();

    }

    public void testDown() throws Exception {
    //通过工具类调用WebService接口
        WebServiceUtils.callWebService(WebServiceUtils.WEB_SERVER_URL, "getSupportProvince", null, new WebServiceUtils.WebServiceCallBack() {

            //WebService接口返回的数据回调到这个方法中
            @Override
            public void callBack(SoapObject result) {
                //关闭进度条
                //Log.e("result", "" + result.toString());
                assertEquals("hello", result.toString());
            }
        });
    }

    public void testWebService(){
        // 命名空间
        String nameSpace = "http://WebXml.com.cn/";
        // 调用的方法名称
        String methodName = "getMobileCodeInfo";
        // EndPoint
        String endPoint = "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx";
        // SOAP Action
        String soapAction = "http://WebXml.com.cn/getMobileCodeInfo";

        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);

        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId
        rpc.addProperty("mobileCode", "15260663933");
        rpc.addProperty("userId", "");

        // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.bodyOut = rpc;
        // 设置是否调用的是dotNet开发的WebService
        envelope.dotNet = true;
        // 等价于envelope.bodyOut = rpc;
        envelope.setOutputSoapObject(rpc);

        HttpTransportSE transport = new HttpTransportSE(endPoint);
        try {
            // 调用WebService
            transport.call(soapAction, envelope);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 获取返回的数据
        SoapObject object = (SoapObject) envelope.bodyIn;
        // 获取返回的结果
        assertNotNull(object);
        String result = object.getProperty(0).toString();
        Log.e("result", result);
    }

    public void testWebServiceWSDL(){
        // 命名空间
        String nameSpace = "http://schemas.xmlsoap.org/soap/encoding/";
        // 调用的方法名称
        String methodName = "getMobileCodeInfo";
        // EndPoint
        String endPoint = "http://192.168.0.117:9818/uums.nsf/DirectoryManage?OpenWebService";
        // SOAP Action
        String soapAction = "http://WebXml.com.cn/getMobileCodeInfo";

        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);

        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId
        rpc.addProperty("mobileCode", "15260663933");
        rpc.addProperty("userId", "");

        // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.bodyOut = rpc;
        // 设置是否调用的是dotNet开发的WebService
        envelope.dotNet = true;
        // 等价于envelope.bodyOut = rpc;
        envelope.setOutputSoapObject(rpc);

        HttpTransportSE transport = new HttpTransportSE(endPoint);
        try {
            // 调用WebService
            transport.call(soapAction, envelope);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 获取返回的数据
        SoapObject object = (SoapObject) envelope.bodyIn;
        // 获取返回的结果
        assertNotNull(object);
        String result = object.getProperty(0).toString();
        Log.e("result", result);
    }
}
package com.azcx9d.pay.util;

import com.azcx9d.pay.entity.Order;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

/**
 * Jaxb2工具类
 */
@XmlRootElement
public class JaxbUtil {

    /**
     * JavaBean转换成xml
     *
     * @param obj
     * @param encoding
     * @return
     */
    public static String convertToXml(Object obj,String encoding) {

        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //注意jdk版本
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory
                    .createXMLStreamWriter(baos, (String) marshaller
                            .getProperty(Marshaller.JAXB_ENCODING));
            xmlStreamWriter.writeStartDocument(
                    (String) marshaller.getProperty(Marshaller.JAXB_ENCODING),
                    "1.0");
            marshaller.marshal(obj, xmlStreamWriter);
            xmlStreamWriter.writeEndDocument();
            xmlStreamWriter.close();
            return new String(baos.toString(encoding));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    /**
     * xml转换成JavaBean
     *
     * @param xml
     * @param c
     * @return
     */
    public static <T> T converyToJavaBean(String xml, Class<T> c) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return t;
    }

    public static void main(String[] args) {
        Order order = new Order();
        order.setMCHNTCD("0002900F0022256");
        order.setTYPE(10);
        order.setVERSION("2.0");
        order.setLOGOTP(0);
        order.setMCHNTORDERID("9632577412589332544");
        order.setUSERID(123456);
        order.setAMT(1100000);
        order.setBANKCARD("123712");
        order.setBACKURL("quantuanle.ssstc.cn");
        order.setREURL("quantuanle.ssstc.cn");
        order.setHOMEURL("quantuanle.ssstc.cn");
        order.setNAME("fby");
        order.setIDTYPE(0);
        order.setIDNO("1982739182739812739");
        order.setSIGNTP("md5");
        order.setSIGN("TYPE+\"|\"+VERSION+\"|\"+MCHNTCD+\"|\"+MCHNTORDERID+\"|\"+USERID+\"|\"+AMT+\"|\"+BANKCARD+\"|\"+BACKURL+\n" +
                "必填\n" +
                "2908283de2814a64dc3b9b12e93915bc\n" +
                "详见3.4加密方式\n" +
                "商户接入手册\n" +
                "6\n" +
                "\"|\"+NAME+\"|\"+IDNO+\"|\"+IDTYPE+\"|\"+LOGOTP+\"|\"+\" HOMERUL+\"|\"+\" REURL+\"|\"+\"商户32位密钥key\"");
        System.out.println(JaxbUtil.convertToXml(order,"utf-8"));
    }

}
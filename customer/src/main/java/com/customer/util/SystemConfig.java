package com.customer.util;

import com.customer.exception.FileUpException;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import javax.servlet.jsp.JspWriter;
import java.net.URL;


/**
 * Created by fangbaoyan on 2017/3/28.
 */
public class SystemConfig {

    private static SAXReader reader;
    private static Document doc;


    public static void load(){
        try
        {
            reader = new SAXReader();
            URL url = SystemConfig.class.getClassLoader().getResource("fileUploadConfig.xml");

            doc = reader.read(url);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    public static void load(JspWriter out){
        try
        {
            reader = new SAXReader();
            URL url = SystemConfig.class.getClassLoader().getResource("fileUploadConfig.xml");
            doc = reader.read(url);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static String getProperty(String type, String parameters, String parameter) throws FileUpException{
        if(doc == null){
            load();
        }

        Node node = doc.selectSingleNode("//propertie[@type='"+type+"']/parameters[@name='"+parameters+"']/parameter[@name='"+parameter+"']");
        if(node != null){
            return node.getText();
        }
        throw new FileUpException("不支持的type参数");
    }

    public static void main(String[] args)
    {
        try {
            String filePath1 = SystemConfig.getProperty("file", "a", "phycial") +
                    SystemConfig.getProperty("file", "a", "url");

            System.out.println(filePath1);
        } catch (FileUpException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (Exception e) {
             e.printStackTrace();
        }
    }
}

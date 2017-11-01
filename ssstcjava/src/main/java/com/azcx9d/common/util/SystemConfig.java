package com.azcx9d.common.util;

import javax.servlet.jsp.JspWriter;
import java.net.URL;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import javax.servlet.jsp.JspWriter;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;


/**
 * Created by fangbaoyan on 2017/3/28.
 */
public class SystemConfig {

    private static SAXReader reader;
    private static Document doc;


    public static void load()
            throws Exception
    {
        try
        {
            reader = new SAXReader();
            URL url = SystemConfig.class.getClassLoader().getResource("devproperties.xml");

            doc = reader.read(url);
        }
        catch(Exception e)
        {
            e.printStackTrace();

        }
    }


    public static void load(JspWriter out)
            throws Exception
    {
        try
        {
            reader = new SAXReader();
            URL url = SystemConfig.class.getClassLoader().getResource("devproperties.xml");
            doc = reader.read(url);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static String getProperty(String type, String parameters, String parameter)
            throws Exception{

        String value = "";
        try
        {
            if(doc == null)
                load();

            Node node = doc.selectSingleNode("//propertie[@type='"+type+"']/parameters[@name='"+parameters+"']/parameter[@name='"+parameter+"']");
            if(node != null){
                value = node.getText();
            }
            return value;
        }
        catch(Exception e)
        {
            throw new Exception(e.toString());
        }
    }

    public static void main(String[] args)
    {
        try {
            String filePath1 = SystemConfig.getProperty("picture", "business",
                    "phycial")+SystemConfig.getProperty("picture","business","url");

            System.out.println(filePath1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

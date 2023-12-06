package utils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;

public class ConfigUtil {
    public ConfigUtil() {
    }

    public static void writeConfig(File file, Config config) {
        Document document = DocumentHelper.createDocument();
        Element configElement = document.addElement("config");
        Element intervalElement = configElement.addElement("interval");
        intervalElement.addAttribute("desc", config.getInterval_desc());
        intervalElement.setText(Integer.toString(config.getInterval()));
        Element pagesizeElement = configElement.addElement("pagesize");
        pagesizeElement.addAttribute("desc", config.getPagesize_desc());
        pagesizeElement.setText(Integer.toString(config.getPagesize()));
        Element timegapElement = configElement.addElement("timegap");
        timegapElement.addAttribute("desc", config.getTimegap_desc());
        timegapElement.setText(Integer.toString(config.getTimegap()));
        Element minfilesizeElement = configElement.addElement("minfilesize");
        minfilesizeElement.addAttribute("desc", config.getMinfilesize_desc());
        minfilesizeElement.setText(Integer.toString(config.getMinfilesize()));
        Element maxfilesizeElement = configElement.addElement("maxfilesize");
        maxfilesizeElement.addAttribute("desc", config.getMaxfilesize_desc());
        maxfilesizeElement.setText(Integer.toString(config.getMaxfilesize()));
        Element candeleteElement = configElement.addElement("candelete");
        candeleteElement.addAttribute("desc", config.getCandelete_desc());
        candeleteElement.setText(Boolean.toString(config.isCandelete()));

        try {
            //FileWriter fileWriter = new FileWriter(file);
            OutputStreamWriter out = new OutputStreamWriter (new FileOutputStream (file),"UTF-8");
            OutputFormat xmlFormat = new OutputFormat();
            xmlFormat.setEncoding("UTF-8");
            xmlFormat.setNewlines(true);
            xmlFormat.setLineSeparator("\r\n");
            xmlFormat.setIndent(true);
            xmlFormat.setIndent("\t");
            XMLWriter xmlWriter = new XMLWriter(out, xmlFormat);
            xmlWriter.write(document);
            xmlWriter.close();
        } catch (IOException var13) {
            var13.printStackTrace();
        }

    }

    public static Config parseConfig(File file){
        Config config = new Config();
        SAXReader saxReader = new SAXReader();
            try{
                Document document = saxReader.read(new FileInputStream(file));
                Element rootElement = document.getRootElement();
                Element element = rootElement.element("interval");
                String desc = element.attributeValue("desc");
                config.setInterval_desc(desc);
                String value = element.getText().trim();
                config.setInterval(Integer.parseInt(value));
                element = rootElement.element("pagesize");
                desc = element.attributeValue("desc");
                config.setPagesize_desc(desc);
                value = element.getText().trim();
                config.setPagesize(Integer.parseInt(value));
                element = rootElement.element("timegap");
                desc = element.attributeValue("desc");
                config.setTimegap_desc(desc);
                value = element.getText().trim();
                config.setTimegap(Integer.parseInt(value));
                element = rootElement.element("minfilesize");
                desc = element.attributeValue("desc");
                config.setMinfilesize_desc(desc);
                value = element.getText().trim();
                config.setMinfilesize(Integer.parseInt(value));
                element = rootElement.element("maxfilesize");
                desc = element.attributeValue("desc");
                config.setMaxfilesize_desc(desc);
                value = element.getText().trim();
                config.setMaxfilesize(Integer.parseInt(value));
                element = rootElement.element("candelete");
                desc = element.attributeValue("desc");
                config.setCandelete_desc(desc);
                value = element.getText().trim();
                config.setCandelete(Boolean.parseBoolean(value));
            }catch (Exception e){
                e.printStackTrace();
            }

        return config;
    }
}

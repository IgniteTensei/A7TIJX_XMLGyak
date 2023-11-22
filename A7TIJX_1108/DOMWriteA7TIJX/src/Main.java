import org.w3c.dom.*;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List lista = new ArrayList();

        StringWriter stringWriter = new StringWriter();
        String subscrXML=null;
        DOMImplementationRegistry registry = null;
        DOMImplementationLS impls = null;
        LSOutput domOutput = null;
        LSSerializer domWriter = null;

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(
                    "C:\\Users\\IgniteTensei\\Desktop\\Egyetem\\9\\XML\\A7TIJX_XMLGyak\\A7TIJX_0927\\a7tijx_orarend.xml"
            );

            registry =  DOMImplementationRegistry.newInstance();
            impls =  (DOMImplementationLS)registry.getDOMImplementation("LS");

            NodeList orak = doc.getElementsByTagName("ora");

            ArrayList<String> oraGyerekekNeve = new ArrayList<String>();

            for (int i = 0; i < orak.item(0).getChildNodes().getLength(); i++){
                if(!orak.item(0).getChildNodes().item(i).getNodeName().equals("#text")){
                    String temp = orak.item(0).getChildNodes().item(i).getNodeName();
                    oraGyerekekNeve.add(temp.substring(0,1).toUpperCase() + temp.substring(1,temp.length()));
                }
            }

            for (int i = 0; i < orak.getLength(); i++){
                NodeList orakAdatok = orak.item(i).getChildNodes();
                NamedNodeMap orakAttr = orak.item(i).getAttributes();

                int k = 0;
                for (int j = 0; j < orakAdatok.getLength(); j++){
                    if(!orakAdatok.item(j).toString().contains("   ")){
                        Node node = orakAdatok.item(j);
                        String gyerekNev = oraGyerekekNeve.get(k).toString();
                        if(!gyerekNev.equals("Idopont")){
                            System.out.println(gyerekNev + ": " + node.getTextContent());
                        }
                        else{
                            System.out.println(gyerekNev + "(mettől meddig): " + node.getTextContent());
                        }

                        k++;
                    }
                }

                k = 0;
                System.out.println("---------");
            }

            domOutput = impls.createLSOutput();
            domOutput.setEncoding("a7tijx_orarend1.xml");
            domOutput.setCharacterStream(stringWriter);
            domOutput.setEncoding("UTF-8");

            domWriter = impls.createLSSerializer();
            DOMConfiguration domConfig = domWriter.getDomConfig();
            domConfig.setParameter("format-pretty-print", true);
            domConfig.setParameter("element-content-whitespace", true);
            domWriter.setNewLine("\r\n");
            domConfig.setParameter("cdata-sections", Boolean.TRUE);

            domWriter.write(doc, domOutput);
            subscrXML = domOutput.getCharacterStream().toString();

        }
        catch (Exception ee) {
            ee.printStackTrace();
        }
    }
}
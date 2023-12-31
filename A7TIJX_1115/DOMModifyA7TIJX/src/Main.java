import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try{
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse("A7TIJX_orarend.xml");
            document.getDocumentElement().normalize();

            for(int i = 0; i < document.getElementsByTagName("ora").getLength(); i++){
                if(document.getElementsByTagName("ora").item(i).getAttributes().item(1).getTextContent().equals("eloadas")){
                    document.getElementsByTagName("ora").item(i).getAttributes().item(1).setTextContent("gyakorlat");
                }
            }

            try {
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(document);
                FileOutputStream output = new FileOutputStream("A7TIJX_orarend_mod.xml");
                StreamResult streamResult = new StreamResult(output);
                transformer.transform(domSource, streamResult);
                transformer.transform(domSource, new StreamResult(System.out));

            } catch (Exception e) {
                e.printStackTrace();
            };

        }
        catch (Exception ee){
            ee.printStackTrace();
        }


    }
}
package hu.domparse.a7tijx;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;

public class DOMWriteA7TIJX {
    public static void main(String[] args) {
        try{
            //XML dokumentum read
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse("XMLA7TIJX.xml");
            document.getDocumentElement().normalize();

            try {
                //XML dokumentum write
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(document);
                FileOutputStream output = new FileOutputStream("XMLA7TIJX1.xml");
                StreamResult streamResult = new StreamResult(output);
                transformer.transform(domSource, streamResult);
                transformer.transform(domSource, new StreamResult(System.out));

            } catch (Exception e) {
                e.printStackTrace();
            };

        }
        catch (Exception ee) {
            ee.printStackTrace();
        }

    }
}

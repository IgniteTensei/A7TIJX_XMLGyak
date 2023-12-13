package hu.domparse.a7tijx;

import hu.domparse.a7tijx.elements.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.util.List;

import static hu.domparse.a7tijx.elements.Author.makeAuthorList;
import static hu.domparse.a7tijx.elements.Book.makeBookList;
import static hu.domparse.a7tijx.elements.Bookstore.makeBookstoreList;
import static hu.domparse.a7tijx.elements.Magazine.makeMagazineList;
import static hu.domparse.a7tijx.elements.President.makePresidentList;
import static hu.domparse.a7tijx.elements.Publisher.makePublisherList;
import static hu.domparse.a7tijx.elements.Selling.makeSellingList;
import static hu.domparse.a7tijx.elements.Series.makeSeriesList;

public class DomModifyA7TIJX {
    public static void main(String[] args) {
        try{
            //XML dokumentum read
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse("XMLA7TIJX.xml");
            document.getDocumentElement().normalize();

            //XML dokumentum modify
            modifyElementXML(document, "Series", 0, "SeriesName", "Bomby Girl");
            modifyElementXML(document, "Magazine", 2, "PublishingFrequency", "Monthly");
            modifyElementXML(document, "Publisher", 1, "PhoneNumber", "123-4567-8910");
            modifyElementXML(document, "President", 0, "PresidentGender", "Female");
            modifyAttributeXML(document, "Selling", 4, "SBookID", "55");
            modifyAttributeXML(document, "Book", 3, "BSeries", "4");

            try {
                //XML dokumentum write
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(document);
                FileOutputStream output = new FileOutputStream("XMLA7TIJXmodify.xml");
                StreamResult streamResult = new StreamResult(output);
                transformer.transform(domSource, streamResult);
                transformer.transform(domSource,  new StreamResult(System.out));

            } catch (Exception e) {
                e.printStackTrace();
            };

        }
        catch (Exception ee) {
            ee.printStackTrace();
        }

    }

    //Ha elemet akarunk módosítani
    public static void modifyElementXML(Document document, String mainTag, int mainTagItem, String targetTag, String modifiedString){
            for(int i = 0; i < document.getElementsByTagName(mainTag).item(mainTagItem).getChildNodes().getLength(); i++){
                if(document.getElementsByTagName(mainTag).item(mainTagItem).getChildNodes().item(i).getNodeName() == targetTag){
                    System.out.println("Before: " + document.getElementsByTagName(mainTag).item(mainTagItem).getChildNodes().item(i).getTextContent());
                    document.getElementsByTagName(mainTag).item(mainTagItem).getChildNodes().item(i).setTextContent(modifiedString);
                    System.out.println("After: " + document.getElementsByTagName(mainTag).item(mainTagItem).getChildNodes().item(i).getTextContent());
                }
            }
            System.out.println("-----------------------");

    }

    //Ha attribútumot akarunk módasítani
    public static void modifyAttributeXML(Document document, String mainTag, int mainTagItem, String targetAttribute, String modifiedString){
            for(int i = 0; i < document.getElementsByTagName(mainTag).item(mainTagItem).getAttributes().getLength(); i++){
                if(document.getElementsByTagName(mainTag).item(mainTagItem).getAttributes().item(i).getNodeName() == targetAttribute){
                    System.out.println("Before: " + document.getElementsByTagName(mainTag).item(mainTagItem).getAttributes().item(i).getTextContent());
                    document.getElementsByTagName(mainTag).item(mainTagItem).getAttributes().item(i).setTextContent(modifiedString);
                    System.out.println("After: " + document.getElementsByTagName(mainTag).item(mainTagItem).getAttributes().item(i).getTextContent());
                }
            }
            System.out.println("-----------------------");
    }

}

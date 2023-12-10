import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try{
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse("A7TIJX_orarend.xml");
            document.getDocumentElement().normalize();

            System.out.print("Kurzusok: ");
            NodeList nl1 = document.getElementsByTagName("targy");
            for (int i = 0; i < nl1.getLength(); i++) {
                System.out.print(nl1.item(i).getTextContent() + "| ");
            }

            for (int i = 0; i < document.getElementsByTagName("ora").item(0).getChildNodes().getLength(); i++){
                System.out.println(document.getElementsByTagName("ora").item(0).getChildNodes().item(i).getTextContent());
            }

            System.out.print("Oktatók: ");
            NodeList nl2 = document.getElementsByTagName("oktato");
            List<String> okt = new ArrayList<String>();
            for (int i = 0; i < nl2.getLength(); i++) {
                okt.add(nl2.item(i).getTextContent());
            }

            for (int i = 0; i < okt.size(); i++) {
                String temp = okt.get(i).toString();
                System.out.println(temp);
                for (int j = 0; j < okt.size(); j++) {
                    okt.remove(temp);
                }
            }

        }
        catch (Exception ee){
            ee.printStackTrace();
        }


    }
}
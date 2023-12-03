package hu.domparse.a7tijx;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

import hu.domparse.a7tijx.elements.*;

import static hu.domparse.a7tijx.elements.Series.makeSeriesList;

public class DomReadA7TIJX {
    public static void main(String[] args) {
        try{
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse("XMLA7TIJX.xml");
            document.getDocumentElement().normalize();

            NodeList kkiado_a7tijx = document.getElementsByTagName("Kepregeny_kiado_A7TIJX");

            NodeList SeriesNodeList = document.getElementsByTagName("Series");
            NodeList AuthorNodeList = document.getElementsByTagName("Author");
            NodeList MagazineNodeList = document.getElementsByTagName("Magazine");
            NodeList PublisherNodeList = document.getElementsByTagName("Publisher");
            NodeList PresidentNodeList = document.getElementsByTagName("President");
            NodeList BookNodeList = document.getElementsByTagName("Book");
            NodeList SellingNodeList = document.getElementsByTagName("Selling");
            NodeList BookstoreNodeList = document.getElementsByTagName("Bookstore");

            List<Series> seriesList = makeSeriesList(SeriesNodeList);
            Series testSeries = seriesList.get(3);

            System.out.println(testSeries.seriesName);

        }
        catch (Exception ee) {
        ee.printStackTrace();
        }

    }


}





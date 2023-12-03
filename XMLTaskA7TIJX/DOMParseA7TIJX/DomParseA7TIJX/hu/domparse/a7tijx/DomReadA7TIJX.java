package hu.domparse.a7tijx;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

import hu.domparse.a7tijx.elements.*;

import static hu.domparse.a7tijx.elements.Author.makeAuthorList;
import static hu.domparse.a7tijx.elements.Book.makeBookList;
import static hu.domparse.a7tijx.elements.Bookstore.makeBookstoreList;
import static hu.domparse.a7tijx.elements.Magazine.makeMagazineList;
import static hu.domparse.a7tijx.elements.President.makePresidentList;
import static hu.domparse.a7tijx.elements.Publisher.makePublisherList;
import static hu.domparse.a7tijx.elements.Selling.makeSellingList;
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





package hu.domparse.a7tijx;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

import hu.domparse.a7tijx.elements.*;

import static hu.domparse.a7tijx.elements.Author.makeAuthorList;
import static hu.domparse.a7tijx.elements.Magazine.makeMagazineList;
import static hu.domparse.a7tijx.elements.Publisher.makePublisherList;
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

            /*List<Series> seriesList = makeSeriesList(SeriesNodeList);
            Series testSeries = seriesList.get(3);

            System.out.println(testSeries.seriesName);*/

            /*List<Author> authorList = makeAuthorList(AuthorNodeList);
            Author testAuthor = authorList.get(1);

            System.out.println(testAuthor.authorName);*/

            /*List<Magazine> magazineList = makeMagazineList(MagazineNodeList);
            Magazine testMagazine = magazineList.get(1);

            System.out.println(testMagazine.magazineID);
            System.out.println(testMagazine.magazineName);
            System.out.println(testMagazine.magazinePrice);
            System.out.println(testMagazine.demographic);*/

            List<Publisher> publisherList = makePublisherList(PublisherNodeList);
            Publisher testPublisher = publisherList.get(1);

            System.out.println(testPublisher.publisherName);
            System.out.println(testPublisher.phoneNumber);
            System.out.println(testPublisher.pPresident);

        }
        catch (Exception ee) {
        ee.printStackTrace();
        }

    }


}





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

            //Node listák kiszedése a dokumentumból
            NodeList kkiado_a7tijx = document.getElementsByTagName("Kepregeny_kiado_A7TIJX");

            NodeList SeriesNodeList = document.getElementsByTagName("Series");
            NodeList AuthorNodeList = document.getElementsByTagName("Author");
            NodeList MagazineNodeList = document.getElementsByTagName("Magazine");
            NodeList PublisherNodeList = document.getElementsByTagName("Publisher");
            NodeList PresidentNodeList = document.getElementsByTagName("President");
            NodeList BookNodeList = document.getElementsByTagName("Book");
            NodeList SellingNodeList = document.getElementsByTagName("Selling");
            NodeList BookstoreNodeList = document.getElementsByTagName("Bookstore");

            //Node listákból elem listákat csinálunk
            List<Series> seriesList = makeSeriesList(SeriesNodeList);
            List<Author> authorList = makeAuthorList(AuthorNodeList);
            List<Magazine> magazineList = makeMagazineList(MagazineNodeList);
            List<Publisher> publisherList = makePublisherList(PublisherNodeList);
            List<President> presidentList = makePresidentList(PresidentNodeList);
            List<Book> bookList = makeBookList(BookNodeList);
            List<Selling> sellingList = makeSellingList(SellingNodeList);
            List<Bookstore> bookstoreList = makeBookstoreList(BookstoreNodeList);

            //Teljes dokumentum kiírása egyedi strukturált formában

            System.out.println("\n-------SERIES-------\n");

            for (Series s : seriesList) {
                System.out.println("Series:");
                System.out.println("Attributes: SeriesID=" + s.seriesID + ", SMagazine=" + s.sMagazine + ", SAuthor=" + s.sAuthor);
                System.out.print("Elements: SeriesName=" + s.seriesName + ", Ranking=" + s.ranking + ", NumOfReaders=" + s.numOfReaders + ", Genres: ");
                for (String subgenre : s.subGenre) {
                    System.out.print("Subgenre=" + subgenre + " ");
                }
                System.out.print("\n");
                System.out.println("-----------------------");
            }

            System.out.println("\n-------AUTHOR-------\n");

            for (Author a : authorList) {
                System.out.println("Author:");
                System.out.println("Attributes: AuthorID=" + a.authorID);
                System.out.println("Elements: AuthorName=" + a.authorName + ", AuthorGender=" + a.authorGender + ", AuthorAge=" + a.authorAge);
                System.out.println("-----------------------");
            }

            System.out.println("\n-------MAGAZINE-------\n");

            for (Magazine m : magazineList) {
                System.out.println("Magazine:");
                System.out.println("Attributes: MagazineID=" + m.magazineID + ", MPublisher=" + m.mPublisher);
                System.out.println("Elements: MagazineName=" + m.magazineName + ", PublishingFrequency=" + m.publishingFrequency + ", Demographic=" + m.demographic + ", MagazinePrice=" + m.magazinePrice);
                System.out.println("-----------------------");
            }

            System.out.println("\n-------PUBLISHER-------\n");

            for (Publisher pu : publisherList) {
                System.out.println("Publisher:");
                System.out.println("Attributes: PublisherID=" + pu.publisherID + ", PPresident=" + pu.pPresident);
                System.out.println("Elements: PublisherName=" + pu.publisherName + ", PhoneNumber=" + pu.phoneNumber + ", EmailAddress=" + pu.emailAddress + ", HQAddress: PCity=" + pu.pCity + ", PStreet=" + pu.pStreet + ", PHouseNumber=" + pu.pHouseNumber);
                System.out.println("-----------------------");
            }

            System.out.println("\n-------PRESIDENT-------\n");

            for (President pr : presidentList) {
                System.out.println("President:");
                System.out.println("Attributes: PresidentID=" + pr.presidentID);
                System.out.println("Elements: PresidentName=" + pr.presidentName + ", PresidentGender=" + pr.presidentGender + ", PresidentAge=" + pr.presidentAge);
                System.out.println("-----------------------");
            }

            System.out.println("\n-------BOOK-------\n");

            for (Book b : bookList) {
                System.out.println("Book:");
                System.out.println("Attributes: BookID=" + b.bookID + ", BSeries=" + b.bSeries);
                System.out.println("Elements: BookName=" + b.bookName + ", BookPrice=" + b.bookPrice + ", NumOfPages=" + b.numOfPages + ", NumOfChapters=" + b.numOfChapters + ", VolumeNumber=" + b.volumeNumber);
                System.out.println("-----------------------");
            }

            System.out.println("\n-------SELLING-------\n");

            for (Selling sl : sellingList) {
                System.out.println("Selling:");
                System.out.println("Attributes: SBookID=" + sl.sBookID + ", SBookstoreID=" + sl.sBookstoreID);
                System.out.println("Elements:  NumOfSells=" + sl.numOfSells);
                System.out.println("-----------------------");
            }

            System.out.println("\n-------BOOKSTORE-------\n");

            for (Bookstore bs : bookstoreList) {
                System.out.println("Bookstore:");
                System.out.println("Attributes: BookstoreID=" + bs.bookstoreID);
                System.out.println("Elements: BookstoreName=" + bs.bookstoreName + ", StoreAddress: SCity=" + bs.sCity + "SStreet=" + bs.sStreet + "SHouseNumber=" + bs.sHouseNumber);
                System.out.println("-----------------------");
            }
        }
        catch (Exception ee) {
        ee.printStackTrace();
        }

    }


}





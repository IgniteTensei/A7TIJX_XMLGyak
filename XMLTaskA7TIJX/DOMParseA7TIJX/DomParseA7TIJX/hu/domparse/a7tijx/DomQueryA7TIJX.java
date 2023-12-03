package hu.domparse.a7tijx;

import hu.domparse.a7tijx.elements.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

import static hu.domparse.a7tijx.elements.Author.makeAuthorList;
import static hu.domparse.a7tijx.elements.Book.makeBookList;
import static hu.domparse.a7tijx.elements.Bookstore.makeBookstoreList;
import static hu.domparse.a7tijx.elements.Magazine.makeMagazineList;
import static hu.domparse.a7tijx.elements.President.makePresidentList;
import static hu.domparse.a7tijx.elements.Publisher.makePublisherList;
import static hu.domparse.a7tijx.elements.Selling.makeSellingList;
import static hu.domparse.a7tijx.elements.Series.makeSeriesList;

public class DomQueryA7TIJX {
    public static void main(String[] args) {
        try{
            //XML dokumentum read
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

            //Lekérdezések

            //Attack on Titan képregény rankja
            System.out.println("Attack on Titan képregény rankja: ");
            for (Series s : seriesList) {
                if(s.seriesName.equals("Attack on Titan")){
                    System.out.println(s.ranking);
                }
            }
            System.out.println("-------------");

            //A Sci-Fi műfajban jártas írók neve
            System.out.println("A Sci-Fi műfajban jártas írók neve: ");
            List<Integer> iID = new ArrayList<Integer>();
            for (Series s : seriesList) {
                if(s.subGenre.contains("Sci-fi")) {
                    iID.add(s.sAuthor);
                }
            }
            for (Author a : authorList) {
                for (int i : iID) {
                    if(a.authorID == i){
                        System.out.println(a.authorName);
                    }
                }
            }
            System.out.println("-------------");

            //Book First Umeda könyvesboltban árult könyvek
            System.out.println("Book First Umeda könyvesboltban árult könyvek: ");
            int bsID = -1;
            List<Integer> bukID = new ArrayList<Integer>();
            for (Bookstore bs : bookstoreList) {
                if(bs.bookstoreName.equals("Book First Umeda")){
                    bsID = bs.bookstoreID;
                }
            }
            for (Selling sell : sellingList) {
                if(sell.sBookstoreID == bsID){
                    bukID.add(sell.sBookID);
                }
            }
            for (Book b : bookList) {
                for (int i : bukID) {
                    if(b.bookID == i){
                        System.out.println(b.bookName);
                    }
                }
            }
            System.out.println("-------------");

            //Urasawa Naoki kiadójának a teljes címe
            System.out.println("Urasawa Naoki kiadójának a teljes címe: ");
            int uID = -1, mID = -1;
            for (Author auth : authorList) {
                if(auth.authorName.equals("Urasawa Naoki")){
                    uID = auth.authorID;
                }
            }
            for (Series si : seriesList) {
                if(si.sAuthor == uID){
                    mID = si.sMagazine;
                }
            }
            for (Magazine m : magazineList) {
                if(m.magazineID == mID){
                    for (Publisher p : publisherList){
                        if(m.mPublisher == p.publisherID){
                            System.out.println(p.publisherName);
                            System.out.println(p.pCity);
                            System.out.println(p.pStreet);
                            System.out.println(p.pHouseNumber);
                        }
                    }
                }
            }
            System.out.println("-------------");

            //Azoknak a könyveknek a nevének a lekérdezése amelyeket Fujimoto Tatsuki írt
            int aID = -1;
            List<Integer> sID = new ArrayList<Integer>();
            for (Author auth : authorList) {
                if(auth.authorName.equals("Fujimoto Tatsuki")){
                    aID = auth.authorID;
                }
            }
            for (Series seri : seriesList) {
                if(seri.sAuthor == aID){
                    sID.add(seri.seriesID);
                }
            }
            System.out.println("Fujimoto Tatsuki által készített könyvek:");
            for(Book book : bookList) {
                for (int s : sID) {
                    if(book.bSeries == s){
                        System.out.println(book.bookName);
                    }
                }
            }
            System.out.println("-------------");
        }
        catch (Exception ee) {
            ee.printStackTrace();
        }

    }
}

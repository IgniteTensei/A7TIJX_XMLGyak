package hu.domparse.a7tijx;

import hu.domparse.a7tijx.elements.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static hu.domparse.a7tijx.elements.Author.makeAuthorList;
import static hu.domparse.a7tijx.elements.Book.makeBookList;
import static hu.domparse.a7tijx.elements.Bookstore.makeBookstoreList;
import static hu.domparse.a7tijx.elements.Magazine.makeMagazineList;
import static hu.domparse.a7tijx.elements.President.makePresidentList;
import static hu.domparse.a7tijx.elements.Publisher.makePublisherList;
import static hu.domparse.a7tijx.elements.Selling.makeSellingList;
import static hu.domparse.a7tijx.elements.Series.makeSeriesList;

public class DOMWriteA7TIJX {
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

            //Feltöltendő dokumentum elkészítése

            Document document2 = builder.newDocument();
            Element KepregenyE = document2.createElement("Kepregeny_kiado_A7TIJX_Built");

            document2.appendChild(KepregenyE);

            //Feltöltés

            //Series
            KepregenyE.appendChild(newSeries(document2, seriesList.get(0).seriesID, seriesList.get(0).sMagazine, seriesList.get(0).sAuthor, seriesList.get(0).seriesName, seriesList.get(0).ranking, seriesList.get(0).numOfReaders, seriesList.get(0).subGenre));
            KepregenyE.appendChild(newSeries(document2, seriesList.get(1).seriesID, seriesList.get(1).sMagazine, seriesList.get(1).sAuthor, seriesList.get(1).seriesName, seriesList.get(1).ranking, seriesList.get(1).numOfReaders, seriesList.get(1).subGenre));
            KepregenyE.appendChild(newSeries(document2, seriesList.get(2).seriesID, seriesList.get(2).sMagazine, seriesList.get(2).sAuthor, seriesList.get(2).seriesName, seriesList.get(2).ranking, seriesList.get(2).numOfReaders, seriesList.get(2).subGenre));

            //Author
            KepregenyE.appendChild(newAuthor(document2, authorList.get(0).authorID, authorList.get(0).authorName, authorList.get(0).authorGender, authorList.get(0).authorAge));
            KepregenyE.appendChild(newAuthor(document2, authorList.get(1).authorID, authorList.get(1).authorName, authorList.get(1).authorGender, authorList.get(1).authorAge));
            KepregenyE.appendChild(newAuthor(document2, authorList.get(2).authorID, authorList.get(2).authorName, authorList.get(2).authorGender, authorList.get(2).authorAge));

            //Magazine
            KepregenyE.appendChild(newMagazine(document2, magazineList.get(0).magazineID, magazineList.get(0).mPublisher, magazineList.get(0).magazineName, magazineList.get(0).publishingFrequency, magazineList.get(0).demographic, magazineList.get(0).magazinePrice));
            KepregenyE.appendChild(newMagazine(document2, magazineList.get(1).magazineID, magazineList.get(1).mPublisher, magazineList.get(1).magazineName, magazineList.get(1).publishingFrequency, magazineList.get(1).demographic, magazineList.get(1).magazinePrice));
            KepregenyE.appendChild(newMagazine(document2, magazineList.get(2).magazineID, magazineList.get(2).mPublisher, magazineList.get(2).magazineName, magazineList.get(2).publishingFrequency, magazineList.get(2).demographic, magazineList.get(2).magazinePrice));

            //Publisher
            KepregenyE.appendChild(newPublisher(document2, publisherList.get(0).publisherID, publisherList.get(0).pPresident, publisherList.get(0).publisherName, publisherList.get(0).phoneNumber, publisherList.get(0).emailAddress, publisherList.get(0).pCity, publisherList.get(0).pStreet, publisherList.get(0).pHouseNumber));
            KepregenyE.appendChild(newPublisher(document2, publisherList.get(1).publisherID, publisherList.get(1).pPresident, publisherList.get(1).publisherName, publisherList.get(1).phoneNumber, publisherList.get(1).emailAddress, publisherList.get(1).pCity, publisherList.get(1).pStreet, publisherList.get(1).pHouseNumber));
            KepregenyE.appendChild(newPublisher(document2, publisherList.get(2).publisherID, publisherList.get(2).pPresident, publisherList.get(2).publisherName, publisherList.get(2).phoneNumber, publisherList.get(2).emailAddress, publisherList.get(2).pCity, publisherList.get(2).pStreet, publisherList.get(2).pHouseNumber));

            //President
            KepregenyE.appendChild(newPresident(document2, presidentList.get(0).presidentID, presidentList.get(0).presidentName, presidentList.get(0).presidentGender, presidentList.get(0).presidentAge));
            KepregenyE.appendChild(newPresident(document2, presidentList.get(1).presidentID, presidentList.get(1).presidentName, presidentList.get(1).presidentGender, presidentList.get(1).presidentAge));
            KepregenyE.appendChild(newPresident(document2, presidentList.get(2).presidentID, presidentList.get(2).presidentName, presidentList.get(2).presidentGender, presidentList.get(2).presidentAge));

            //Book
            KepregenyE.appendChild(newBook(document2, bookList.get(0).bookID, bookList.get(0).bSeries, bookList.get(0).bookName, bookList.get(0).bookPrice, bookList.get(0).numOfPages, bookList.get(0).numOfChapters, bookList.get(0).volumeNumber));
            KepregenyE.appendChild(newBook(document2, bookList.get(1).bookID, bookList.get(1).bSeries, bookList.get(1).bookName, bookList.get(1).bookPrice, bookList.get(1).numOfPages, bookList.get(1).numOfChapters, bookList.get(1).volumeNumber));
            KepregenyE.appendChild(newBook(document2, bookList.get(2).bookID, bookList.get(2).bSeries, bookList.get(2).bookName, bookList.get(2).bookPrice, bookList.get(2).numOfPages, bookList.get(2).numOfChapters, bookList.get(2).volumeNumber));

            //Selling
            KepregenyE.appendChild(newSelling(document2, sellingList.get(0).sBookID, sellingList.get(0).sBookstoreID, sellingList.get(0).numOfSells));
            KepregenyE.appendChild(newSelling(document2, sellingList.get(1).sBookID, sellingList.get(1).sBookstoreID, sellingList.get(1).numOfSells));
            KepregenyE.appendChild(newSelling(document2, sellingList.get(2).sBookID, sellingList.get(2).sBookstoreID, sellingList.get(2).numOfSells));

            //Bookstore
            KepregenyE.appendChild(newBookstore(document2, bookstoreList.get(0).bookstoreID, bookstoreList.get(0).bookstoreName, bookstoreList.get(0).sCity, bookstoreList.get(0).sStreet, bookstoreList.get(0).sHouseNumber));
            KepregenyE.appendChild(newBookstore(document2, bookstoreList.get(1).bookstoreID, bookstoreList.get(1).bookstoreName, bookstoreList.get(1).sCity, bookstoreList.get(1).sStreet, bookstoreList.get(1).sHouseNumber));
            KepregenyE.appendChild(newBookstore(document2, bookstoreList.get(2).bookstoreID, bookstoreList.get(2).bookstoreName, bookstoreList.get(2).sCity, bookstoreList.get(2).sStreet, bookstoreList.get(2).sHouseNumber));

            try {
                //XML dokumentum write
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(document2);
                FileOutputStream output = new FileOutputStream("XMLA7TIJX1write.xml");
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

    //Az feltölteni kívánt elemek elkészítése

    private static Element newSeries(Document doc,
                                     int seriesID, int sMagazine, int sAuthor,
                                     String seriesName, String ranking, int numOfReaders, List<String> subGenre){
        Element SeriesE = doc.createElement("Series");

        SeriesE.setAttribute("SeriesID", Integer.toString(seriesID));
        SeriesE.setAttribute("SMagazine", Integer.toString(sMagazine));
        SeriesE.setAttribute("SAuthor", Integer.toString(sAuthor));

        SeriesE.appendChild(newElement(doc,"SeriesName", seriesName));
        SeriesE.appendChild(newElement(doc, "Ranking", ranking));
        SeriesE.appendChild(newElement(doc,"NumOfReaders", Integer.toString(numOfReaders)));

        Element GenreE = doc.createElement("Genre");

        for(int i = 0; i < subGenre.size(); i++){
            GenreE.appendChild(newElement(doc,"Subgenre", subGenre.get(i)));
        }

        SeriesE.appendChild(GenreE);

        return SeriesE;
    }

    private static Element newAuthor(Document doc,
                                     int authorID,
                                     String authorName, String authorGender, int authorAge){
        Element AuthorE = doc.createElement("Author");

        AuthorE.setAttribute("AuthorID", Integer.toString(authorID));

        AuthorE.appendChild(newElement(doc, "AuthorName", authorName));
        AuthorE.appendChild(newElement(doc, "AuthorGender", authorGender));
        AuthorE.appendChild(newElement(doc, "AuthorAge", Integer.toString(authorAge)));

        return AuthorE;

    }

    private static Element newMagazine(Document doc,
                                       int magazineID, int mPublisher,
                                       String magazineName, String publishingFrequency, String demographic, String magazinePrice){
        Element MagazineE = doc.createElement("Magazine");

        MagazineE.setAttribute("MagazineID", Integer.toString(magazineID));
        MagazineE.setAttribute("MPublisher", Integer.toString(mPublisher));

        MagazineE.appendChild(newElement(doc, "MagazineName", magazineName));
        MagazineE.appendChild(newElement(doc, "PublishingFrequency", publishingFrequency));
        MagazineE.appendChild(newElement(doc, "Demographic", demographic));
        MagazineE.appendChild(newElement(doc, "MagazinePrice", magazinePrice));

        return MagazineE;

    }

    private static Element newPublisher(Document doc,
                                        int publisherID, int pPresident,
                                        String publisherName, String phoneNumber, String emailAddress, String pCity, String pStreet, String pHouseNumber){
        Element PublisherE = doc.createElement("Publisher");

        PublisherE.setAttribute("PublisherID", Integer.toString(publisherID));
        PublisherE.setAttribute("PPresident", Integer.toString(pPresident));

        PublisherE.appendChild(newElement(doc, "PublisherName", publisherName));
        PublisherE.appendChild(newElement(doc, "PhoneNumber", phoneNumber));
        PublisherE.appendChild(newElement(doc, "EmailAddress", emailAddress));

        Element HQAddressE = doc.createElement("HQAddress");

        HQAddressE.appendChild(newElement(doc, "PCity", pCity));
        HQAddressE.appendChild(newElement(doc, "PStreet", pStreet));
        HQAddressE.appendChild(newElement(doc, "PHouseNumber", pHouseNumber));

        PublisherE.appendChild(HQAddressE);

        return PublisherE;

    }

    private static Element newPresident(Document doc,
                                        int presidentID,
                                        String presidentName, String presidentGender, int presidentAge){
        Element PresidentE = doc.createElement("President");

        PresidentE.setAttribute("AuthorID", Integer.toString(presidentID));

        PresidentE.appendChild(newElement(doc, "PresidentName", presidentName));
        PresidentE.appendChild(newElement(doc, "PresidentGender", presidentGender));
        PresidentE.appendChild(newElement(doc, "PresidentAge", Integer.toString(presidentAge)));

        return PresidentE;
    }

    private static Element newBook(Document doc,
                                   int bookID, int bSeries,
                                   String bookName, String bookPrice, int numOfPages, int numOfChapters, int volumeNumber){
        Element BookE = doc.createElement("Book");

        BookE.setAttribute("BookID", Integer.toString(bookID));
        BookE.setAttribute("BSeries", Integer.toString(bSeries));

        BookE.appendChild(newElement(doc, "BookName", bookName));
        BookE.appendChild(newElement(doc, "BookPrice", bookPrice));
        BookE.appendChild(newElement(doc, "NumOfPages", Integer.toString(numOfPages)));
        BookE.appendChild(newElement(doc, "NumOfChapters", Integer.toString(numOfChapters)));
        BookE.appendChild(newElement(doc, "VolumeNumber", Integer.toString(volumeNumber)));

        return BookE;

    }

    private static Element newSelling(Document doc,
                                      int sBookID, int sBookstoreID,
                                      int numOfSells){
        Element SellingE = doc.createElement("Selling");

        SellingE.setAttribute("SBookID", Integer.toString(sBookID));
        SellingE.setAttribute("SBookstoreID", Integer.toString(sBookstoreID));

        SellingE.appendChild(newElement(doc, "NumOfSells", Integer.toString(numOfSells)));

        return SellingE;

    }

    private static Element newBookstore(Document doc,
                                        int bookstoreID,
                                        String bookstoreName, String sCity, String sStreet, String sHouseNumber){
        Element BookstoreE = doc.createElement("Bookstore");

        BookstoreE.setAttribute("BookstoreID", Integer.toString(bookstoreID));

        BookstoreE.appendChild(newElement(doc, "BookstoreName", bookstoreName));

        Element StoreAddressE = doc.createElement("StoreAddress");

        StoreAddressE.appendChild(newElement(doc,"SCity", sCity));
        StoreAddressE.appendChild(newElement(doc,"SStreet", sStreet));
        StoreAddressE.appendChild(newElement(doc,"SHouseNumber", sHouseNumber));

        BookstoreE.appendChild(StoreAddressE);

        return BookstoreE;

    }

    private static Element newElement(Document doc, String elementName, String listName) {
        Element element = doc.createElement(elementName);
        element.appendChild(doc.createTextNode(listName));
        return element;
    }

}

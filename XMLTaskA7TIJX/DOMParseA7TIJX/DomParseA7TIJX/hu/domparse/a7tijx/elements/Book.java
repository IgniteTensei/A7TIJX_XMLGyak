package hu.domparse.a7tijx.elements;

import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Book {
    //Attribútumok
    public int bookID;
    public int bSeries;

    //Elemek
    public String bookName;
    public String bookPrice;
    public int numOfPages;
    public int numOfChapters;
    public int volumeNumber;


    //Book példány feltöltésére szolgál (segédfüggvény)
    public static Book bookLoad(NodeList bookNodes, int item){
        Book book = new Book();

        //Feltölti az elemeket
        for(int i = 0; i < bookNodes.item(item).getChildNodes().getLength(); i++){
            String nodeName = bookNodes.item(item).getChildNodes().item(i).getNodeName();
            String nodeContent = bookNodes.item(item).getChildNodes().item(i).getTextContent();
            if(nodeName.equals("BookName")){book.bookName = nodeContent;}
            else if(nodeName.equals("BookPrice")){book.bookPrice = nodeContent;}
            else if(nodeName.equals("NumOfPages")){book.numOfPages = Integer.parseInt(nodeContent);}
            else if(nodeName.equals("NumOfChapters")){book.numOfChapters = Integer.parseInt(nodeContent);}
            else if(nodeName.equals("VolumeNumber")){book.volumeNumber = Integer.parseInt(nodeContent);}
        }

        //Feltölti az attribútumokat
        for(int i = 0; i < bookNodes.item(item).getAttributes().getLength(); i++){
            String nodeAttributeName = bookNodes.item(item).getAttributes().item(i).getNodeName();
            String nodeAttributeContent = bookNodes.item(item).getAttributes().item(i).getTextContent();

            if(nodeAttributeName.equals("BookID")){book.bookID = Integer.parseInt(nodeAttributeContent);}
            else if(nodeAttributeName.equals("BSeries")){book.bSeries = Integer.parseInt(nodeAttributeContent);}
        }

        return book;
    }

    //Book egy Book osztály alapú listát az XML dokumentumból kapott Book Node listájából
    public static List<Book> makeBookList(NodeList bookNodeList){
        List<Book> bookList = new ArrayList<Book>();
        for(int i = 0; i < bookNodeList.getLength(); i++){
            bookList.add(bookLoad(bookNodeList, i));
        }
        return bookList;
    }
}

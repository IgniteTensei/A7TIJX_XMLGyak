package hu.domparse.a7tijx.elements;

import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Bookstore {
    //Attribútumok
    public int bookstoreID;

    //Elemek
    public String bookstoreName;
    public String sCity;
    public String sStreet;
    public String sHouseNumber;

    //Bookstore példány feltöltésére szolgál (segédfüggvény)
    public static Bookstore bookstoreLoad(NodeList bookstoreNodes, int item){
        Bookstore bookstore = new Bookstore();

        //Feltölti az elemeket
        for(int i = 0; i < bookstoreNodes.item(item).getChildNodes().getLength(); i++){
            String nodeName = bookstoreNodes.item(item).getChildNodes().item(i).getNodeName();
            String nodeContent = bookstoreNodes.item(item).getChildNodes().item(i).getTextContent();
            if(nodeName.equals("BookstoreName")){bookstore.bookstoreName = nodeContent;}
            else if(nodeName.equals("StoreAddress")){
                for(int j = 0; j < bookstoreNodes.item(item).getChildNodes().item(i).getChildNodes().getLength(); j++){
                    String addressName = bookstoreNodes.item(item).getChildNodes().item(i).getChildNodes().item(j).getNodeName();
                    String addressContent = bookstoreNodes.item(item).getChildNodes().item(i).getChildNodes().item(j).getTextContent();
                    if(addressName.equals("SCity")){bookstore.sCity = addressContent;}
                    else if(addressName.equals("SStreet")){bookstore.sStreet = addressContent;}
                    else if(addressName.equals("SHouseNumber")){bookstore.sHouseNumber = addressContent;}
                }
            }
        }

        //Feltölti az attribútumokat
        for(int i = 0; i < bookstoreNodes.item(item).getAttributes().getLength(); i++){
            String nodeAttributeName = bookstoreNodes.item(item).getAttributes().item(i).getNodeName();
            String nodeAttributeContent = bookstoreNodes.item(item).getAttributes().item(i).getTextContent();

            if(nodeAttributeName.equals("BookstoreID")){bookstore.bookstoreID = Integer.parseInt(nodeAttributeContent);}
        }

        return bookstore;
    }

    //Készít egy Bookstore osztály alapú listát az XML dokumentumból kapott Bookstore Node listájából
    public static List<Bookstore> makeBookstoreList(NodeList bookstoreNodeList){
        List<Bookstore> bookstoreList = new ArrayList<Bookstore>();
        for(int i = 0; i < bookstoreNodeList.getLength(); i++){
            bookstoreList.add(bookstoreLoad(bookstoreNodeList, i));
        }
        return bookstoreList;
    }
}

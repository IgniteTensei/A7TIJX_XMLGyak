package hu.domparse.a7tijx.elements;

import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Selling {
    //Attribútumok
    public int sBookID;
    public int sBookstoreID;

    //Elemek
    public int numOfSells;

    //Selling példány feltöltésére szolgál (segédfüggvény)
    public static Selling sellingLoad(NodeList sellingNodes, int item){
        Selling selling = new Selling();

        //Feltölti az elemeket
        for(int i = 0; i < sellingNodes.item(item).getChildNodes().getLength(); i++){
            String nodeName = sellingNodes.item(item).getChildNodes().item(i).getNodeName();
            String nodeContent = sellingNodes.item(item).getChildNodes().item(i).getTextContent();

            if(nodeName.equals("NumOfSells")){selling.numOfSells = Integer.parseInt(nodeContent);}
        }

        //Feltölti az attribútumokat
        for(int i = 0; i < sellingNodes.item(item).getAttributes().getLength(); i++){
            String nodeAttributeName = sellingNodes.item(item).getAttributes().item(i).getNodeName();
            String nodeAttributeContent = sellingNodes.item(item).getAttributes().item(i).getTextContent();

            if(nodeAttributeName.equals("SBookID")){selling.sBookID = Integer.parseInt(nodeAttributeContent);}
            else if(nodeAttributeName.equals("SBookstoreID")){selling.sBookstoreID = Integer.parseInt(nodeAttributeContent);}
        }

        return selling;
    }

    //Készít egy Selling osztály alapú listát az XML dokumentumból kapott Selling Node listájából
    public static List<Selling> makeSellingList(NodeList sellingNodeList){
        List<Selling> sellingList = new ArrayList<Selling>();
        for(int i = 0; i < sellingNodeList.getLength(); i++){
            sellingList.add(sellingLoad(sellingNodeList, i));
        }
        return sellingList;
    }
}

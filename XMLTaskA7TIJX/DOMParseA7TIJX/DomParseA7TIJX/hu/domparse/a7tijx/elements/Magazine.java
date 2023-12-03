package hu.domparse.a7tijx.elements;

import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Magazine {
    //Attribútumok
    public int magazineID;
    public int mPublisher;

    //Elemek
    public String magazineName;
    public String publishingFrequency;
    public String demographic;
    public String magazinePrice;


    //Magazine példány feltöltésére szolgál (segédfüggvény)
    public static Magazine magazineLoad(NodeList magazineNodes, int item){
        Magazine magazine = new Magazine();

        //Feltölti az elemeket
        for(int i = 0; i < magazineNodes.item(item).getChildNodes().getLength(); i++){
            String nodeName = magazineNodes.item(item).getChildNodes().item(i).getNodeName();
            String nodeContent = magazineNodes.item(item).getChildNodes().item(i).getTextContent();
            if(nodeName.equals("MagazineName")){magazine.magazineName = nodeContent;}
            else if(nodeName.equals("PublishingFrequency")){magazine.publishingFrequency = nodeContent;}
            else if(nodeName.equals("Demographic")){magazine.demographic = nodeContent;}
            else if(nodeName.equals("MagazinePrice")){magazine.magazinePrice = nodeContent;}
        }

        //Feltölti az attribútumokat
        for(int i = 0; i < magazineNodes.item(item).getAttributes().getLength(); i++){
            String nodeAttributeName = magazineNodes.item(item).getAttributes().item(i).getNodeName();
            String nodeAttributeContent = magazineNodes.item(item).getAttributes().item(i).getTextContent();

            if(nodeAttributeName.equals("MagazineID")){magazine.magazineID = Integer.parseInt(nodeAttributeContent);}
            else if(nodeAttributeName.equals("MPublisher")){magazine.mPublisher = Integer.parseInt(nodeAttributeContent);}
        }

        return magazine;
    }

    //Készít egy Magazine osztály alapú listát az XML dokumentumból kapott Magazine Node listájából
    public static List<Magazine> makeMagazineList(NodeList magazineNodeList){
        List<Magazine> magazineList = new ArrayList<Magazine>();
        for(int i = 0; i < magazineNodeList.getLength(); i++){
            magazineList.add(magazineLoad(magazineNodeList, i));
        }
        return magazineList;
    }
}

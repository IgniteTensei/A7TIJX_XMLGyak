package hu.domparse.a7tijx.elements;

import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class President {
    //Attribútumok
    public int presidentID;

    //Elemek
    public String presidentName;
    public String presidentGender;
    public int presidentAge;

    //President példány feltöltésére szolgál (segédfüggvény)
    public static President presidentLoad(NodeList presidentNodes, int item){
        President president = new President();

        //Feltölti az elemeket
        for(int i = 0; i < presidentNodes.item(item).getChildNodes().getLength(); i++){
            String nodeName = presidentNodes.item(item).getChildNodes().item(i).getNodeName();
            String nodeContent = presidentNodes.item(item).getChildNodes().item(i).getTextContent();
            if(nodeName.equals("PresidentName")){president.presidentName = nodeContent;}
            else if(nodeName.equals("PresidentGender")){president.presidentGender = nodeContent;}
            else if(nodeName.equals("PresidentAge")){president.presidentAge = Integer.parseInt(nodeContent);}
        }

        //Feltölti az attribútumokat
        for(int i = 0; i < presidentNodes.item(item).getAttributes().getLength(); i++){
            String nodeAttributeName = presidentNodes.item(item).getAttributes().item(i).getNodeName();
            String nodeAttributeContent = presidentNodes.item(item).getAttributes().item(i).getTextContent();

            if(nodeAttributeName.equals("PresidentID")){president.presidentID = Integer.parseInt(nodeAttributeContent);}
        }

        return president;
    }

    //Készít egy President osztály alapú listát az XML dokumentumból kapott President Node listájából
    public static List<President> makePresidentList(NodeList presidentNodeList){
        List<President> presidentList = new ArrayList<President>();
        for(int i = 0; i < presidentNodeList.getLength(); i++){
            presidentList.add(presidentLoad(presidentNodeList, i));
        }
        return presidentList;
    }
}

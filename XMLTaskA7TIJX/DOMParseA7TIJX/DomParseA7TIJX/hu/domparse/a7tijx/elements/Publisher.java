package hu.domparse.a7tijx.elements;

import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    //Attribútumok
    public int publisherID;
    public int pPresident;

    //Elemek
    public String publisherName;
    public String phoneNumber;
    public String emailAddress;
    public String pCity;
    public String pStreet;
    public String pHouseNumber;

    //Publisher példány feltöltésére szolgál (segédfüggvény)
    public static Publisher publisherLoad(NodeList publisherNodes, int item){
        Publisher publisher = new Publisher();

        //Feltölti az elemeket
        for(int i = 0; i < publisherNodes.item(item).getChildNodes().getLength(); i++){
            String nodeName = publisherNodes.item(item).getChildNodes().item(i).getNodeName();
            String nodeContent = publisherNodes.item(item).getChildNodes().item(i).getTextContent();
            if(nodeName.equals("PublisherName")){publisher.publisherName = nodeContent;}
            else if(nodeName.equals("PhoneNumber")){publisher.phoneNumber = nodeContent;}
            else if(nodeName.equals("EmailAddress")){publisher.emailAddress = nodeContent;}
            else if(nodeName.equals("HQAddress")){
                for(int j = 0; j < publisherNodes.item(item).getChildNodes().item(i).getChildNodes().getLength(); j++){
                    String addressName = publisherNodes.item(item).getChildNodes().item(i).getChildNodes().item(j).getNodeName();
                    String addressContent = publisherNodes.item(item).getChildNodes().item(i).getChildNodes().item(j).getTextContent();
                    if(addressName.equals("PCity")){publisher.pCity = addressContent;}
                    else if(addressName.equals("PStreet")){publisher.pStreet = addressContent;}
                    else if(addressName.equals("PHouseNumber")){publisher.pHouseNumber = addressContent;}
                }
            }
        }

        //Feltölti az attribútumokat
        for(int i = 0; i < publisherNodes.item(item).getAttributes().getLength(); i++){
            String nodeAttributeName = publisherNodes.item(item).getAttributes().item(i).getNodeName();
            String nodeAttributeContent = publisherNodes.item(item).getAttributes().item(i).getTextContent();

            if(nodeAttributeName.equals("PublisherID")){publisher.publisherID = Integer.parseInt(nodeAttributeContent);}
            else if(nodeAttributeName.equals("PPresident")){publisher.pPresident = Integer.parseInt(nodeAttributeContent);}
        }

        return publisher;
    }

    //Készít egy Publisher osztály alapú listát az XML dokumentumból kapott Publisher Node listájából
    public static List<Publisher> makePublisherList(NodeList publisherNodeList){
        List<Publisher> publisherList = new ArrayList<Publisher>();
        for(int i = 0; i < publisherNodeList.getLength(); i++){
            publisherList.add(publisherLoad(publisherNodeList, i));
        }
        return publisherList;
    }
}

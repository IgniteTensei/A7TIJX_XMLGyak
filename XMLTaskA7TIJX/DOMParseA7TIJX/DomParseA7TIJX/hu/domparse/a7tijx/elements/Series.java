package hu.domparse.a7tijx.elements;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Series{
    //Attribútumok
    public int seriesID;
    public int sMagazine;
    public int sAuthor;

    //Elemek
    public String seriesName;
    public String ranking;
    public int numOfReaders;
    public List<String> subGenre;

    //Series példány feltöltésére szolgál (segédfüggvény)
    public static Series seriesLoad(NodeList seriesNodes, int item){
        Series series = new Series();

        //Feltölti az elemeket
        for(int i = 0; i < seriesNodes.item(item).getChildNodes().getLength(); i++){
            String nodeName = seriesNodes.item(item).getChildNodes().item(i).getNodeName();
            String nodeContent = seriesNodes.item(item).getChildNodes().item(i).getTextContent();
            if(nodeName.equals("SeriesName")){series.seriesName = nodeContent;}
            else if(nodeName.equals("Ranking")){series.ranking = nodeContent;}
            else if(nodeName.equals("NumOfReaders")){series.numOfReaders = Integer.parseInt(nodeContent);}
            else if(nodeName.equals("Genre")){
                List<String> subgenreList = new ArrayList<String>();
                for (int j = 0; j < seriesNodes.item(item).getChildNodes().item(i).getChildNodes().getLength(); j++ ) {
                    if(seriesNodes.item(item).getChildNodes().item(i).getChildNodes().item(j).getNodeType() == Node.ELEMENT_NODE){
                        subgenreList.add(seriesNodes.item(item).getChildNodes().item(i).getChildNodes().item(j).getTextContent());
                    }

                }
                series.subGenre = subgenreList;
            }
        }

        //Feltölti az attribútumokat
        for(int i = 0; i < seriesNodes.item(item).getAttributes().getLength(); i++){
            String nodeAttributeName = seriesNodes.item(item).getAttributes().item(i).getNodeName();
            String nodeAttributeContent = seriesNodes.item(item).getAttributes().item(i).getTextContent();

            if(nodeAttributeName.equals("SeriesID")){series.seriesID = Integer.parseInt(nodeAttributeContent);}
            else if(nodeAttributeName.equals("SMagazine")){series.sMagazine = Integer.parseInt(nodeAttributeContent);}
            else if(nodeAttributeName.equals("SAuthor")){series.sAuthor = Integer.parseInt(nodeAttributeContent);}
        }

        return series;
    }

    //Készít egy Series osztály alapú listát az XML dokumentumból kapott Series Node listájából
    public static List<Series> makeSeriesList(NodeList seriesNodeList){
        List<Series> seriesList = new ArrayList<Series>();
        for(int i = 0; i < seriesNodeList.getLength(); i++){
            seriesList.add(seriesLoad(seriesNodeList, i));
        }
        return seriesList;
    }
}


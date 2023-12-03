package hu.domparse.a7tijx.elements;

import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Author {
    //Attribútumok
    public int authorID;

    //Elemek
    public String authorName;
    public String authorGender;
    public int authorAge;

    //Author példány feltöltésére szolgál (segédfüggvény)
    public static Author authorLoad(NodeList authorNodes, int item){
        Author author = new Author();

        //Feltölti az elemeket
        for(int i = 0; i < authorNodes.item(item).getChildNodes().getLength(); i++){
            String nodeName = authorNodes.item(item).getChildNodes().item(i).getNodeName();
            String nodeContent = authorNodes.item(item).getChildNodes().item(i).getTextContent();
            if(nodeName.equals("AuthorName")){author.authorName = nodeContent;}
            else if(nodeName.equals("AuthorGender")){author.authorGender = nodeContent;}
            else if(nodeName.equals("AuthorAge")){author.authorAge = Integer.parseInt(nodeContent);}
        }

        //Feltölti az attribútumokat
        for(int i = 0; i < authorNodes.item(item).getAttributes().getLength(); i++){
            String nodeAttributeName = authorNodes.item(item).getAttributes().item(i).getNodeName();
            String nodeAttributeContent = authorNodes.item(item).getAttributes().item(i).getTextContent();

            if(nodeAttributeName.equals("AuthorID")){author.authorID = Integer.parseInt(nodeAttributeContent);}
        }

        return author;
    }

    //Készít egy Author osztály alapú listát az XML dokumentumból kapott Author Node listájából
    public static List<Author> makeAuthorList(NodeList authorNodeList){
        List<Author> authorList = new ArrayList<Author>();
        for(int i = 0; i < authorNodeList.getLength(); i++){
            authorList.add(authorLoad(authorNodeList, i));
        }
        return authorList;
    }
}

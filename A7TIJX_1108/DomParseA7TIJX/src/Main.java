import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class Main {
    public static void main(String[] args) {
        List lista = new ArrayList();

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(
                    "C:\\Users\\IgniteTensei\\Desktop\\Egyetem\\9\\XML\\A7TIJX_XMLGyak\\A7TIJX_0927\\a7tijx_orarend.xml"
            );
            
            NodeList orak = doc.getElementsByTagName("ora");

            for (int i = 0; i < orak.getLength(); i++){
                NodeList orakGyerek = orak.item(i).getChildNodes();
                NamedNodeMap orakAttr = orak.item(i).getAttributes();


            }
        }
        catch (Exception ee) {
            ee.printStackTrace();
        }
    }
}
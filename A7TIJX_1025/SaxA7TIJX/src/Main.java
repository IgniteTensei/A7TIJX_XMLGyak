import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
public class Main {
    public static void main(String[] args) {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

            SAXParser saxParser = saxParserFactory.newSAXParser();

            SaxHandler saxHandler = new SaxHandler();

            saxParser.parse(new File("A7TIJX_kurzusfelvetel.xml"), saxHandler);

        } catch (ParserConfigurationException | SAXException  | IOException e) {
            e.printStackTrace();
        }
    }

    private static class SaxHandler extends DefaultHandler {
        boolean elementStat = false;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes){

            System.out.print("//start// " + qName);

            checkNull(attributes,"id");
            checkNull(attributes,"nyelv");
            checkNull(attributes,"tanev");
            checkNull(attributes,"evfolyam");
            checkNull(attributes,"egyetem");

            System.out.print("\n");


            elementStat = true;
        }

        @Override
        public void endElement(String uri, String localName, String qName){
            System.out.println("//end// " + qName);
            elementStat = false;
        }

        @Override
        public void characters(char[] character, int start, int length){
            if (elementStat) {
                String content = new String(character, start, length).trim();
                if (!content.isEmpty()) {
                    System.out.println("//tartalom// " + content);

                }
            }
        }

        public void checkNull(Attributes attributes, String attributeName){
            if(attributes.getValue(attributeName) != null){

                System.out.print(" [" + attributeName +": " + attributes.getValue(attributeName) + "]");
            }
        }
    }

}
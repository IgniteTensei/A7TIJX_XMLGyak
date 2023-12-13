import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class JSONRead {
    public static void main(String[] args) {
        try(FileReader reader = new FileReader("orarendA7TIJX.json")){
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(reader);
            JSONObject rootObj = (JSONObject) jsonObject.get("gyorki_orarend");
            JSONArray oraArr = (JSONArray) rootObj.get("ora");

            for(int i = 0; i< oraArr.size(); i++) {

                JSONObject ora = (JSONObject) oraArr.get(i);
                JSONObject ido = (JSONObject) ora.get("idopont");

                System.out.println("Tárgy: "+ ora.get("targy"));
                System.out.println("Nap: "+ ido.get("nap")+ " Tól: " + ido.get("tol")+ " Ig: " + ido.get("ig"));
                System.out.println("Helyszín: "+ ora.get("helyszin"));
                System.out.println("Oktató: "+ ora.get("oktato"));
                System.out.println("Szak: "+ ora.get("szak"));

            }


            JSONObject oraObj = new JSONObject();
            JSONObject rootObj2 = new JSONObject();
            oraObj.put("ora", oraArr);
            rootObj2.put("gyorki_orarend", oraObj);

            FileWriter file = new FileWriter("orarendA7TIJX1.json");
            String jsonString = rootObj2.toString().replace(",", ",\n\t").replace("{", "{\n\t").replace("[", "[\n\t").replace("]", "\n]").replace("}", "\n}");
            file.write(jsonString);
            file.close();

        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}

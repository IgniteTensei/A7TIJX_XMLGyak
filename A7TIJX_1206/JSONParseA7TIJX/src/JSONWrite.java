import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.parser.JSONParser;

import java.io.FileWriter;

public class JSONWrite {
    public static void main(String[] args) {
        JSONParser parse = new JSONParser();
        JSONObject obj = new JSONObject();
        JSONArray ora = new JSONArray();

        ora.add(newObj("Kitalált I", "Hétfő", "6", "8", "A1 201", "Dr. Doktor Dokktro", "mérnökinformatikus"));
        ora.add(newObj("Fizika II", "Hétfő", "8", "10", "A1 305", "Dr. Pszota Gábor", "mérnökinformatikus"));
        ora.add(newObj("Szoftvertesztelés", "Hétfő", "10", "12", "info 103", "Dr. Hornyák Olivér", "mérnökinformatikus"));
        ora.add(newObj("Szoftvertesztelés", "Hétfő", "12", "14", "info 103", "Dr. Hornyák Olivér", "mérnökinformatikus"));
        ora.add(newObj("Webtechnológiák I", "Hétfő", "14", "16", "A1 305", "Agárdi Anita", "mérnökinformatikus"));
        ora.add(newObj("Webtechnológiák I", "Hétfő", "16", "18", "info 103", "Agárdi Anita", "mérnökinformatikus"));
        ora.add(newObj("Adatkezelés XMLben", "Kedd", "12", "14", "XXXII előadó", "Dr. Bednarik László", "mérnökinformatikus"));
        ora.add(newObj("Webes Alkalmazások", "Kedd", "14", "16", "info 101", "Dr. Árvai László", "mérnökinformatikus"));
        ora.add(newObj("Webes Alkalmazások", "Kedd", "16", "18", "info 101", "Dr. Mileff Péter", "mérnökinformatikus"));
        ora.add(newObj("Adatkezelés XMLben", "Szerda", "10", "12", "info 101", "Dr. Bednarik László", "mérnökinformatikus"));
        ora.add(newObj("Integrált Vállalati Rendszerek", "Szerda", "12", "14", "A1 218", "Dr. Samad Dadvandipour", "mérnökinformatikus"));
        ora.add(newObj("Integrált Vállalati Rendszerek", "Szerda", "14", "16", "info 15", "Kulcsárné Dr. Forrai Mónika", "mérnökinformatikus"));

        JSONObject oraObj = new JSONObject();
        oraObj.put("ora", ora);
        obj.put("gyorki_orarend", oraObj);
        JSONArray oraArr = (JSONArray) oraObj.get("ora");

        for(int i = 0; i< oraArr.size(); i++) {

            JSONObject o = (JSONObject) oraArr.get(i);
            JSONObject ido = (JSONObject) o.get("idopont");

            System.out.println("Tárgy: "+ o.get("targy"));
            System.out.println("Nap: "+ ido.get("nap")+ " Tól: " + ido.get("tol")+ " Ig: " + ido.get("ig"));
            System.out.println("Helyszín: "+ o.get("helyszin"));
            System.out.println("Oktató: "+ o.get("oktato"));
            System.out.println("Szak: "+ o.get("szak"));

        }

        try(FileWriter file = new FileWriter("orarendA7TIJX2.json");){
            String jsonString = obj.toString().replace(",", ",\n\t").replace("{", "{\n\t").replace("[", "[\n\t").replace("]", "\n]").replace("}", "\n}");
            file.write(jsonString);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    private static JSONObject newObj(String targy, String nap, String tol, String ig, String helyszin, String oktato, String szak){
        JSONObject localObject = new JSONObject();

        localObject.put("targy", targy);
        JSONObject idopont = new JSONObject();
        idopont.put("nap", nap);
        idopont.put("tol", tol);
        idopont.put("ig", ig);
        localObject.put("idopont", idopont);
        localObject.put("helyszin", helyszin);
        localObject.put("oktato", oktato);
        localObject.put("szak", szak);

        return localObject;
    }

}

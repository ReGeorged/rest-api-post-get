import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            int responseCode =CheckServerResponse.checkResponse("GET");

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(CheckServerResponse.getUrl().openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                //Close the scanner
                scanner.close();

                System.out.println(informationString);


                //JSON simple library Setup with Maven is used to convert strings to JSON
                JSONParser parse = new JSONParser();
                JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));

                //Get the first JSON object in the JSON array
                //System.out.println("more info: "+dataObject.get(2));
                for(int i=0;i<dataObject.size();i++){
                    System.out.println(dataObject.get(i));
                }

                JSONObject countryData = (JSONObject) dataObject.get(0);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

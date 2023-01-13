import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {
    static Map<String, Object> missed_values;

    public static void main(String[] args) throws IOException, ParseException {
        if (args.length > 1)
            if (!args[0].isEmpty() && !args[1].isEmpty()) {
                Reader file1 = new FileReader(args[0]);
                Reader file2 = new FileReader(args[1]);
                missed_values = new HashMap<>();

                JSONParser parser = new JSONParser();
                JSONObject json_object1 = (JSONObject) parser.parse(file1);
                JSONObject json_object2 = (JSONObject) parser.parse(file2);
                JSONArray json_array1 = (JSONArray) json_object1.get("tests");
                JSONArray json_array2 = (JSONArray) json_object2.get("values");

                checkJson(json_array2, 1);
                checkJson(json_array1, 0);

                FileWriter file = new FileWriter("output.json");
                file.write(json_object1.toJSONString());
                file.close();

                System.out.println("json file was appended");
            } else
                System.out.print("Uncorrected values");
        else
            System.out.print("Use 2 arguments: Paths in form \"C:\\...\"");
    }

    @SuppressWarnings("unchecked")
    public static void checkJson(JSONArray snd_array, int sender) {
        Iterator<?> iterator = snd_array.iterator();
        while(iterator.hasNext()) {
            JSONObject jsn_obj = (JSONObject) iterator.next();
            String id = null;

            for (Object key : jsn_obj.keySet()) {
                if (key.equals("id"))
                    id = jsn_obj.get(key).toString();
                else if (sender == 1)
                    missed_values.put(id, jsn_obj.get(key));
                else if (key.equals("value")) {
                    jsn_obj.put(key.toString(), missed_values.get(id));
                } else if (key.equals("values"))
                    checkJson((JSONArray) jsn_obj.get("values"),0);
            }
        }
    }
}
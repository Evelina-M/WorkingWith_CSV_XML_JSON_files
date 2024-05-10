import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSON {
    private static final String fileName = "new_data.json";

    public static void main(String[] args) throws ParseException {
        String json = readString(fileName);
        List<Employee> list = jsonToList(json);
        for (Employee employee : list) {
            System.out.println(employee.toString());
        }
    }

    private static String readString(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            System.out.println(e);
            // return null;
        }
        return sb.toString();
    }

    private static List<Employee> jsonToList(String json) throws ParseException {
        List<Employee> employees = new ArrayList<>();

        try {
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(json);

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            //Gson gson = new GsonBuilder().create();

            for (Object obj : jsonArray) {
                //JSONObject jsonObject = (JSONObject) obj;
                Employee employee = gson.fromJson(obj.toString(), Employee.class);
                employees.add(employee);
            }
        } catch (ParseException e) {
            System.out.println(e);
        }

        return employees;
    }
}

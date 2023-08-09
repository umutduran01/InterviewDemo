package Utils;

import org.json.JSONObject;

import java.util.Map;
import java.util.Set;

public class APIPayloadConstants {
    //we will pass the body in multiple formats, for this we have created this class
    //we can use json for java to not deal with these complicated body
    public static String createEmployeePayload() {
        String createEmployeePayload = "{\n" +
                "    \"emp_firstname\": \"Mark\",\n" +
                "    \"emp_lastname\": \"Zuckerberg\",\n" +
                "    \"emp_middle_name\": \"de\",\n" +
                "    \"emp_gender\": \"M\",\n" +
                "    \"emp_birthday\": \"1999-09-09\",\n" +
                "    \"emp_status\": \"Pending\",\n" +
                "    \"emp_job_title\": \"Engineer\"\n" +
                "}";
        return createEmployeePayload;
    }

    public static String createEmployeePayloadJson() {

        //This JSON object allows us to write in JSON format as we pass Map values. When we return it, it should be .toString().
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", "Elon");
        obj.put("emp_lastname", "Musk");
        obj.put("emp_middle_name", "von");
        obj.put("emp_gender", "M");
        obj.put("emp_birthday", "2000-10-10");
        obj.put("emp_status", "Confirmed");
        obj.put("emp_job_title", "Engineer");

        return obj.toString();
    }

    public static String createEmployeePayloadDynamic
            (String emp_firstname, String emp_lastname,
             String emp_middle_name, String emp_gender, String emp_birthday,
             String emp_status, String emp_job_title) {

        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", emp_firstname);
        obj.put("emp_lastname", emp_lastname);
        obj.put("emp_middle_name", emp_middle_name);
        obj.put("emp_gender", emp_gender);
        obj.put("emp_birthday", emp_birthday);
        obj.put("emp_status", emp_status);
        obj.put("emp_job_title", emp_job_title);

        return obj.toString();
    }

    public static String updateEmployeePayloadJson() {

        JSONObject obj = new JSONObject();
        obj.put("employee_id", "57908A");
        obj.put("emp_firstname", "Billy");
        obj.put("emp_lastname", "Gates");
        obj.put("emp_middle_name", "von");
        obj.put("emp_gender", "M");
        obj.put("emp_birthday", "2000-02-22");
        obj.put("emp_status", "Inactive");
        obj.put("emp_job_title", "CEO");

        return obj.toString();
    }

    public static String createEmployeeFromDatatable(Map<String, String> map) {

        Set<String> keys = map.keySet();

        JSONObject obj = new JSONObject();

        for (String key : keys) {
            String value = map.get(key);
            obj.put(key, value);
        }


        return obj.toString();
    }
}

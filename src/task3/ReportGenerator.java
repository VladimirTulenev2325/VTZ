package task3;

import com.google.gson.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReportGenerator {
    public static void main(String[] args) {

        String testsFile = "tests.json";
        String valuesFile = "values.json";
        String reportFile = "report.json";

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            // Read tests.json file
            JsonObject testsObject;
            try (FileReader reader = new FileReader(testsFile)) {
                testsObject = gson.fromJson(reader, JsonObject.class);
            }

            // Read values.json file
            JsonObject valuesObject;
            try (FileReader reader = new FileReader(valuesFile)) {
                valuesObject = gson.fromJson(reader, JsonObject.class);
            }

            // Update test values
            if (testsObject != null && valuesObject != null) {
                updateTestValues(testsObject, valuesObject);
            }

            // Write value.json file
            JsonObject reportObject = getReportObject(testsObject);
            try (FileWriter writer = new FileWriter(reportFile)) {
                gson.toJson(reportObject, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void updateTestValues(JsonObject testsObject, JsonObject valuesObject) {
        if (testsObject.has("tests")) {
            JsonArray testsArray = testsObject.getAsJsonArray("tests");
            for (JsonElement testElement : testsArray) {
                if (testElement.isJsonObject()) {
                    JsonObject testObject = testElement.getAsJsonObject();
                    updateTestValue(testObject, valuesObject);
                    if (testObject.has("values")) {
                        JsonArray valuesArray = testObject.getAsJsonArray("values");
                        for (JsonElement valueElement : valuesArray) {
                            if (valueElement.isJsonObject()) {
                                JsonObject valueObject = valueElement.getAsJsonObject();
                                updateTestValue(valueObject, valuesObject);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void updateTestValue(JsonObject testObject, JsonObject valuesObject) {
        if (testObject.has("id") && testObject.has("value")) {
            String id = testObject.get("id").getAsString();
            if (valuesObject.has(id) && valuesObject.get(id).isJsonObject()) {
                JsonObject valueObject = valuesObject.getAsJsonObject(id);
                if (valueObject.has("value")) {
                    String value = valueObject.get("value").getAsString();
                    testObject.addProperty("value", value);
                }
            }
        }
    }

    private static JsonObject getReportObject(JsonObject testsObject) {
        if (testsObject.has("tests")) {
            JsonArray testsArray = testsObject.getAsJsonArray("tests");
            for (JsonElement testElement : testsArray) {
                if (testElement.isJsonObject()) {
                    JsonObject testObject = testElement.getAsJsonObject();
                    if (testObject.has("id") && testObject.has("value")) {
                        String id = testObject.get("id").getAsString();
                        if (id.equals("122")) {
                            return testObject;
                        }
                    }
                }
            }
        }
        return null;
    }
}
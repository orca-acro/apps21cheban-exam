package domain;

import json.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {

    private final List<Tuple<String, Integer>> exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = Arrays.asList(exams);
    }

    @Override
    public JsonObject toJsonObject() {
        ArrayList<JsonObject> statements = new ArrayList<>();
        for (Tuple<String, Integer> pairs : this.exams) {
            boolean pass = pairs.value >= 3;
            JsonPair passPair = new JsonPair("passed", new JsonBoolean(pass));
            ;
            JsonObject tempObject = new JsonObject(new JsonPair("course", new JsonString(pairs.key)),
                    new JsonPair("mark", new JsonNumber(pairs.value)), passPair);
            System.out.println(tempObject.toJson());
            statements.add(tempObject);
        }
        JsonObject[] jsonList = new JsonObject[exams.size()];
        for (int i = 0; i < statements.size(); i++) {
            jsonList[i] = statements.get(i);
        }
        JsonArray arr = new JsonArray(jsonList);
        JsonObject jsonObject = super.toJsonObject();
        jsonObject.add(new JsonPair("exams", arr));
        return jsonObject;
    }
}

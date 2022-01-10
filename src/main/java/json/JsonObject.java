package json;


import java.util.HashMap;
import java.util.LinkedHashMap;


/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private HashMap<String, Json> jsonObject;

    public JsonObject(JsonPair... jsonPairs) {
        jsonObject = new LinkedHashMap<>();
        for (Tuple pairs : jsonPairs) {
            Json pairVal = (Json) pairs.value;
            jsonObject.put(pairs.key.toString(), pairVal);
        }
    }

    @Override
    public String toJson() {
        Object[] keys = jsonObject.keySet().toArray();
        String msg = "";
        if (jsonObject.isEmpty() == true) {
            msg = "{}";
        } else {
            msg += "{";
            for (int i = 0; i < keys.length; i++) {
                msg += "'" + keys[i] + "'";
                msg += ": ";
                msg += jsonObject.get(keys[i]).toJson();
                if (i < keys.length - 1) {
                    msg += ", ";
                }
            }
            msg += "}";
        }
        return msg;
    }

    public void add(JsonPair jsonPair) {
        jsonObject.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        if (jsonObject.containsKey(name)) {
            Json resFind = (Json) jsonObject.get(name);
            return resFind;
        } else {
            return null;
        }
    }

    public JsonObject projection(String... names) {
        JsonObject newJsonObject = new JsonObject();
        for (int i = 0; i < names.length; i++) {
            if (jsonObject.containsKey(names[i])) {
                JsonPair pair = new JsonPair(names[i], jsonObject.get(names[i]));
                newJsonObject.add(pair);
            }
        }
        return newJsonObject;
    }
}

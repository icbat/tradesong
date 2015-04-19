package icbat.games.tradesong.engine;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

/***/
public class JsonTest {
    @Test
    public void name() throws Exception {
        JSONArray json = new JSONArray("[\n" +
                "  {\n" +
                "    name: \"an Item\",\n" +
                "    basePrice: 300,\n" +
                "  },{\n" +
                "    name: \"a better item\",\n" +
                "    basePrice: 1000,\n" +
                "  },{\n" +
                "    name: \"Assembled thing\",\n" +
                "    basePrice: 1500,\n" +
                "  },\n" +
                "]");

        System.out.println(json.length());
        for (int i=0; i<json.length(); ++i) {
            final JSONObject item = json.getJSONObject(i);
            item.getString("name");
            item.getInt("basePrice");
            System.out.println(item);
            System.out.println(item.toString());
        }
    }
}

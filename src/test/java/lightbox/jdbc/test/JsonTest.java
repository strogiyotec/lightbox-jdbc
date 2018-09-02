package lightbox.jdbc.test;

import org.junit.Assert;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.util.HashMap;
import java.util.Map;

public final class JsonTest extends Assert {

    @Test
    public void test() {
        Map<String, Object> map = new HashMap<>();
        map.put("ss", Json.createObjectBuilder().add("ds",1).build());
        final JsonObjectBuilder objectBuilder = Json.createObjectBuilder(map);
    }
}

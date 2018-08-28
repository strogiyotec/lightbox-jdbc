package lightbox.jdbc.types;

import com.github.strogiyotec.lightbox.jdbc.types.JsonType;
import org.junit.Assert;
import org.junit.Test;

import javax.json.JsonObject;
import javax.json.stream.JsonParsingException;

import static org.hamcrest.CoreMatchers.is;

public final class JsonTypeTest extends Assert {

    @Test
    public void shouldConvertoJson() throws Exception {
        final JsonObject apply = new JsonType().apply("{\n" +
                "  \"name\" : \"Almas\"\n" +
                "}");
        assertThat(apply.getString("name"), is("Almas"));
    }

    @Test(expected = JsonParsingException.class)
    public void shouldFailed() throws Exception {
        final JsonObject apply = new JsonType().apply("Hello world");
        assertThat(apply.getString("name"), is("Almas"));
    }
}

package lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Result;
import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.query.SimpleQuery;
import com.github.strogiyotec.lightbox.jdbc.session.DriverSession;
import com.github.strogiyotec.lightbox.jdbc.stmnt.JoinAsJsonArray;
import com.github.strogiyotec.lightbox.jdbc.stmnt.ResultAsCustomType;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Select;
import com.github.strogiyotec.lightbox.jdbc.types.IntArrayType;
import com.github.strogiyotec.lightbox.jdbc.types.JsonType;
import com.github.strogiyotec.lightbox.jdbc.value.data.IntValue;
import com.github.strogiyotec.lightbox.jdbc.value.join.JoinedTable;
import com.github.strogiyotec.lightbox.jdbc.value.join.TablesAsJsonArray;
import org.junit.Assert;
import org.junit.Test;

import javax.json.JsonArray;
import javax.json.JsonObject;

import static org.hamcrest.CoreMatchers.is;

public final class ResultAsCustomTypeTest extends Assert {

    @Test
    public void fetchJsonB() throws Exception {
        final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final ResultAsCustomType<JsonObject> select = new ResultAsCustomType<>(
                new Select(
                        postgres,
                        new SimpleQuery(
                                "select info from child where par_id = :id",
                                new IntValue("id", 228)
                        )
                ), new JsonType()
        );
        final JsonObject jsonObject = select.result().get();
        assertThat(jsonObject.getString("name"), is("Almas"));
    }

    @Test
    public void fetchIntArray() throws Exception {
        final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final ResultAsCustomType<int[]> select = new ResultAsCustomType<>(
                new Select(
                        postgres,
                        new SimpleQuery(
                                "select images from child where id = :id",
                                new IntValue("id", 60)
                        )
                ), new IntArrayType()
        );

        assertTrue(select.result().get().length > 0);
    }

    @Test
    public void test() throws Exception {
        final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final Result<JsonArray> result = new JoinAsJsonArray(
                postgres,
                new SimpleQuery("select m.* , mi.* from movie m inner join movie_info mi on m.id = mi.movie_id"),
                new TablesAsJsonArray(new JoinedTable("movie_info"))
        ).result();
        final JsonArray jsonValues = result.get();
        jsonValues.forEach(System.out::println);
    }
}

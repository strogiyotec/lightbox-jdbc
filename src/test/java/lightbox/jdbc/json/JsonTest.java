package lightbox.jdbc.json;

import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.query.SimpleQuery;
import com.github.strogiyotec.lightbox.jdbc.rows.JsonValuesOf;
import com.github.strogiyotec.lightbox.jdbc.script.SqlScript;
import com.github.strogiyotec.lightbox.jdbc.session.DriverSession;
import com.github.strogiyotec.lightbox.jdbc.stmnt.ResultAsCustomType;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Select;
import com.github.strogiyotec.lightbox.jdbc.types.JsonType;
import com.github.strogiyotec.lightbox.jdbc.value.data.IntValue;
import lightbox.jdbc.fake.FakeDatabaseSession;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.json.JsonArray;
import javax.json.JsonObject;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public final class JsonTest {

    private static final Session session = new FakeDatabaseSession();

    @BeforeClass
    public static void initDb() throws Exception {
        new SqlScript(
                session,
                String.join(
                        " ",
                        "CREATE table testJson ( ",
                        "id serial PRIMARY KEY, ",
                        "data jsonb not null",
                        ")"
                ),
                String.join(
                        " ",
                        "INSERT INTO testJson ",
                        "(data) ",
                        "VALUES ",
                        "('{\"name\":\"Almas\"}')"
                )
        ).execute();
    }

    @Test
    public void testJsonValue() throws Exception {
        final ResultAsCustomType<JsonObject> json = new ResultAsCustomType<>(
                new Select(
                        session,
                        new SimpleQuery("select data from testJson where id = :id", new IntValue("id", 1))
                ),
                new JsonType()
        );
        assertThat(json.result().get().getString("name"), is("Almas"));
    }

    @Test
    public void testJsonValues() throws Exception {
        final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final JsonArray jsonValuesOf = new JsonValuesOf(
                new Select(
                        postgres,
                        new SimpleQuery("select m.* , mi.* from movie m inner join movie_info mi on m.id = mi.movie_id")
                ).result().get()
        );
        System.out.println(jsonValuesOf);
    }

    @AfterClass
    public static void removeDb() throws Exception {
        new SqlScript(
                session,
                "drop table testJson"
        ).execute();
    }
}

package lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.query.SimpleQuery;
import com.github.strogiyotec.lightbox.jdbc.script.SqlScript;
import com.github.strogiyotec.lightbox.jdbc.session.DriverSession;
import com.github.strogiyotec.lightbox.jdbc.stmnt.ResultAsCustomType;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Select;
import com.github.strogiyotec.lightbox.jdbc.types.IntArrayType;
import com.github.strogiyotec.lightbox.jdbc.types.JsonType;
import com.github.strogiyotec.lightbox.jdbc.value.data.IntValue;
import lightbox.jdbc.fake.FakeDatabaseSession;
import org.junit.*;

import javax.json.JsonObject;

import static org.hamcrest.CoreMatchers.is;

@Ignore
public final class ResultAsCustomTypeTest extends Assert {

    private static final Session postgres = new FakeDatabaseSession();

    @BeforeClass
    public static void initTable() throws Exception {
        new SqlScript(
                postgres,
                String.join(
                        " ",
                        "CREATE TABLE testJsonB (",
                        "id serial primary key ,",
                        "name jsonb not null )"
                ),
                String.join(
                        " ",
                        "INSERT INTO testJsonB ",
                        "(name) ",
                        "VALUES ",
                        "('{\"name\":\"Almas\"}')"
                )
        ).execute();
    }

    @AfterClass
    public static void deleteTable() throws Exception {
        new SqlScript(
                postgres,
                "DROP TABLE testJsonB"
        ).execute();
    }
    
    @Test
    public void fetchJsonB() throws Exception {
       final ResultAsCustomType<JsonObject> select = new ResultAsCustomType<>(
                new Select(
                        postgres,
                        new SimpleQuery(
                                "select name from testJsonB where id = :id",
                                new IntValue("id", 1)
                        )
                ), new JsonType()
        );
        final JsonObject jsonObject = select.result().call();
        assertThat(jsonObject.getString("name"), is("Almas"));
    }
}

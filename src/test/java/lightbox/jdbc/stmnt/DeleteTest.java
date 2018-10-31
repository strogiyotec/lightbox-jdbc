package lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Result;
import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.query.KeyedQuery;
import com.github.strogiyotec.lightbox.jdbc.query.SimpleQuery;
import com.github.strogiyotec.lightbox.jdbc.rows.JsonValuesOf;
import com.github.strogiyotec.lightbox.jdbc.script.SqlScript;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Delete;
import com.github.strogiyotec.lightbox.jdbc.stmnt.KeyedUpdate;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Update;
import com.github.strogiyotec.lightbox.jdbc.value.data.StringValue;
import lightbox.jdbc.fake.FakeDatabaseSession;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public final class DeleteTest extends Assert {

    private static final Session postgres = new FakeDatabaseSession();

    @BeforeClass
    public static void initTable() throws Exception {
        new SqlScript(
                postgres,
                String.join(
                        " ",
                        "CREATE TABLE testDelete (",
                        "id serial primary key ,",
                        "name text not null )"
                ),
                String.join(
                        " ",
                        "INSERT INTO testDelete ",
                        "(name) ",
                        "VALUES ",
                        "('Almas')"
                ),
                String.join(
                        " ",
                        "INSERT INTO testDelete ",
                        "(name) ",
                        "VALUES ",
                        "('Murat')"
                )
        ).execute();
    }

    @AfterClass
    public static void deleteTable() throws Exception {
        new SqlScript(
                postgres,
                "DROP TABLE testDelete"
        ).execute();
    }

    @Test
    public void deleteSingleRow() throws Exception {
        final Result<Integer> result = new Delete(
                postgres,
                new SimpleQuery(
                        "DELETE from testDelete WHERE name LIKE :name",
                        new StringValue("name", "Almas")
                )
        ).result();
        assertThat(result.call(), is(1));
    }

    @Test
    public void emptyResult() throws Exception {
        final Result<Integer> result = new Update(
                postgres,
                new SimpleQuery(
                        "DELETE from testDelete WHERE name LIKE :name",
                        new StringValue("name", "HELLO")
                )
        ).result();
        assertThat(result.call(), is(0));
    }

    @Test
    public void deleteWithKeys() throws Exception {
        final JsonValuesOf jsonValues = new JsonValuesOf(
                new KeyedUpdate(
                        postgres,
                        new KeyedQuery(
                                "DELETE FROM testDelete WHERE name like :name returning *",
                                new StringValue("name", "Murat")
                        )

                ).result().call()
        );
        assertTrue(!jsonValues.isEmpty());
        assertThat(jsonValues.get(0).asJsonObject().getString("name"), is("Murat"));
    }
}

package lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.query.SimpleQuery;
import com.github.strogiyotec.lightbox.jdbc.rows.JsonValuesOf;
import com.github.strogiyotec.lightbox.jdbc.script.SqlScript;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Join;
import com.github.strogiyotec.lightbox.jdbc.value.join.JoinTables;
import lightbox.jdbc.fake.FakeDatabaseSession;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.json.JsonObject;

import static org.hamcrest.CoreMatchers.is;

public final class JoinTest extends Assert {

    private static final Session postgres = new FakeDatabaseSession();

    @BeforeClass
    public static void initTable() throws Exception {
        new SqlScript(
                postgres,
                String.join(
                        " ",
                        "CREATE TABLE account (",
                        "id serial primary key ,",
                        "name text not null );"
                ),
                String.join(
                        " ",
                        "INSERT INTO account ",
                        "(name) ",
                        "VALUES ",
                        "('Almas');"
                )
        ).execute();

        new SqlScript(
                postgres,
                String.join(
                        " ",
                        "CREATE TABLE orders (",
                        "id serial primary key ,",
                        "name text ,",
                        "price int not null ,",
                        "account_id int references account(id)",
                        ");"
                ),
                String.join(
                        " ",
                        "INSERT INTO orders ",
                        "(price,account_id,name) ",
                        "VALUES ",
                        "(200,1,'Milk');"
                ),
                String.join(
                        " ",
                        "INSERT INTO orders ",
                        "(price,account_id,name) ",
                        "VALUES ",
                        "(300,1,'apples');"
                )
        ).execute();
    }

    @AfterClass
    public static void deleteTable() throws Exception {
        new SqlScript(
                postgres,
                "DROP TABLE orders; ",
                "DROP TABLE account; "
        ).execute();
    }

    @Test
    public void testJoin() throws Exception {
        final JsonValuesOf jsonValues = new JsonValuesOf(
                new Join(
                        postgres,
                        new SimpleQuery(
                                String.join(
                                        " ",
                                        "SELECT u.*,o.* FROM account u INNER JOIN orders o",
                                        "ON u.id = o.account_id"
                                )
                        ), new JoinTables("orders")
                ).result()
        );
        assertThat(jsonValues.size(), is(1));

        final JsonObject account = jsonValues.get(0).asJsonObject();

        assertThat(account.getString("name"), is("Almas"));
        assertThat(account.getJsonArray("orders").size(), is(2));
    }
}

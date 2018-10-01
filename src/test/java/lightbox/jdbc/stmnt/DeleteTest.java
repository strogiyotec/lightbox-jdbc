package lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.query.KeyedQuery;
import com.github.strogiyotec.lightbox.jdbc.query.SimpleQuery;
import com.github.strogiyotec.lightbox.jdbc.script.SqlScript;
import com.github.strogiyotec.lightbox.jdbc.session.DriverSession;
import com.github.strogiyotec.lightbox.jdbc.stmnt.KeyedUpdate;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Update;
import lightbox.jdbc.fake.FakeDatabaseSession;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

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
                )
        ).execute();
    }

    @Test
    public void testDelete() throws Exception {
        final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final Update delete = new Update(
                postgres,
                new SimpleQuery("delete from child where par_id =1 "));
        assertTrue(delete.result().get() != null);
    }

    @Test
    public void testDeleteWithKeys() throws Exception {
        final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final KeyedUpdate keyedUpdate = new KeyedUpdate(
                postgres,
                new KeyedQuery("delete from child where par_id = 2 returning *")

        );
        final Iterator<Map<String, Object>> iterator = keyedUpdate.result().get().iterator();
        boolean hasValues = false;
        while (iterator.hasNext()) {
            iterator.next().forEach((k, v) -> System.out.println(k + " " + v));
            hasValues = true;
        }
        assertTrue(hasValues);

    }
}

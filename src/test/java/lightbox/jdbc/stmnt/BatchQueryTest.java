package lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.query.BatchQuery;
import com.github.strogiyotec.lightbox.jdbc.script.SqlScript;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Batch;
import com.github.strogiyotec.lightbox.jdbc.value.data.QueryParams;
import com.github.strogiyotec.lightbox.jdbc.value.data.StringValue;
import lightbox.jdbc.fake.FakeDatabaseSession;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public final class BatchQueryTest extends Assert {

    private static final Session postgres = new FakeDatabaseSession();

    @BeforeClass
    public static void initTable() throws Exception {
        new SqlScript(
                postgres,
                String.join(
                        " ",
                        "CREATE TABLE testBatch (",
                        "id serial primary key ,",
                        "name text not null )"
                )
        ).execute();
    }

    @AfterClass
    public static void deleteTable() throws Exception {
        new SqlScript(
                postgres,
                "DROP TABLE testBatch"
        ).execute();
    }

    @Test
    public void testBatchQuery() throws Exception {
        final int[] rows = new Batch(
                postgres,
                new BatchQuery(
                        String.join(
                                " ",
                                "INSERT INTO testBatch",
                                "(name)",
                                "VALUES",
                                "(:name)"
                        ),
                        new QueryParams(
                                new StringValue("name", "Igor")
                        ),
                        new QueryParams(
                                new StringValue("name","Almas")
                        )
                )
        ).result().get();
        assertTrue(rows.length == 2);
    }
}

package lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Result;
import com.github.strogiyotec.lightbox.jdbc.Rows;
import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.query.SimpleQuery;
import com.github.strogiyotec.lightbox.jdbc.session.DriverSession;
import com.github.strogiyotec.lightbox.jdbc.stmnt.ResultAsValue;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Select;
import com.github.strogiyotec.lightbox.jdbc.value.IntValue;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import org.junit.Assert;
import org.junit.Test;

public final class ResultAsValueTest extends Assert {

    @Test(expected = IllegalStateException.class)
    public void throwExceptionOnEmptyResultSet() throws Exception {
        final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final ResultAsValue<String> id = new ResultAsValue<>(
                new Select(
                        postgres,
                        new SimpleQuery(
                                "select id from child where id = :id ",
                                new IntValue("id", -321)
                        )
                ), String.class

        );
        final Result<String> result = id.result();
        assertTrue(result.get() != null);
    }

    @Test
    public void insertInSelectStatement() throws Exception {
        final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final Select select =
                new Select(
                    postgres,
                    new SimpleQuery(
                        String.join(
                                " ",
                                "insert into child (par_id)",
                                "values",
                                "(:id)"
                        ),
                        new IntValue("id", -321)
                    )
                );
        final Rows maps = select.result().get();
        assertTrue(Iterables.isEmpty(maps));
    }

    @Test
    public void shouldGetSingleResult() throws Exception {
        final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final ResultAsValue<Integer> id = new ResultAsValue<>(
                new Select(
                        postgres,
                        new SimpleQuery(
                                "select id from child where id = :id ",
                                new IntValue("id", 7)
                        )
                ), Integer.class

        );
        final Result<Integer> result = id.result();
        assertTrue(result.get() != null);
    }

    @Test(expected = ClassCastException.class)
    public void throwExceptionOnIllegalCast() throws Exception {
        final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final ResultAsValue<String> id = new ResultAsValue<>(
                new Select(
                        postgres,
                        new SimpleQuery(
                                "select id from child where id = :id ",
                                new IntValue("id", 7)
                        )
                ), String.class

        );
        final Result<String> result = id.result();
        assertTrue(result.get() != null);
    }
}

package lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Rows;
import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.query.SimpleQuery;
import com.github.strogiyotec.lightbox.jdbc.rows.JsonValuesOf;
import com.github.strogiyotec.lightbox.jdbc.session.DriverSession;
import com.github.strogiyotec.lightbox.jdbc.stmnt.ResultAsValues;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Select;
import com.github.strogiyotec.lightbox.jdbc.stmnt.SelectWithJoin;
import com.github.strogiyotec.lightbox.jdbc.value.data.IntValue;
import com.github.strogiyotec.lightbox.jdbc.value.join.JoinTable;
import com.github.strogiyotec.lightbox.jdbc.value.join.JoinTables;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public final class ResultAsValuesTest extends Assert {

    @Test
    public void testSelectAll() throws Exception {
        final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final ResultAsValues<Integer> id = new ResultAsValues<>(
                new Select(
                        postgres,
                        new SimpleQuery(
                                "select id from child"
                        )
                ),
                Integer.class
        );
        final List<Integer> ids = id.result().get();
        assertTrue(!ids.isEmpty());
    }

    @Test(expected = ClassCastException.class)
    public void throwClassCastException() throws Exception {
        final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final ResultAsValues<String> id = new ResultAsValues<>(
                new Select(
                        postgres,
                        new SimpleQuery(
                                "select id from child"
                        )
                ),
                String.class
        );
        final List<String> ids = id.result().get();
        assertTrue(!ids.isEmpty());
    }

    @Test
    public void returnEmptyList() throws Exception {
        final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final ResultAsValues<Integer> id = new ResultAsValues<>(
                new Select(
                        postgres,
                        new SimpleQuery(
                                "select id from child where id > :id",
                                new IntValue("id",1000)
                        )
                ),
                Integer.class
        );
        final List<Integer> ids = id.result().get();
        assertTrue(ids.isEmpty());
    }

    @Test
    public void joinTest() throws Exception {
        final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final SelectWithJoin movie_info = new SelectWithJoin(
                postgres,
                new SimpleQuery("select m.* , mi.* from movie m inner join movie_info mi on m.id = mi.movie_id"),
                new JoinTables(new JoinTable("movie_info"))
        );
        final Rows maps = movie_info.result().get();
        System.out.println(new JsonValuesOf(maps));
    }
}

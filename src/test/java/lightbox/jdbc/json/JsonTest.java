package lightbox.jdbc.json;

import com.github.strogiyotec.lightbox.jdbc.Rows;
import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.query.SimpleQuery;
import com.github.strogiyotec.lightbox.jdbc.rows.JsonValueOf;
import com.github.strogiyotec.lightbox.jdbc.rows.JsonValuesOf;
import com.github.strogiyotec.lightbox.jdbc.session.DriverSession;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Select;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import javax.json.JsonObject;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class JsonTest extends Assert {


    @Test
    public void testJsonValue() throws Exception {
        final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final Select select = new Select(postgres, new SimpleQuery("select m.* , mi.* from movie m inner join movie_info mi on m.id = mi.movie_id"));
        final JsonObject jsonValueOf = new JsonValueOf(
                new Select(
                        postgres,
                        new SimpleQuery("select m.* , mi.* from movie m inner join movie_info mi on m.id = mi.movie_id")
                ).result().get().iterator().next()
        );
        System.out.println(jsonValueOf);
    }

    @Test
    public void testJsonValues() throws Exception {
        final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final Select select = new Select(postgres, new SimpleQuery("select m.* , mi.* from movie m inner join movie_info mi on m.id = mi.movie_id"));
        final JsonObject jsonValueOf = new JsonValuesOf(
                new Select(
                        postgres,
                        new SimpleQuery("select m.* , mi.* from movie m inner join movie_info mi on m.id = mi.movie_id")
                ).result().get(),"array"
        );
        System.out.println(jsonValueOf);
    }

    @AllArgsConstructor
    static class RowsFromMap implements Rows {

        private final List<Map<String, Object>> rows;

        @Override
        public Iterator<Map<String, Object>> iterator() {
            return this.rows.iterator();
        }
    }
}

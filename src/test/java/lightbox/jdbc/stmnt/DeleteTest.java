package lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.query.KeyedQuery;
import com.github.strogiyotec.lightbox.jdbc.query.SimpleQuery;
import com.github.strogiyotec.lightbox.jdbc.session.DriverSession;
import com.github.strogiyotec.lightbox.jdbc.stmnt.AffectedRowsCount;
import com.github.strogiyotec.lightbox.jdbc.stmnt.GeneratedKeys;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

public final class DeleteTest extends Assert {

    @Test
    public void testDelete() throws Exception {
        final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final AffectedRowsCount delete = new AffectedRowsCount(
                postgres,
                new SimpleQuery("delete from child where par_id =1 "));
        assertTrue(delete.result().get() != null);
    }

    @Test
    public void testDeleteWithKeys() throws Exception {
        final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final GeneratedKeys generatedKeys = new GeneratedKeys(
                postgres,
                new KeyedQuery("delete from child where par_id = 2 returning *")

        );
        final Iterator<Map<String, Object>> iterator = generatedKeys.result().get().iterator();
        boolean hasValues = false;
        while(iterator.hasNext()){
            iterator.next().forEach((k,v)-> System.out.println(k+" "+v));
            hasValues = true;
        }
        assertTrue(hasValues);

    }
}

package lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.Result;
import com.github.strogiyotec.lightbox.jdbc.Session;
import com.github.strogiyotec.lightbox.jdbc.query.SimpleQuery;
import com.github.strogiyotec.lightbox.jdbc.session.DriverSession;
import com.github.strogiyotec.lightbox.jdbc.session.StickySession;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Insert;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Transaction;
import com.github.strogiyotec.lightbox.jdbc.value.IntValue;
import org.junit.Assert;
import org.junit.Test;

public final class TransactionTest extends Assert {

    @Test()
    public void transaction() throws Exception {
        final Session session = new StickySession(
                        new DriverSession(
                                "jdbc:postgresql://127.0.0.1:5432/test",
                                "postgres",
                                "123"
                        )
        );
        final Result<String> id = new Transaction<>(session, () -> {
            final Insert insert = new Insert(
                    new SimpleQuery(
                            "insert into child (par_id) values (:id)",
                            new IntValue("id", 26)
                    ),
                    session
            );
            final Result<Integer> result = insert.result();
            return String.valueOf(result.get());
        }).result();
        assertTrue(id.get()!=null);


    }
}

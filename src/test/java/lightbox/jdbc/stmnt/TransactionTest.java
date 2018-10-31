package lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.query.SimpleQuery;
import com.github.strogiyotec.lightbox.jdbc.session.DriverSession;
import com.github.strogiyotec.lightbox.jdbc.session.TransactedSession;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Transaction;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Update;
import com.github.strogiyotec.lightbox.jdbc.value.data.IntValue;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Ignore
public final class TransactionTest extends Assert {

    @Test()
    public void transaction() throws Exception {
        final TransactedSession session = new TransactedSession(
                new DriverSession(
                        "jdbc:postgresql://127.0.0.1:5432/test",
                        "postgres",
                        "123"
                )
        );
        final Transaction<Integer> transaction = new Transaction<>(
                session,
                () -> {
                    final Update id = new Update(
                            session,
                            new SimpleQuery(
                                    "insert into child (par_id) values (:id)",
                                    new IntValue("id", 212)
                            )
                    );
                    return id.result().call();
                }
        );

        final Integer integer = transaction.result().call();
        assertTrue(integer != null);
    }

    @Test()
    public void transactionNative() throws Exception {
        final TransactedSession session = new TransactedSession(
                new DriverSession(
                        "jdbc:postgresql://127.0.0.1:5432/test",
                        "postgres",
                        "123"
                )
        );
        final Connection connection2 = session.connection();
        connection2.setAutoCommit(false);
        try (final Connection connection = session.connection()) {
            try (final PreparedStatement st = connection.prepareStatement("INSERT INTO child (par_id) VALUES (?)")) {
                st.setInt(1, 146);
                st.execute();
                // connection.commit();
            }
        }
    }
}

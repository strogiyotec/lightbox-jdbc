package lightbox.jdbc.stmnt;

import com.github.strogiyotec.lightbox.jdbc.query.SimpleQuery;
import com.github.strogiyotec.lightbox.jdbc.session.DriverSession;
import com.github.strogiyotec.lightbox.jdbc.session.TransactionSession;
import com.github.strogiyotec.lightbox.jdbc.stmnt.AffectedRowsCount;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Transaction;
import com.github.strogiyotec.lightbox.jdbc.value.IntValue;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

public final class TransactionTest extends Assert {

    @Test()
    public void transaction() throws Exception {
        final TransactionSession session = new TransactionSession(
                new DriverSession(
                        "jdbc:postgresql://127.0.0.1:5432/test",
                        "postgres",
                        "123"
                )
        );
        final Transaction<Integer> transaction = new Transaction<>(
                session,
                () -> {
                    final AffectedRowsCount id = new AffectedRowsCount(
                            session,
                            new SimpleQuery(
                                    "insert into child (par_id) values (:id)",
                                    new IntValue("id", 124)
                            )
                    );
                    return id.result().get();
                }
        );

        final Integer integer = transaction.result().get();
        assertTrue(integer != null);
    }

    @Test()
    public void transactionNative() throws Exception {
        final TransactionSession session = new TransactionSession(
                new DriverSession(
                        "jdbc:postgresql://127.0.0.1:5432/test",
                        "postgres",
                        "123"
                )
        );
        try(final Connection connection = session.connection()){
            connection.setReadOnly();
            try(final PreparedStatement st = connection.prepareStatement("insert into child (par_id) values (?)")){
                st.setInt(1,145);
                st.execute();
                connection.commit();
            }
        }
    }
}

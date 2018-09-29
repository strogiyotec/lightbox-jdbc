package lightbox.jdbc;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;

import java.io.IOException;

public final class FakeDatabase {

    protected final EmbeddedPostgres postgres;

    public FakeDatabase() throws IOException {
        this.postgres = EmbeddedPostgres.start();
    }

}

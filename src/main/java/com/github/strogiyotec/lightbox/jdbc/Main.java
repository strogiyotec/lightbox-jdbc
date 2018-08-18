package com.github.strogiyotec.lightbox.jdbc;

import com.github.strogiyotec.lightbox.jdbc.query.SimpleQuery;
import com.github.strogiyotec.lightbox.jdbc.session.DriverSession;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Select;
import com.github.strogiyotec.lightbox.jdbc.stmnt.SingleSelect;

public final class Main {

    private Main() {
        throw new IllegalStateException("Utility class");
    }

    public static void main(final String[] args) throws Exception {
        final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final Select select = new Select(postgres, new SimpleQuery("select m.* , mi.* from movie m inner join movie_info mi on m.id = mi.movie_id"));
        select.result().get();
    }
}

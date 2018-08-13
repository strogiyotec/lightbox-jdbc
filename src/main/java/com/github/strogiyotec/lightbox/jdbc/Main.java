package com.github.strogiyotec.lightbox.jdbc;

import com.github.strogiyotec.lightbox.jdbc.query.SimpleQuery;
import com.github.strogiyotec.lightbox.jdbc.session.DriverSession;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Select;
import com.github.strogiyotec.lightbox.jdbc.value.IntValue;

public final class Main {

    private Main() {
        throw new IllegalStateException("Utility class");
    }

    public static void main(final String[] args) throws Exception {
        final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final Statement<Rows> select = new Select(postgres, new SimpleQuery("select * from movie where id=:id", new IntValue("id", 1)));
        final Rows maps = select.result().get();
        maps.forEach(map -> map.forEach((k, v) -> System.out.println(k + " " + v)));
    }
}

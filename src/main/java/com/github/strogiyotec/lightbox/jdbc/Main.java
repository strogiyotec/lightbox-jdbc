package com.github.strogiyotec.lightbox.jdbc;

import com.github.strogiyotec.lightbox.jdbc.query.SimpleQuery;
import com.github.strogiyotec.lightbox.jdbc.session.DriverSession;
import com.github.strogiyotec.lightbox.jdbc.stmnt.Select;

public final class Main {

    private Main() {
        throw new IllegalStateException("Utility class");
    }

    public static void main(final String[] args) throws Exception {
        final DriverSession postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final Select select = new Select(postgres, new SimpleQuery("select * from movie "));
        final Rows maps = select.result().get();
        maps.forEach(map-> System.out.println(map.get("id")));
    }
}

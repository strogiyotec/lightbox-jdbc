package com.github.strogiyotec.lightbox.jdbc;

import com.github.strogiyotec.lightbox.jdbc.session.DriverSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public final class Main {

    private Main() {
        throw new IllegalStateException("Utility class");
    }

    public static void main(final String[] args) throws Exception {
        final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        /*final Select select = new Select(postgres, new SimpleQuery("select m.* , mi.* from movie m inner join movie_info mi on m.id = mi.movie_id"));
        select.result().get();*/
        Connection connection = postgres.connection();
        final PreparedStatement statement = connection.prepareStatement(String.join("\n", "insert into people",
                "(login,paasword)",
                "values" +
                        "(?,?)"), Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, "ALmas");
        statement.setString(2, "qwerty");
        statement.execute();
        final ResultSet generatedKeys = statement.getGeneratedKeys();
        while (generatedKeys.next()) {
            System.out.println(generatedKeys.getInt(1));
        }
    }
}

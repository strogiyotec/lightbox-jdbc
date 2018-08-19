# lightbox-jdbc
Here I will try to write OOP library to work with jdbc, no JPA , no ORM , only OOP ;)

How to use:

This is how you do select statement using lightbox-jdbc

```groovy
 final Session postgresSession = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
 final Statement<Rows> select = new Select(postgres, new SimpleQuery("select * from movie where id=:id",new IntValue("id",1)));
 final Rows maps = select.result().get();
 maps.forEach(map -> map.forEach((k, v) -> System.out.println(k + " " + v)));
```

Steps:
1) You create a jdbc session
2) You make a select statement with Params (int pram with name and value)
3) You fetch result from db
4) Rows interface extends Iterable interface and can iterate on Map
.Each map represent a single column from database table

This is how you select single property from DB

```groovy
        final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final int result = new SingleSelect<>(
                                    new Select(
                                            postgres,
                                            new SimpleQuery(
                                                "select id from movie where id = 2")
                                            ),
                                     Integer.class).result().get();
        System.out.println(result);

```

If you want to take result set as Json:

```groovy
final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final JsonObject jsonValueOf = new JsonValueOf(
                new Select(
                        postgres,
                        new SimpleQuery("select m.* , mi.* from movie m inner join movie_info mi on m.id = mi.movie_id where m.id = :id",new IntValue("id",1))
                ).result().get().iterator().next()
        );
        System.out.println(jsonValueOf);

```
Remember that ```JsonValueOf``` takes ```Map<String,Object>```
in constructor and convert it into ```JsonObject```


If you fetch multiple rows from DB and want to convert them into JsonArray use
```JsonValuesOf``` instead of ```JsonValueOf```

```groovy
final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final JsonArray jsonValuesOf = new JsonValuesOf(
                new Select(
                        postgres,
                        new SimpleQuery("select m.* , mi.* from movie m inner join movie_info mi on m.id = mi.movie_id")
                ).result().get()
        );
        System.out.println(jsonValuesOf);
```

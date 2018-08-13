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
4) Rows interface implements Iterable interface and can iterate on Map
.Each map represent a single column from database table


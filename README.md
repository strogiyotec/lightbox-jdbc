# lightbox-jdbc
Here I will try to write OOP library to work with jdbc, no JPA , no ORM , only OOP ;)

Code style:

The project is inspired by the guidelines from the books [Elegant Objects](https://www.yegor256.com/elegant-objects.html), written by Yegor Bugayenko, and the materials from his blog

How to use:

This is how you do select statement using lightbox-jdbc

```groovy
 final Session postgresSession = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
 final Statement<Rows> select = new Select(postgres, new SimpleQuery("select * from movie where id=:id",new IntValue("id",1)));
 final Rows maps = select.result().call();
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
                                     Integer.class).result().call();
        System.out.println(result);

```

If you want to take result set as Json:

```groovy
final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final JsonObject jsonValueOf = new JsonValueOf(
                new Select(
                        postgres,
                        new SimpleQuery("select m.* , mi.* from movie m inner join movie_info mi on m.id = mi.movie_id where m.id = :id",new IntValue("id",1))
                ).result().call().iterator().next()
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
                ).result().call()
        );
        System.out.println(jsonValuesOf);
```

If you need to insert data you can use one of the following ways:
1)

```groovy
final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final AffectedRowsCount insert = new AffectedRowsCount(
                        postgres,
                        new SimpleQuery(
                                String.join("\n",
                                        "insert into people",
                                        "(login,paasword)",
                                        "values",
                                        "(:name,:password)"),
                                new StringValue("name", "Almas"),
                                new StringValue("password", "qwerty"))
                        );
        final Integer res = insert.result().call();
```
In this way you will receive int value which show how many rows were effected by your query or ```0``` if noone

2)
```groovy
final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final GeneratedKeys keyedUpdate = new GeneratedKeys(
                        postgres,
                        new KeyedQuery(
                                String.join("\n",
                                        "insert into people",
                                        "(login,paasword)",
                                        "values",
                                        "(:name,:password)"),
                                new StringValue("name", "Almas"),
                                new StringValue("password", "qwerty")
                        )
                );
        while(iterator.hasNext()){
            iterator.next().forEach((k,v)-> System.out.println(k+" "+v));
        }
```
Here you will receive iterator with inserted keys and auto-generated values like ```Primary key```

You do delete in the same way:


```groovy

final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final GeneratedKeys keyedUpdate = new GeneratedKeys(
                        postgres,
                        new KeyedQuery("delete from child where par_id = 2 returning *")
        
                );
        final Iterator<Map<String, Object>> iterator = deleteWithKeys.result().call().iterator();
        while(iterator.hasNext()){
            iterator.next().forEach((k,v)-> System.out.println(k+" "+v));
        }
```

Sometimes you need to fetch data that not supported by java.lang classes such as jsonB from ```Postgres```

In this case you need:

```groovy
final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final ResultAsCustomType<JsonObject> select = new ResultAsCustomType<>(
                new Select(
                        postgres,
                        new SimpleQuery(
                                "select info from child where par_id = :id",
                                new IntValue("id", 228)
                        )
                ),
                new JsonType()
        );
        final JsonObject jsonObject = select.result().call();
        assertThat(jsonObject.getString("name"),is("Almas"));
```

Here ```JsonType```
 is the mapper provided by library , if you need another type just create a ```Function``` to map object into your type
 
 
If you need to Join two tables you usuaaly use ORM like hibernate and mapping to both classes.
For example user create two classes: User and Payments
In User you add something like this
```groovy
    @OneToMany
    private List<Payments> payments;
```
And in Payments you add this
```groovy
    
    @ManyToOne
    @JoinBy("user_id")
    private User user
```

If you use ```EAGER``` fetch type , you will receive all ```Payments``` associated with user

```groovy
   User user =  session.getOne(user_id)
```

If you use ```LAZY``` fetch type , you will receive proxy of ```Payments``` associated with user

```groovy
   User user =  session.getOne(user_id)//will return Payments as Proxy object
```
In ```lightbox-jdbc``` you don't need to do it. Moreover you will see all joins in your code

But lets run this query directly in psql

 ```select m.* , mi.* from movie m inner join movie_info mi on m.id = mi.movie_id```

This is the output:

```groovy
id;siteid;id;value;movie_id
1;"p";1;"test";1
2;"o";2;"test";2
1;"p";3;"dasd";1
```

As you can see first and third rows have the same movie but different movie_infos.
 With [Hibernate](http://hibernate.org/) you will receive all movie_info related rows as single List
 
This is how you do it in ```lightbox-jdbc```

```groovy

final Session postgres = new DriverSession("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "123");
        final SelectWithJoin movie_info = new SelectWithJoin(
                postgres,
                new SimpleQuery("select m.* , mi.* from movie m inner join movie_info mi on m.id = mi.movie_id"),
                new JoinTables(new JoinTable("movie_info"))
        );
        final Rows maps = movie_info.result().call();
        System.out.println(new JsonValuesOf(maps));
``` 

This is the the output:

```[
{
"site":"p",
"movie_info":
    [
        {"id":1,"movie_id":1,"value":"test"},
        {"id":3,"movie_id":1,"value":"dasd"}
    ]
 ,"id":1
},
{
"site":"o",
"movie_info":
      {"id":2,"movie_id":2,"value":"test"},
"id":2}
      
   ]
```

As you can see movie with id 1 has 2 movie_info rows associated with it


Logging:


If you need to log all your SQL statements you need to decorate your Session into LoggedSession:

```groovy
final SimpleQuery query = new SimpleQuery("select * from people");
        final Session postgres = new LoggedSession(
                new DriverSession(
                        "jdbc:postgresql://127.0.0.1:5432/test",
                        "postgres",
                        "123"
                ),
                log,
                query,
                new LogStatementsOf(LogStatements.CONNECTION, LogStatements.RESULT_SET)
        );
        final Select select = new Select(postgres, query);
```
In this way you will log close,open methods of Connection class and
all sqlExecution with executed sql query and time if execution in millis

Some times you need to map your ResultSet to POJO , I don't like ORM,
instead I suggest to use [SQL-Speaking Objects](https://www.yegor256.com/2014/12/01/orm-offensive-anti-pattern.html),
Example:
We declare Sql-Speaking Object
```groovy

interface User extends SqlObject {
        @Column(name = "name", nullable = false)
        String name();

        @Column(name = "id", nullable = false)
        int id();
    }
    
    
```
Then we can map ResultSet to this object
```java
public void simpleBindTest() throws Exception {
        final User user = new RowsAsSqlObject<>(
                new Select(
                        postgres,
                        new SimpleQuery(
                                "SELECT * FROM simpleBindTest where id = :id",
                                new IntValue("id", 1)
                        )
                ),
                User.class
        ).get();

        assertThat(user.name(), is("Almas"));
        assertThat(user.id(), is(1));
    }
```

This is an example how you can execute transaction
```groovy

final TransactedSession session = new TransactedSession(postgres);
        try {
            new Transaction<>(
                    session,
                    () -> {
                        new Update(
                                session,
                                new SimpleQuery(
                                        "insert into testTransaction (name,login) values (:name,:login)",
                                        new StringValue("name", "Almas"),
                                        new StringValue("login", "almas")
                                )
                        ).result();
                        new Update(
                                session,
                                new SimpleQuery(
                                        "insert into testTransaction (name,login) values (:name,:login)",
                                        new StringValue("name", "Almas"),
                                        new StringValue("login", "almas")
                                )
                        ).result();
                        return true;
                    }
            ).result();
        } catch (final Exception exc) {
            final Iterator<Map<String, Object>> iterator = new Select(
                    postgres,
                    new SimpleQuery(
                            String.join(
                                    " ",
                                    "SELECT * from testTransaction",
                                    "where login = :login"
                            ),
                            new StringValue("login", "Almas")
                    )
            ).result().call().iterator();

            assertThat(iterator.hasNext(), is(false));

        }
```
Here you create TransactionSession and pass Callback to Transaction object , remember, all 
code which use Database inside call back must to use the same instance of TransactionalSession
Optionally you can provide a list of supported exception,
if exception appeared and it's not in this list, all your statements would be
commited. By default Transaction is rolled back if type of Exception has appeared 
package com.github.strogiyotec.lightbox.jdbc.log;

public interface JdbcLog {

    JdbcLog SQL_AND_TIME = new SqlAndTimeLog();

    JdbcLog SQL = new SqlOnlyLog();

    JdbcLog AUDIT = new AuditLog();

    JdbcLog RESULT_SET = new ResultSetLog();

    JdbcLog CONNECTION = new ConnectionLog();

    JdbcLog TRANSACTIONS = new TransactionLog();

    /**
     * @return true if need to log all sql queries and execution time
     */
    boolean sqlAndTime();

    /**
     * @return true if need to log all sql queries
     */
    boolean sqlOnly();

    /**
     * @return true if need to log all JDBC calls
     */
    boolean audit();

    /**
     * @return true if need to log {@link java.sql.ResultSet}
     */
    boolean resultSet();

    /**
     * @return true if need to log open and close connections
     */
    boolean connection();

    /**
     * @return true if need to log commit and rollback
     */
    boolean transactions();

    class SqlAndTimeLog implements JdbcLog {

        @Override
        public boolean sqlAndTime() {
            return true;
        }

        @Override
        public boolean sqlOnly() {
            return false;
        }

        @Override
        public boolean audit() {
            return false;
        }

        @Override
        public boolean resultSet() {
            return false;
        }

        @Override
        public boolean connection() {
            return false;
        }

        @Override
        public boolean transactions() {
            return false;
        }
    }

    class SqlOnlyLog implements JdbcLog {

        @Override
        public boolean sqlAndTime() {
            return false;
        }

        @Override
        public boolean sqlOnly() {
            return true;
        }

        @Override
        public boolean audit() {
            return false;
        }

        @Override
        public boolean resultSet() {
            return false;
        }

        @Override
        public boolean connection() {
            return false;
        }

        @Override
        public boolean transactions() {
            return false;
        }
    }

    class AuditLog implements JdbcLog {

        @Override
        public boolean sqlAndTime() {
            return false;
        }

        @Override
        public boolean sqlOnly() {
            return false;
        }

        @Override
        public boolean audit() {
            return true;
        }

        @Override
        public boolean resultSet() {
            return false;
        }

        @Override
        public boolean connection() {
            return false;
        }

        @Override
        public boolean transactions() {
            return false;
        }
    }

    class ResultSetLog implements JdbcLog {

        @Override
        public boolean sqlAndTime() {
            return false;
        }

        @Override
        public boolean sqlOnly() {
            return false;
        }

        @Override
        public boolean audit() {
            return false;
        }

        @Override
        public boolean resultSet() {
            return true;
        }

        @Override
        public boolean connection() {
            return false;
        }

        @Override
        public boolean transactions() {
            return false;
        }
    }

    class ConnectionLog implements JdbcLog {

        @Override
        public boolean sqlAndTime() {
            return false;
        }

        @Override
        public boolean sqlOnly() {
            return false;
        }

        @Override
        public boolean audit() {
            return false;
        }

        @Override
        public boolean resultSet() {
            return false;
        }

        @Override
        public boolean connection() {
            return false;
        }

        @Override
        public boolean transactions() {
            return true;
        }
    }

    class TransactionLog implements JdbcLog {

        @Override
        public boolean sqlAndTime() {
            return false;
        }

        @Override
        public boolean sqlOnly() {
            return false;
        }

        @Override
        public boolean audit() {
            return false;
        }

        @Override
        public boolean resultSet() {
            return false;
        }

        @Override
        public boolean connection() {
            return false;
        }

        @Override
        public boolean transactions() {
            return true;
        }
    }

}
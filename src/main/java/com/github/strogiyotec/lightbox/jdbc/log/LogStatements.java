package com.github.strogiyotec.lightbox.jdbc.log;

public interface LogStatements {

    LogStatements SQL_AND_TIME = new SqlAndTimeLog();

    LogStatements SQL = new SqlOnlyLog();

    LogStatements AUDIT = new AuditLog();

    LogStatements RESULT_SET = new ResultSetLog();

    LogStatements CONNECTION = new ConnectionLog();

    LogStatements TRANSACTIONS = new TransactionLog();

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

    class SqlAndTimeLog implements LogStatements {

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

    class SqlOnlyLog implements LogStatements {

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

    class AuditLog implements LogStatements {

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

    class ResultSetLog implements LogStatements {

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

    class ConnectionLog implements LogStatements {

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

    class TransactionLog implements LogStatements {

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
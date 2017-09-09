package com.sohu;

import java.sql.SQLException;

/**
 * User: chenghaixing
 * Date: 2014-7-14
 * Time: 18:12:09
 */
public class DaoException extends RuntimeException {


    /**
     * @serial
     */
    private String SQLState;

    /**
     * @serial
     */
    private int vendorCode;

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message, String SQLState) {
        super(message);
        this.SQLState = SQLState;
    }

    public DaoException(String message, String SQLState, int vendorCode) {
        super(message);
        this.SQLState = SQLState;
        this.vendorCode = vendorCode;
    }

    public DaoException(SQLException e) {
        super(e);
        this.SQLState = e.getSQLState();
        this.vendorCode = e.getErrorCode();
    }

    public DaoException() {
    }

    /**
     * Retrieves the SQLState for this <code>SQLException</code> object.
     *
     * @return the SQLState value
     */
    public String getSQLState() {
        return (SQLState);
    }

    /**
     * Retrieves the vendor-specific exception code
     * for this <code>SQLException</code> object.
     *
     * @return the vendor's error code
     */
    public int getErrorCode() {
        return (vendorCode);
    }


}

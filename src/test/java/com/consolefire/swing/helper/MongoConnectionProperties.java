/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consolefire.swing.helper;

/**
 *
 * @author sabuj.das
 */
public class MongoConnectionProperties {
    
    private String primaryHostName;
    private int port;
    private String databaseName;

    public String getPrimaryHostName() {
        return primaryHostName;
    }

    public void setPrimaryHostName(String primaryHostName) {
        this.primaryHostName = primaryHostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
    
    
    
}

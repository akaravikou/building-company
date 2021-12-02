package com.solvd.buildingcompany.persistence;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ConnectionPool {

    private volatile static ConnectionPool instance = null;

    private final List<Connection> availableConnections;

    private ConnectionPool(Integer quantityConnection) throws IOException {
        try {
            Class.forName(Configuration.DRIVER);
        } catch (ClassNotFoundException e) {
            throw new ConnectException("Driver not found");
        }
        this.availableConnections = new ArrayList<>();
        IntStream.range(0, quantityConnection)
                .boxed()
                .forEach(x -> {
                    try {
                        this.availableConnections.add(createConnection());
                    } catch (IOException e) {
                        try {
                            throw new ConnectException("Problems with connection");
                        } catch (ConnectException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
    }

    private Connection createConnection() throws IOException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(Configuration.URL, Configuration.USER, Configuration.PASSWORD);
        } catch (SQLException e) {
            throw new ConnectException("Cannot create new connection");
        }
        return connection;
    }

    public static ConnectionPool getInstance(Integer quantityConnection) throws IOException {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool(quantityConnection);
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        synchronized (this.availableConnections) {
            while (this.availableConnections.isEmpty()) {
                try {
                    availableConnections.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return availableConnections.remove(0);
    }

    public void releaseConnection(Connection connection) {
        synchronized (this.availableConnections) {
            this.availableConnections.add(connection);
            this.availableConnections.notifyAll();
        }
    }
}
package com.solvd.buildingcompany.persistence.impl;

import com.solvd.buildingcompany.domain.Address;
import com.solvd.buildingcompany.domain.BuildingCompany;
import com.solvd.buildingcompany.persistence.AddressRepository;
import com.solvd.buildingcompany.persistence.ConnectionPool;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class AddressRepositoryImpl implements AddressRepository {

    ConnectionPool connectionPool = ConnectionPool.getInstance(5);

    public AddressRepositoryImpl() throws IOException {
    }

    @Override
    public void create(Address address) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Addresses(city, street," +
                "house_number, apartment_number) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, address.getCity());
            preparedStatement.setString(2, address.getStreet());
            preparedStatement.setString(3, address.getHouseNumber());
            preparedStatement.setInt(4, address.getApartmentNumber());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                address.setId(resultSet.getLong(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public void delete(String city) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Addresses WHERE city = ?")) {
            preparedStatement.setString(1, city);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public Address createIfNotExists(Long addressId, List<Address> addresses) {
        return addresses.stream()
                .filter(address -> address.getId().equals(addressId))
                .findFirst()
                .orElseGet(() -> {
                    Address address= new Address();
                    addresses.add(address);
                    return address;
                });
    }

    public void update(Address address) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Addresses SET city = ?, " +
                " street = ?, house_number = ?, apartment_number = ?;")) {
            preparedStatement.setString(1, address.getCity());
            preparedStatement.setString(2, address.getStreet());
            preparedStatement.setString(3, address.getHouseNumber());
            preparedStatement.setInt(4, address.getApartmentNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}

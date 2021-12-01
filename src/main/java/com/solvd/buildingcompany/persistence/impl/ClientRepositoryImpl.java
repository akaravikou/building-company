package com.solvd.buildingcompany.persistence.impl;

import com.solvd.buildingcompany.domain.Address;
import com.solvd.buildingcompany.domain.BuildingCompany;
import com.solvd.buildingcompany.domain.Client;
import com.solvd.buildingcompany.persistence.ClientRepository;
import com.solvd.buildingcompany.persistence.ConnectionPool;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepositoryImpl implements ClientRepository {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance(5);

    private static String selectInnerJoin() {
        return "SELECT a.city, a.street, a.house_number, a.apartment_number, " +
                " c.first_name, c.last_name, b.name FROM Clients c INNER JOIN Addresses a ON a.id = c.address_id " +
                " INNER JOIN Building_companies b ON a.id = b.id;";
    }

    public ClientRepositoryImpl() throws IOException {
    }

    @Override
    public void create(Client client, Long companyId) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Clients(address_id, " +
                " company_id, first_name, last_name) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, client.getAddress().getId());
            preparedStatement.setLong(2, companyId);
            preparedStatement.setString(3, client.getFirstName());
            preparedStatement.setString(4, client.getLastName());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                client.setId(resultSet.getLong(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Long> findIdByLastName(String lastName) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatementSelect = connection.prepareStatement("SELECT id FROM Clients WHERE last_name = ?")) {
            preparedStatementSelect.setString(1, lastName);
            ResultSet resultSet = preparedStatementSelect.executeQuery();
            return resultSet.next()
                    ? Optional.of(resultSet.getLong("id"))
                    : Optional.empty();
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public List<BuildingCompany> select() {
        Connection connection = connectionPool.getConnection();
        BuildingCompany buildingCompany = new BuildingCompany();
        Address address = new Address();
        Client client = new Client();
        List<BuildingCompany> companies = new ArrayList<>();
        List<Client> clients = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectInnerJoin())) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                address.setCity(resultSet.getString("city"));
                address.setStreet(resultSet.getString("street"));
                address.setHouseNumber(resultSet.getString("house_number"));
                address.setApartmentNumber(resultSet.getInt("apartment_number"));
                buildingCompany.setAddress(address);
                client.setFirstName(resultSet.getString("first_name"));
                client.setLastName(resultSet.getString("last_name"));
                clients.add(client);
                buildingCompany.setClients(clients);
                buildingCompany.setName("name");
                companies.add(buildingCompany);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return companies;
    }

    public Client createIfNotExists(Long clientId, List<Client> clients) {
        return clients.stream()
                .filter(client -> client.getId().equals(clientId))
                .findFirst()
                .orElseGet(() -> {
                    Client client= new Client();
                    clients.add(client);
                    return client;
                });
    }
}


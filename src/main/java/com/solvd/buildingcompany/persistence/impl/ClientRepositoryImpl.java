package com.solvd.buildingcompany.persistence.impl;

import com.solvd.buildingcompany.domain.Address;
import com.solvd.buildingcompany.domain.BuildingCompany;
import com.solvd.buildingcompany.domain.Client;
import com.solvd.buildingcompany.domain.exception.RetrieveDataException;
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
        return "SELECT a.id as address_id, a.city, a.street, a.house_number, a.apartment_number, " +
                " c.id as client_id, c.first_name, c.last_name, b.id as company_id, b.name FROM Clients c LEFT JOIN Addresses a ON a.id = c.address_id " +
                " LEFT JOIN Building_companies b ON c.company_id = b.id;";
    }

    public ClientRepositoryImpl() throws IOException {
    }

    @Override
    public void create(Client client, Long companyId) throws RetrieveDataException {
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
        } catch (SQLException exception) {
            throw new RetrieveDataException("Problem with the data");
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public List<BuildingCompany> get() throws RetrieveDataException {
        Connection connection = connectionPool.getConnection();
        List<BuildingCompany> companies = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectInnerJoin())) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long companyId = resultSet.getLong("company_id");
                BuildingCompany newCompany = BuildingCompanyRepositoryImpl.createIfNotExists(companyId, companies);
                newCompany.setName(resultSet.getString("name"));

                if (newCompany.getClients() == null) {
                    newCompany.setClients(new ArrayList<>());
                }
                Long clientId = resultSet.getLong("client_id");
                Client newClient = createIfNotExists(clientId, newCompany.getClients());
                newClient.setId(resultSet.getLong("client_id"));
                newClient.setFirstName(resultSet.getString("first_name"));
                newClient.setLastName(resultSet.getString("last_name"));

                if (newClient.getAddress() == null) {
                    Address address = new Address();
                    address.setId(resultSet.getLong("address_id"));
                    address.setCity(resultSet.getString("city"));
                    address.setStreet(resultSet.getString("street"));
                    address.setHouseNumber(resultSet.getString("house_number"));
                    address.setApartmentNumber(resultSet.getInt("apartment_number"));
                    newClient.setAddress(address);
                }
            }
        } catch (SQLException exception) {
            throw new RetrieveDataException("Problem with the data");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return companies;
    }

    public static Client createIfNotExists(Long clientId, List<Client> clients) {
        return clients.stream()
                .filter(client -> client.getId().equals(clientId))
                .findFirst()
                .orElseGet(() -> {
                    Client client= new Client();
                    client.setId(clientId);
                    clients.add(client);
                    return client;
                });
    }
}


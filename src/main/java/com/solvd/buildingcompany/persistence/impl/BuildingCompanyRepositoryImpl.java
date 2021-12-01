package com.solvd.buildingcompany.persistence.impl;

import com.solvd.buildingcompany.domain.Address;
import com.solvd.buildingcompany.domain.BuildingCompany;
import com.solvd.buildingcompany.domain.Client;
import com.solvd.buildingcompany.persistence.BuildingCompanyRepository;
import com.solvd.buildingcompany.persistence.ClientRepository;
import com.solvd.buildingcompany.persistence.ConnectionPool;
import com.solvd.buildingcompany.service.ClientService;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class BuildingCompanyRepositoryImpl implements BuildingCompanyRepository {

    ConnectionPool connectionPool = ConnectionPool.getInstance(5);

    ClientRepository clientRepository = new ClientRepositoryImpl();

    public BuildingCompanyRepositoryImpl() throws IOException {
    }

    @Override
    public void create(BuildingCompany buildingCompany) throws IOException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Building_companies(address_id, " +
                "name) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, buildingCompany.getAddress().getId());
            preparedStatement.setString(2, buildingCompany.getName());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                buildingCompany.setId(resultSet.getLong(1));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Long> findIdByName(String name) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatementSelect = connection.prepareStatement("SELECT id FROM Building_companies WHERE name = ?")) {
            preparedStatementSelect.setString(1, name);
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

    public BuildingCompany createIfNotExists(Long companyId, List<BuildingCompany> companies) {
        return companies.stream()
                .filter(buildingCompany -> buildingCompany.getId().equals(companyId))
                .findFirst()
                .orElseGet(() -> {
                    BuildingCompany newCompany = new BuildingCompany();
                    companies.add(newCompany);
                    return newCompany;
                });
    }

    public List<BuildingCompany> createUniqueCompanies(List<BuildingCompany> companies) {
        for (BuildingCompany buildingCompany : companies) {
            for (Client client : buildingCompany.getClients()) {
                clientRepository.createIfNotExists(client.getId(), buildingCompany.getClients());
                if (client.getAddress() != null) {
                    Address address = new Address();
                    client.setAddress(address);
                }
                createIfNotExists(buildingCompany.getId(), companies);
            }
        }
        return companies;
    }
}
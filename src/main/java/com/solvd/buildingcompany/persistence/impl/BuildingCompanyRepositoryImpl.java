package com.solvd.buildingcompany.persistence.impl;

import com.solvd.buildingcompany.domain.BuildingCompany;
import com.solvd.buildingcompany.domain.exception.RetrieveDataException;
import com.solvd.buildingcompany.persistence.BuildingCompanyRepository;
import com.solvd.buildingcompany.persistence.ConnectionPool;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class BuildingCompanyRepositoryImpl implements BuildingCompanyRepository {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance(5);

    public BuildingCompanyRepositoryImpl() throws IOException {
    }

    @Override
    public void create(BuildingCompany buildingCompany) throws IOException, RetrieveDataException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Building_companies(address_id, " +
                " name) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, buildingCompany.getAddress().getId());
            preparedStatement.setString(2, buildingCompany.getName());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                buildingCompany.setId(resultSet.getLong(1));
            }
        } catch (SQLException exception) {
            throw new RetrieveDataException("Problem with the data");
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Long> findIdByName(String name) throws RetrieveDataException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatementSelect = connection.prepareStatement("SELECT id FROM Building_companies WHERE name = ?")) {
            preparedStatementSelect.setString(1, name);
            ResultSet resultSet = preparedStatementSelect.executeQuery();
            return resultSet.next()
                    ? Optional.of(resultSet.getLong("id"))
                    : Optional.empty();
        } catch (SQLException exception) {
            throw new RetrieveDataException("Problem with the data");
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public static BuildingCompany createIfNotExists(Long companyId, List<BuildingCompany> companies) {
        return companies.stream()
                .filter(buildingCompany -> buildingCompany.getId().equals(companyId))
                .findFirst()
                .orElseGet(() -> {
                    BuildingCompany newCompany = new BuildingCompany();
                    newCompany.setId(companyId);
                    companies.add(newCompany);
                    return newCompany;
                });
    }
}
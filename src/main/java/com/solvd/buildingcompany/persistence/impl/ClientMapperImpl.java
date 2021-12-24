package com.solvd.buildingcompany.persistence.impl;

import com.solvd.buildingcompany.domain.BuildingCompany;
import com.solvd.buildingcompany.domain.Client;
import com.solvd.buildingcompany.domain.exception.RetrieveDataException;
import com.solvd.buildingcompany.persistence.AddressRepository;
import com.solvd.buildingcompany.persistence.ClientRepository;
import com.solvd.buildingcompany.persistence.MybatisConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ClientMapperImpl implements ClientRepository {

    @Override
    public void create(Client client, Long companyId) throws RetrieveDataException {
        try (SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            clientRepository.create(client, companyId);
        }
    }

    @Override
    public List<BuildingCompany> get() throws RetrieveDataException {
        try (SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            return clientRepository.get();
        }
    }

    @Override
    public Integer getCount() throws RetrieveDataException {
        try (SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            return clientRepository.getCount();
        }
    }

    @Override
    public List<Client> getAll() throws RetrieveDataException {
        try (SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            return clientRepository.getAll();
        }
    }
}
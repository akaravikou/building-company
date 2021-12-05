package com.solvd.buildingcompany.persistence.impl;

import com.solvd.buildingcompany.domain.Address;
import com.solvd.buildingcompany.domain.exception.RetrieveDataException;
import com.solvd.buildingcompany.persistence.AddressRepository;
import com.solvd.buildingcompany.persistence.MybatisConfig;
import org.apache.ibatis.session.SqlSession;

public class AddressMapperImpl implements AddressRepository {

    @Override
    public void create(Address address) throws RetrieveDataException {
        try (SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AddressRepository addressRepository = sqlSession.getMapper(AddressRepository.class);
            addressRepository.create(address);
        }
    }

    @Override
    public void delete(String city) throws RetrieveDataException {
        try (SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AddressRepository addressRepository = sqlSession.getMapper(AddressRepository.class);
            addressRepository.delete(city);
        }
    }

    @Override
    public void update(Address address) throws RetrieveDataException {
        try (SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AddressRepository addressRepository = sqlSession.getMapper(AddressRepository.class);
            addressRepository.update(address);
        }
    }
}

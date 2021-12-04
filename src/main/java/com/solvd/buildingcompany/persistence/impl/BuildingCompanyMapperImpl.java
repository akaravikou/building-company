package com.solvd.buildingcompany.persistence.impl;

import com.solvd.buildingcompany.domain.BuildingCompany;
import com.solvd.buildingcompany.domain.exception.RetrieveDataException;
import com.solvd.buildingcompany.persistence.BuildingCompanyRepository;
import com.solvd.buildingcompany.persistence.MybatisConfig;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.Optional;

public class BuildingCompanyMapperImpl implements BuildingCompanyRepository {

    @Override
    public void create(BuildingCompany buildingCompany) throws RetrieveDataException, IOException {
        try (SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            BuildingCompanyRepository buildingCompanyRepository = sqlSession.getMapper(BuildingCompanyRepository.class);
            buildingCompanyRepository.create(buildingCompany);
        }
    }

    @Override
    public Optional<Long> findIdByName(String name) throws RetrieveDataException {
        try (SqlSession sqlSession = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            BuildingCompanyRepository buildingCompanyRepository = sqlSession.getMapper(BuildingCompanyRepository.class);
            return buildingCompanyRepository.findIdByName(name);
        }
    }
}
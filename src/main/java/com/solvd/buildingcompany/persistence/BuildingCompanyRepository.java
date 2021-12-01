package com.solvd.buildingcompany.persistence;

import com.solvd.buildingcompany.domain.BuildingCompany;
import com.solvd.buildingcompany.domain.exception.RetrieveDataException;

import java.io.IOException;
import java.util.Optional;

public interface BuildingCompanyRepository {

    void create(BuildingCompany buildingCompany) throws IOException, RetrieveDataException;

    Optional<Long> findIdByName(String name) throws RetrieveDataException;

}

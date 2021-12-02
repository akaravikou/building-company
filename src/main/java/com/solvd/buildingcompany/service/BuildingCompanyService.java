package com.solvd.buildingcompany.service;

import com.solvd.buildingcompany.domain.BuildingCompany;
import com.solvd.buildingcompany.domain.exception.RetrieveDataException;

import java.io.IOException;
import java.util.List;

public interface BuildingCompanyService {

    BuildingCompany create(BuildingCompany buildingCompany) throws IOException, RetrieveDataException;

    Long getIdByName(String name) throws RetrieveDataException;

}

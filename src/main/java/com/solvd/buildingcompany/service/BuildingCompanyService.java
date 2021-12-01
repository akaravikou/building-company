package com.solvd.buildingcompany.service;

import com.solvd.buildingcompany.domain.BuildingCompany;

import java.io.IOException;
import java.util.List;

public interface BuildingCompanyService {

    BuildingCompany create(BuildingCompany buildingCompany) throws IOException;

    Long getIdByName(String name);

    BuildingCompany createIfNotExists(Long companyId, List<BuildingCompany> companies);

    List<BuildingCompany> createUniqueCompanies (List<BuildingCompany> companies);

}

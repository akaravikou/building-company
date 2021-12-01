package com.solvd.buildingcompany.persistence;

import com.solvd.buildingcompany.domain.BuildingCompany;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BuildingCompanyRepository {

    void create(BuildingCompany buildingCompany) throws IOException;

    Optional<Long> findIdByName(String name);

    BuildingCompany createIfNotExists(Long companyId, List<BuildingCompany> companies);

    List<BuildingCompany> createUniqueCompanies (List<BuildingCompany> companies);

}

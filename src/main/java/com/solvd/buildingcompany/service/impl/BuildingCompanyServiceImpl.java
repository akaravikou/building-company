package com.solvd.buildingcompany.service.impl;

import com.solvd.buildingcompany.domain.Address;
import com.solvd.buildingcompany.domain.BuildingCompany;
import com.solvd.buildingcompany.persistence.BuildingCompanyRepository;
import com.solvd.buildingcompany.persistence.impl.BuildingCompanyRepositoryImpl;
import com.solvd.buildingcompany.service.AddressService;
import com.solvd.buildingcompany.service.BuildingCompanyService;

import java.io.IOException;
import java.util.List;

public class BuildingCompanyServiceImpl implements BuildingCompanyService {

    private BuildingCompanyRepository buildingCompanyRepository;
    private AddressService addressService;

    public BuildingCompanyServiceImpl() throws IOException {
        this.buildingCompanyRepository = new BuildingCompanyRepositoryImpl();
        this.addressService = new AddressServiceImpl();
    }

    @Override
    public BuildingCompany create(BuildingCompany buildingCompany) throws IOException {
        buildingCompany.setId(null);
        buildingCompanyRepository.create(buildingCompany);

        if(buildingCompany.getAddress() != null) {
            Address address = addressService.create(buildingCompany.getAddress());
            buildingCompany.setAddress(address);
        }
        return buildingCompany;
    }

    @Override
    public Long getIdByName(String name) {
        return buildingCompanyRepository.findIdByName(name)
                .orElseThrow(() -> new RuntimeException(String.format("Company cannot be found by name %s", name)));
    }

    public BuildingCompany createIfNotExists(Long companyId, List<BuildingCompany> companies) {
        return buildingCompanyRepository.createIfNotExists(companyId, companies);
    }

    public List<BuildingCompany> createUniqueCompanies (List<BuildingCompany> companies) {
        return buildingCompanyRepository.createUniqueCompanies(companies);
    }
}

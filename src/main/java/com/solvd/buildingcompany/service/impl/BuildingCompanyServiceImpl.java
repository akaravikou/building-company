package com.solvd.buildingcompany.service.impl;

import com.solvd.buildingcompany.domain.Address;
import com.solvd.buildingcompany.domain.BuildingCompany;
import com.solvd.buildingcompany.domain.exception.RetrieveDataException;
import com.solvd.buildingcompany.persistence.BuildingCompanyRepository;
import com.solvd.buildingcompany.persistence.impl.BuildingCompanyMapperImpl;
import com.solvd.buildingcompany.persistence.impl.BuildingCompanyRepositoryImpl;
import com.solvd.buildingcompany.service.AddressService;
import com.solvd.buildingcompany.service.BuildingCompanyService;

import java.io.IOException;

public class BuildingCompanyServiceImpl implements BuildingCompanyService {

    private BuildingCompanyRepository buildingCompanyRepository;
    private AddressService addressService;

    public BuildingCompanyServiceImpl() throws IOException {
//        this.buildingCompanyRepository = new BuildingCompanyRepositoryImpl();
        this.buildingCompanyRepository = new BuildingCompanyMapperImpl();
        this.buildingCompanyRepository = new BuildingCompanyRepositoryImpl();

        this.addressService = new AddressServiceImpl();
    }

    @Override
    public BuildingCompany create(BuildingCompany buildingCompany) throws IOException, RetrieveDataException {
        buildingCompany.setId(null);
        buildingCompanyRepository.create(buildingCompany);

        if(buildingCompany.getAddress() != null) {
            Address address = addressService.create(buildingCompany.getAddress());
            buildingCompany.setAddress(address);
        }
        return buildingCompany;
    }

    @Override
    public Long getIdByName(String name) throws RetrieveDataException {
        return buildingCompanyRepository.findIdByName(name)
                .orElseThrow(() -> new RetrieveDataException("No company with this name"));
    }
}

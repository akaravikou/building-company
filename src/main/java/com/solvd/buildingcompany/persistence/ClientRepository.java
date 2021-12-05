package com.solvd.buildingcompany.persistence;

import com.solvd.buildingcompany.domain.BuildingCompany;
import com.solvd.buildingcompany.domain.Client;
import com.solvd.buildingcompany.domain.exception.RetrieveDataException;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Optional;

public interface ClientRepository {


    void create(@Param("client")Client client, @Param("companyId")Long companyId) throws RetrieveDataException;

    Optional<Long> findIdByLastName(String lastName) throws RetrieveDataException;

    List<BuildingCompany> get() throws RetrieveDataException;

}

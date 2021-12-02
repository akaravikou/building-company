package com.solvd.buildingcompany.persistence;

import com.solvd.buildingcompany.domain.Address;
import com.solvd.buildingcompany.domain.exception.RetrieveDataException;

public interface AddressRepository {

    void create(Address address) throws RetrieveDataException;

    void delete(String city) throws RetrieveDataException;

    void update(Address address) throws RetrieveDataException;

}

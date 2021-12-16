package com.test.Service;

import com.test.Exception.NotFoundException;
import com.test.Model.Address;

import java.util.List;

public interface AddresService {

        public List<Address> getall();

        public Address save(Address address);

        public void DeleteById(int id);

        public Address getById(int id) throws NotFoundException;

        public void Urdate(int id, String number) throws NotFoundException;

        public List<Address> getAllBynumbers(String numbers);

        public Address getAllByNumberAndCityAndStreet(Address address);


}

package com.test.Service;

import com.test.Execpton.NotFoundException;
import com.test.Model.Phone;


import java.util.List;

public interface PhoneService {
    public List<Phone> getall();

    public Phone save(Phone phone);

    public void DeleteById(int id);

    public Phone getById(int id) throws NotFoundException;

    public void Urdate(int id, String models) throws NotFoundException;

    public List<Phone> getAllByModels(String models);


}

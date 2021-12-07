package com.test.Service;

import com.test.Execpton.NotFoundException;
import com.test.Model.Phone;
import com.test.Model.User;
import com.test.Repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService {
    @Autowired
    private PhoneRepository PhoneRepository;

    @Override
    public List<Phone> getall() {
        return PhoneRepository.findAll();
    }

    @Override
    public Phone save(Phone phone) {
        return PhoneRepository.save(phone);
    }

    @Override
    public void DeleteById(int id) {
        PhoneRepository.deleteById(id);
    }

    @Override
    public Phone getById(int id) throws NotFoundException {
        Optional<Phone> optional = PhoneRepository.findById(id);

        if (!optional.isPresent()) {
            throw new NotFoundException("not id phone");
        }
        return optional.get();
    }

    @Override
    public void Urdate(int id, String models) throws NotFoundException {
        Phone phone = getById(id);
        phone.setModels(models);
        PhoneRepository.save(phone);
    }

    @Override
    public List<Phone> getAllByModels(String models) {
        return PhoneRepository.getAllByModels(models);
    }
}

package com.test.Controller;

import com.test.Exception.NotFoundException;
import com.test.Model.Phone;
import com.test.Service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/phone")
public class PhoneController {
    @Autowired
    private PhoneService phoneService;

    @GetMapping
    List<Phone> getAll() {
        return phoneService.getall();
    }

    @GetMapping("{id}")
    Phone getById(@PathVariable int id) throws NotFoundException {
        return phoneService.getById(id);
    }


    @DeleteMapping
    public void DeleteById(@RequestParam("id") int id){
       phoneService.DeleteById(id);
    }

    @PutMapping
    public void Urdate(@RequestParam("id") int id,@RequestParam("models") String models) throws NotFoundException {
        phoneService.Urdate(id,models );

    }

    @PostMapping
    void save(@RequestBody Phone phone) {
        phoneService.save(phone);
    }


    @GetMapping("/get-all-by-models")
    public List<Phone> getAllByModels(@RequestParam("models")String models) {
        return phoneService.getAllByModels(models);
    }


}

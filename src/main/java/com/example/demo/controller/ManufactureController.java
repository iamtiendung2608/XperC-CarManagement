/*
* Controller for Manufacture
*/

package com.example.demo.controller;

import com.example.demo.dto.ManufactureDTO;
import com.example.demo.exception.ManufactureNotFoundException;
import com.example.demo.model.Manufacture;
import com.example.demo.repo.ManufactureRepo;
import com.example.demo.service.ManufactureServiceImp;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

/*
* Class handles 4 features: add, edit, delete, find with paging manufacture
*/
@RestController
public class ManufactureController {
    /* Call ManufactureServiceImp */
    @Autowired
    private ManufactureServiceImp manufactureServiceImp;

    /**
     * Function for paging manufactures return from server
     * @param pageable: request param include sort, pagesize, pagenumber
     * @return ResponseEntity contains List of manufactures in database with paging
     * */
    @GetMapping("/manufacture")
    public ResponseEntity<List<ManufactureDTO>>getAllManufacture(Pageable pageable) {
        return ResponseEntity.ok().body(manufactureServiceImp.findAllManufacture(pageable));
    }

    /**
     * Function for adding manufacture to database
     * @param manufacture: manufacture need to insert to database
     * @return HttpStatus.CREATED indicate that create manufacture created success
     * */
    @PostMapping("/manufacture/add")
    public ResponseEntity<String>getAllManufacture(@RequestBody ManufactureDTO manufacture) {
        manufactureServiceImp.editManufacture(manufacture);
        return new ResponseEntity<>("New manufacture created success", HttpStatus.CREATED);
    }

    /**
     * Function for find and update manufacture to database
     * @param id: primary key of the manufacture need to be updated
     * @exception ManufactureNotFoundException indicate that the manufacture with id not found
     * @return ResponseEntity with the value of that manufacture
     * */
    @GetMapping("/manufacture/update/{id}")
    public ResponseEntity<ManufactureDTO>getUpdateManufacture(@PathVariable("id") int id){
        try {
            ManufactureDTO manufacture = manufactureServiceImp.findById(id);
            return ResponseEntity.ok().body(manufacture);
        } catch (ManufactureNotFoundException e) {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Function for find and update manufacture to database
     * @param manufacture: manufacture after changing properties
     * @return HttpStatus.ACCEPTED indicate update accepted
     * */
    @PutMapping("/manufacture/update")
    public ResponseEntity<String>UpdateManufacture(@RequestBody ManufactureDTO manufacture){
        manufactureServiceImp.editManufacture(manufacture);
        return new ResponseEntity<>("Update manufacture success", HttpStatus.NO_CONTENT);
    }

    /**
     * Function for find and update manufacture to database
     * @param id: manufacture's id need to be deleted
     * @return HttpStatus.OK indicate delete accepted
     * */
    @DeleteMapping("/manufacture/delete/{id}")
    public ResponseEntity<String>deleteManufacture(@PathVariable("id") int id){
        manufactureServiceImp.deleteManufacture(id);
        return new ResponseEntity<>("Delete manufacture success", HttpStatus.NO_CONTENT);
    }
}

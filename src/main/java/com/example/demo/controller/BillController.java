package com.example.demo.controller;

import com.example.demo.entity.Bill;
import com.example.demo.services.BillServices;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bills")
@CrossOrigin
public class BillController {

    @Autowired
    private BillServices billServices;

    @GetMapping(value = "/list")
    public ResponseEntity<List<Bill>> list(){
        List<Bill> list = this.billServices.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Bill> addBill(@RequestBody Bill bill) {
        Bill newbill = this.billServices.add(bill);
        return new ResponseEntity<Bill>(newbill, HttpStatus.OK);
    }

    @GetMapping(value = "/getById/{id}")
    public ResponseEntity<Bill> findId(@PathVariable("id") Long id){
        Bill newBill = this.billServices.get(id).get();
        return new ResponseEntity<Bill>(newBill, HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Bill> updateBill(@RequestBody Bill bill){
        Optional<Bill> update = this.billServices.get(bill.getId());
        update.get().setConsumer(bill.getConsumer());
        update.get().setItems(bill.getItems());
        update.get().setTitle(bill.getTitle());
        Bill newBill = this.billServices.update(update);

        return new ResponseEntity<Bill>(newBill, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteId/{id}")
    public ResponseEntity<String> deleteBill(@PathVariable("id") Long id){
        this.billServices.delete(id);
        return new ResponseEntity<String>("OKE",HttpStatus.OK);
    }



}

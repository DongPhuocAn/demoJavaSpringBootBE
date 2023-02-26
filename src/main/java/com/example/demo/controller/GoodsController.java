package com.example.demo.controller;

import com.example.demo.entity.Bill;
import com.example.demo.entity.Goods;
import com.example.demo.services.BillServices;
import com.example.demo.services.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/goods")
@CrossOrigin
public class GoodsController {
    @Autowired
    private GoodService goodService;

    @GetMapping(value = "/list")
    public ResponseEntity<List<Goods>> list(){
        List<Goods> list = this.goodService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/getById/{id}")
    public ResponseEntity<Goods> findId(@PathVariable("id") Long id){
        Goods newGood = this.goodService.get(id).get();
        return new ResponseEntity<Goods>(newGood, HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Goods> addGoods(@RequestBody Goods goods) {
        Goods newGoods = this.goodService.add(goods);
        return new ResponseEntity<Goods>(newGoods, HttpStatus.OK);
    }


    @PutMapping(value = "/update")
    public ResponseEntity<Goods> updateBill(@RequestBody Goods goods){
        Optional<Goods> update = this.goodService.get(goods.getId());
        update.get().setCode(goods.getCode());
        update.get().setName(goods.getName());
        update.get().setPrice(goods.getPrice());
        Goods newGood = this.goodService.update(update);

        return new ResponseEntity<Goods>(newGood, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteId/{id}")
    public ResponseEntity<String> deleteBill(@PathVariable("id") Long id){
        this.goodService.delete(id);
        return new ResponseEntity<String>("OKE",HttpStatus.OK);
    }

    @GetMapping(value = "/search/{code}/{name}/{page}")
    public ResponseEntity<List<Goods>> searchByConditions(@PathVariable("code") String code,
                                                          @PathVariable("name") String name, @PathVariable("page") int page) {
        List<Goods> list = goodService.seachByConditions(code, name, page);
        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @GetMapping(value = "/search/{code}/{name}")
    public ResponseEntity<Integer> numberOfRecord(@PathVariable("code") String code,
                                                  @PathVariable("name") String name) {
        int total = this.goodService.numberOfRecord(code, name);
        return new ResponseEntity<>(total, HttpStatus.OK);

    }
}

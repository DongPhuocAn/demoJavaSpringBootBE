package com.example.demo.services;

import com.example.demo.entity.Bill;
import com.example.demo.entity.Items;
import com.example.demo.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BillServiceImpl implements BillServices{
    @Autowired
    private BillRepository billRepository;

    @Override
    public List<Bill> list() {
        return this.billRepository.findAll();
    }

    @Override
    public Bill add(Bill bill) {
        Bill newBill = new Bill();
        if(bill.getItems() != null) {
            for(Items item : bill.getItems()){
                item.setBill(bill);
            }
        }
        if(bill.getConsumer() != null){
            List<Bill> billList = new ArrayList<>();
            billList.add(bill);
            bill.getConsumer().setBills(billList);
        }
        newBill = this.billRepository.save(bill);
        return newBill;
    }

    @Override
    public Optional<Bill> get(Long id) {
        return this.billRepository.findById(id);
    }

    @Override
    public Bill update(Optional<Bill> bill) {
        return this.billRepository.save(bill.get());
    }

    @Override
    public void delete(Long id) {
        this.billRepository.deleteById(id);
    }

    @Override
    public Bill seachBillWithName(String name) {
        return this.billRepository.seachBillWithName(name);
    }
}

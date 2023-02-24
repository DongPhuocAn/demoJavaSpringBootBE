package com.example.demo.services;

import com.example.demo.entity.Bill;

import java.util.List;
import java.util.Optional;

public interface BillServices {

    public List<Bill> list();

    public Bill add(Bill bill);

    public Optional<Bill> get(Long id);

    public Bill update(Optional<Bill> bill);

    public void delete(Long id);

    Bill seachBillWithName(String name);
}

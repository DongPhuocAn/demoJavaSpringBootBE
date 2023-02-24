package com.example.demo.services;

import com.example.demo.entity.Bill;
import com.example.demo.entity.Goods;

import java.util.List;
import java.util.Optional;

public interface GoodService {
    public List<Goods> list();

    public Goods add(Goods goods);

    public Optional<Goods> get(Long id);

    public Goods update(Optional<Goods> bill);

    public void delete(Long id);

    List<Goods> seachByConditions(String code, String name, int page);

    public int numberOfRecord(String code, String name);
}

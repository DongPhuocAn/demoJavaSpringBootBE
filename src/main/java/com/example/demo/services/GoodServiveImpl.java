package com.example.demo.services;

import com.example.demo.entity.Bill;
import com.example.demo.entity.Goods;
import com.example.demo.entity.Items;
import com.example.demo.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GoodServiveImpl implements GoodService{

    @Autowired
    private GoodRepository goodRepository;

    @Override
    public List<Goods> list() {
        return this.goodRepository.findAll();
    }

    @Override
    public Goods add(Goods goods) {
       return this.goodRepository.save(goods);
    }

    @Override
    public Optional<Goods> get(Long id) {
        return this.goodRepository.findById(id);
    }

    @Override
    public Goods update(Optional<Goods> goods) {
        return this.goodRepository.save(goods.get());
    }

    @Override
    public void delete(Long id) {
        this.goodRepository.deleteById(id);
    }

    @Override
    public List<Goods> seachByConditions(String code, String name, int page) {
        Pageable pageable = PageRequest.of(page, 2);
        String sqlCode = null;
        String sqlName = null;
        if (code != null && code.trim().length() > 0) {
            sqlCode = "%" + code.trim() + "%";
        }
        if (name != null && name.trim().length() > 0) {
            sqlName = "%" + name.trim() + "%";
        }
        return goodRepository.searchByConditions(sqlCode, sqlName, pageable);
    }


    @Override
    public int numberOfRecord(String code, String name) {
        String sqlCode = null;
        String sqlName = null;
        if (code != null && code.trim().length() > 0) {
            sqlCode = "%" + code.trim() + "%";
        }
        if (name != null && name.trim().length() > 0) {
            sqlName = "%" + name.trim() + "%";
        }
        return this.goodRepository.numberOfRecord(sqlCode, sqlName);

    }

}

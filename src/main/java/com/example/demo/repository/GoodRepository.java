package com.example.demo.repository;

import com.example.demo.entity.Goods;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GoodRepository extends JpaRepository<Goods, Long> {

    @Query("Select goods from Goods goods where goods.code like :code OR goods.name like :name")
    public List<Goods> searchByConditions(@Param("code") String code, @Param("name") String name, Pageable pageable);

    @Query(value = "Select count(*) from Goods goods where goods.code like :code OR goods.name like :name", nativeQuery = true)
    public int numberOfRecord(@Param("code") String code, @Param("name") String name);

}

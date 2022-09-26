package com.netspi.interview.repository;

import com.netspi.interview.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("SELECT i FROM Item i ORDER BY i.status")
    public List<Item> findAllByOrderByStatus();

}

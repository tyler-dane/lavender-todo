package com.netspi.interview.repository;

import com.netspi.interview.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}

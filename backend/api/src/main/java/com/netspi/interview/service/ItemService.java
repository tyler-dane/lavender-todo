package com.netspi.interview.service;

import com.netspi.interview.controller.dto.request.CreateItemRequestDto;
import com.netspi.interview.model.Item;
import com.netspi.interview.repository.ItemRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class ItemService {

    private final EntityManager entityManager;

    private final ItemRepository itemRepository;

    public ItemService(EntityManager entityManager, ItemRepository itemRepository) {
        this.entityManager = entityManager;
        this.itemRepository = itemRepository;
    }

    public Item getItemById(Long id) {
        return this.itemRepository.findById(id).orElse(null);
    }

    public List<Item> getAllItems() {
        return this.itemRepository.findAll();
    }


    public void deleteAllItems() {
        this.itemRepository.deleteAll();
    }

    public void createItem(CreateItemRequestDto createItemRequestDto) {
        this.entityManager.createNativeQuery(String.format("insert into item (name) values ('%s')", createItemRequestDto.getName())).executeUpdate();
    }
}

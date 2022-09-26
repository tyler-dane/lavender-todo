package com.netspi.interview.controller;

import com.netspi.interview.controller.dto.request.CreateItemRequestDto;
import com.netspi.interview.controller.dto.request.UpdateItemRequestDto;
import com.netspi.interview.controller.dto.response.ItemResponseDto;
import com.netspi.interview.model.Item;
import com.netspi.interview.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {

        this.itemService = itemService;
    }

    @GetMapping("/ping")
    public ResponseEntity ping() {
        return new ResponseEntity("Hello world", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ItemResponseDto>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        List<ItemResponseDto> response = items.stream().map(ItemResponseDto::new).collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity deleteAllItems() {
        itemService.deleteAllItems();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDto> getItemById(@PathVariable("id") Long id) {
        Item item = itemService.getItemById(id);
        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ItemResponseDto(item), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ItemResponseDto> updateItemById(@PathVariable("id") Long id,
                                                          @RequestBody UpdateItemRequestDto updateItemRequestDto) {
        Item item = itemService.updateItemById(id, updateItemRequestDto);
        return new ResponseEntity<>(new ItemResponseDto(item), HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }

    @Transactional
    @PutMapping
    public ResponseEntity putItem(@RequestBody CreateItemRequestDto createItemRequestDto) {
        this.itemService.createItem(createItemRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

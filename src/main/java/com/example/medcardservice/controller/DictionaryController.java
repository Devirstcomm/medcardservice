package com.example.medcardservice.controller;

import com.example.medcardservice.model.Mkb10Entry;
import com.example.medcardservice.service.Mkb10Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

    private final Mkb10Service mkb10Service;

    @Autowired
    public DictionaryController(Mkb10Service mkb10Service) {
        this.mkb10Service = mkb10Service;
    }

    @GetMapping("/mkb10")
    public ResponseEntity<Set<Mkb10Entry>> getMkb10Dictionary() {
        return ResponseEntity.ok(mkb10Service.getAllMkb10Entries());
    }
}

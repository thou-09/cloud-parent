package com.itany.controllers;

import com.itany.dto.TypeDTO;
import com.itany.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TypeController.
 *
 * @author Thou
 * @date 2022/10/23
 */
@RestController
@RequestMapping("/types")
public class TypeController {

    @Autowired
    public TypeService typeService;

    @PostMapping("/list/{type}")
    public List<TypeDTO> listTypesByType(@PathVariable Integer type) {
        return typeService.listTypeByType(type);
    }
}

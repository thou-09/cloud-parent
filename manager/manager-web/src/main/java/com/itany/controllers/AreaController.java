package com.itany.controllers;

import com.itany.dto.AreaDTO;
import com.itany.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * AreaController.
 *
 * @author Thou
 * @date 2022/10/23
 */
@RestController
@RequestMapping("/areas")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @PostMapping("/list")
    public List<AreaDTO> listAreas() {
        return areaService.listAll();
    }
}

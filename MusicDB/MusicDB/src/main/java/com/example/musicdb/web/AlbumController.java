package com.example.musicdb.web;

import com.example.musicdb.model.binding.AddAlbumBindingModel;
import com.example.musicdb.model.service.AlbumServiceModel;
import com.example.musicdb.service.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;
    private final ModelMapper modelMapper;

    public AlbumController(AlbumService albumService, ModelMapper modelMapper) {
        this.albumService = albumService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add() {
        return "add-album";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid AddAlbumBindingModel addAlbumBindingModel, BindingResult bindingResult
    , RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addAlbumBindingModel", addAlbumBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addAlbumBindingModel", bindingResult);

            return "redirect:add";
        }

        albumService.addAlbum(modelMapper.map(addAlbumBindingModel, AlbumServiceModel.class));

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteAlbumById(@PathVariable Long id){

        albumService.deleteAlbumById(id);

        return "redirect:/";
    }

    @ModelAttribute
    public AddAlbumBindingModel addAlbumBindingModel() {
        return new AddAlbumBindingModel();
    }
}

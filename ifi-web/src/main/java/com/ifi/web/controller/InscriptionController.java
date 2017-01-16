package com.ifi.web.controller;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ifi.errors.UserAlreadyExistsException;
import com.ifi.service.UserService;
import com.ifi.web.dto.InscriptionDto;
import com.ifi.web.dto.ProfileDto;
import com.ifi.web.dto.UserDto;

@Controller
public class InscriptionController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value ="/inscription", method=RequestMethod.GET)
	public String goHome(final Model model, @ModelAttribute("profile") InscriptionDto dto) {
		model.addAttribute("profil", new InscriptionDto());
		return "inscription";
	}
	
	@Transactional
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addOrUpdateProfil(@ModelAttribute("profile") final InscriptionDto dto) throws UserAlreadyExistsException, Exception {
		UserDto user = new UserDto(dto.getUsername(), dto.getPassword());
		ProfileDto profil = new ModelMapper().map(dto, ProfileDto.class);
		user.setProfil(profil);
		userService.addUser(user);
		

		return "redirect:/my-profile";
	}

}
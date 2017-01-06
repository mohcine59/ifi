package com.ifi.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InscriptionController {

	@RequestMapping(value = { "/inscription" })
	public String goHome(final Model model) {
		return "inscription";
	}
}
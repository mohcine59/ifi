package com.ifi.web.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ifi.service.ProfilService;
import com.ifi.service.TweetService;
import com.ifi.web.Tools;
import com.ifi.web.dto.ProfileDto;
import com.ifi.web.dto.TweetDto;

@Controller
public class ProfileController {

	@Autowired
	private ProfilService profilService;

	@Autowired
	private TweetService tweetService;

	@Transactional
	@RequestMapping(value = "/add-update-profile", method = RequestMethod.POST)
	public String addOrUpdateProfil(@ModelAttribute("profile") final ProfileDto dto, final Principal principal) {
		dto.setUsername(principal.getName());
		final ProfileDto profil = this.profilService.getProfil(dto);

		if (profil == null) {
			this.profilService.addProfil(dto);
		} else {
			this.profilService.updateProfil(dto);
		}

		return "redirect:/my-profile";
	}

	@Transactional
	@RequestMapping(value = "/my-profile")
	public String myProfile(final Model model, final Principal principal) {
		if (principal != null) {
			final ProfileDto profilDto = new ProfileDto(principal.getName());
			final ProfileDto profile = this.profilService.getProfil(profilDto);
			if (profile != null) {
				model.addAttribute("profile", profile);
			} else {
				model.addAttribute("profile", profilDto);
			}

			return "my-profile";
		}
		return "redirect:/home";
	}

	@Transactional
	@RequestMapping(value = "/profile/{username}", method = RequestMethod.GET)
	public String getProfile(final Model model, @PathVariable final String username) {

		ProfileDto profile = this.profilService.getProfil(new ProfileDto(username));

		if ((profile == null) && Tools.isUserExist(username)) {
			profile = new ProfileDto(username);
		}

		if (profile != null) {
			model.addAttribute("profile", profile);

			final List<TweetDto> listTweets = this.tweetService.getTweetsByUsername(username);
			Tools.replaceUserWithHTML(listTweets);
			model.addAttribute("listTweets", listTweets);

			return "profile";
		}

		return "redirect:/home";

	}

	@RequestMapping(value = "/upload-picture", method = RequestMethod.POST)
	public String uploadPicture(@RequestParam("file") final MultipartFile fileUpload, final Principal principal) {
		if (!fileUpload.isEmpty()) {
			final String[] ext = fileUpload.getOriginalFilename().split("\\.");
			final String filename = principal.getName() + "." + ext[ext.length - 1];
			final File file = new File("/Users/scof/Sites/img/" + filename);
			try {
				FileUtils.copyInputStreamToFile(fileUpload.getInputStream(), file);
			} catch (final IOException e) {
				e.printStackTrace();
			}

		}

		return "redirect:/my-profile";
	}

}
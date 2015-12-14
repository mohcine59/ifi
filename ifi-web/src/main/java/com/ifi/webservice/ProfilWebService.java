package com.ifi.webservice;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ifi.dao.ProfilDao;
import com.ifi.entity.Profile;
import com.ifi.web.dto.ProfileDto;

@Controller
@RequestMapping(value = "/profilService", produces = "application/json")
public class ProfilWebService {

	@Autowired
	ProfilDao profilDao;

	@RequestMapping(method = RequestMethod.GET, value = "/profil/{username}")
	@ResponseBody
	public ResponseEntity<ProfileDto> getProfil(@PathVariable final String username) {
		final Profile profil = this.profilDao.getProfil(new Profile(username));

		if (profil != null) {
			final ProfileDto dto = new ModelMapper().map(profil, ProfileDto.class);
			return new ResponseEntity<ProfileDto>(dto, HttpStatus.OK);
		}

		return null;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProfileDto> addProfil(@RequestBody final ProfileDto profilDto) {
		final Profile profil = this.profilDao.add(new Profile(profilDto));

		final ProfileDto dto = new ModelMapper().map(profil, ProfileDto.class);
		return new ResponseEntity<ProfileDto>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProfileDto> updateProfil(@RequestBody final ProfileDto profilDto) {
		final Profile profil = this.profilDao.update(new Profile(profilDto));

		final ProfileDto dto = new ModelMapper().map(profil, ProfileDto.class);
		return new ResponseEntity<ProfileDto>(dto, HttpStatus.OK);
	}

}
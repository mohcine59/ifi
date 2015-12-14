package com.ifi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifi.dao.ProfilDao;
import com.ifi.entity.Profile;
import com.ifi.service.ProfilService;
import com.ifi.web.dto.ProfileDto;

@Service
public class ProfilServiceImpl implements ProfilService {

	@Autowired
	ProfilDao profilDao;

	@Override
	public ProfileDto getProfil(final ProfileDto profilDto) {
		final Profile profil = this.profilDao.getProfil(new Profile(profilDto));

		if (profil != null) {
			return new ModelMapper().map(profil, ProfileDto.class);
		}

		return null;
	}

	@Override
	public ProfileDto addProfil(final ProfileDto profilDto) {
		final Profile profil = this.profilDao.add(new Profile(profilDto));

		return new ModelMapper().map(profil, ProfileDto.class);
	}

	@Override
	public ProfileDto updateProfil(final ProfileDto profilDto) {
		final Profile profil = this.profilDao.update(new Profile(profilDto));

		return new ModelMapper().map(profil, ProfileDto.class);
	}

	public void setProfilDao(final ProfilDao profilDao) {
		this.profilDao = profilDao;
	}

}
package com.ifi.service;

import com.ifi.web.dto.ProfileDto;

public interface ProfilService {

	public ProfileDto addProfil(ProfileDto profilDto);

	public ProfileDto updateProfil(ProfileDto profilDto);

	public ProfileDto getProfil(ProfileDto profilDto);

}
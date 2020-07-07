package dev.acs.auth.module.profile.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.acs.auth.core.exception.RowNotFound;
import dev.acs.auth.module.profile.dto.ProfileDTO;

public interface IProfileService {
	public ProfileDTO save(ProfileDTO userGroup) throws RowNotFound;
	public Page<ProfileDTO> getList(Pageable pageable);
	public ProfileDTO get(long id) throws RowNotFound;
}

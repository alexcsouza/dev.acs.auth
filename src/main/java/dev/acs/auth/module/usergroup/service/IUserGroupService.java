package dev.acs.auth.module.usergroup.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.acs.auth.core.exception.RowNotFound;
import dev.acs.auth.module.user.security.dto.UserGroupDTO;

public interface IUserGroupService {
	public UserGroupDTO save(UserGroupDTO userGroup) throws RowNotFound;
	public Page<UserGroupDTO> getList(Pageable pageable);
	public UserGroupDTO get(long id) throws RowNotFound;
}

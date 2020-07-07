package dev.acs.auth.module.usergroup.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.acs.auth.core.bean.ModelMapperBean;
import dev.acs.auth.core.exception.RowNotFound;
import dev.acs.auth.module.usergroup.dto.UserGroupDTO;
import dev.acs.auth.module.usergroup.persistence.IUserGroupRepository;
import dev.acs.auth.module.usergroup.persistence.UserGroup;

@Service
public class UserGroupService implements IUserGroupService{
	
	@Autowired
	private ModelMapperBean modelMapperBean;
	
	@Autowired
	private IUserGroupRepository userGroupRepository;
	
	@Override
	public UserGroupDTO save(UserGroupDTO userGroupDTO) throws RowNotFound {
		UserGroup userGroup;
		if(userGroupDTO.getId() != null) {
			userGroup = userGroupRepository.findById(userGroupDTO.getId()).orElseThrow(() -> new RowNotFound("User group not found"));
			userGroup = modelMapperBean.getModelMapper().map(userGroup, UserGroup.class);
		}else {
			userGroup = modelMapperBean.getModelMapper().map(userGroupDTO, UserGroup.class);
		}
		userGroupRepository.save(userGroup);
		userGroupDTO.setId(userGroup.getId());
		return userGroupDTO;
	}

	@Override
	public Page<UserGroupDTO> getList(Pageable pageable) {
		Page<UserGroup> list = userGroupRepository.findAll(pageable);
		List<UserGroupDTO> dtoList = list.stream().map(userGroup->modelMapperBean.getModelMapper().map(userGroup, UserGroupDTO.class)).collect(Collectors.toList());
		return new PageImpl<>(dtoList, pageable, list.getTotalElements());  
	}

	@Override
	public UserGroupDTO get(long id) throws RowNotFound {
		UserGroup userGroup = userGroupRepository.findById(id).orElseThrow(() -> new RowNotFound("User group not found"));
		return modelMapperBean.getModelMapper().map(userGroup, UserGroupDTO.class);
	}

}

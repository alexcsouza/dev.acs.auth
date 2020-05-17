package dev.acs.auth.module.usergroup.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface IUserGroupRepository  extends PagingAndSortingRepository<UserGroup, Long> {

}

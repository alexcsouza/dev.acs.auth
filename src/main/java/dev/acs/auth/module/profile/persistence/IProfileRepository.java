package dev.acs.auth.module.profile.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface IProfileRepository extends PagingAndSortingRepository<Profile, Long> {

}

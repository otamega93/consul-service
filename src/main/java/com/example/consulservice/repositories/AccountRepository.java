package com.example.consulservice.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.consulservice.entities.Account;

@RepositoryRestResource
public interface AccountRepository extends PagingAndSortingRepository<Account, Long>{

	public Account findByUsername (@Param(value = "username") String username);

	public Account findByName (@Param(value = "name") String name);
	
}
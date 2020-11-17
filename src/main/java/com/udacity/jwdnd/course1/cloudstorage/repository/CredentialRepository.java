package com.udacity.jwdnd.course1.cloudstorage.repository;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CredentialRepository extends CrudRepository<Credential,Long> {
    @Query("select new Credential(id,url,username,password,encodedKey,userId) from Credential where userId= ?1")
    public List<Credential> getCredentialsByUserId(Long userId);

}

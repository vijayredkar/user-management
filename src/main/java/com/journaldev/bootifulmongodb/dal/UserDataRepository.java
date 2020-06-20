package com.journaldev.bootifulmongodb.dal;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.journaldev.bootifulmongodb.model.User;
import com.journaldev.bootifulmongodb.model.UserData;

@Repository
public interface UserDataRepository extends MongoRepository<UserData, String> {
	
	List<UserData> findByType(String type);

	/*
	 * @Query("{ state : 'TYPE' }")
	 * 
	 * Page<UserData> findByType(Pageable pageable);
	 */	
}

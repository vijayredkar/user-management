package com.journaldev.bootifulmongodb.dal;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.journaldev.bootifulmongodb.model.User;
import com.journaldev.bootifulmongodb.model.UserData;

@Repository
public interface UserDataRepository extends MongoRepository<UserData, String> {
}

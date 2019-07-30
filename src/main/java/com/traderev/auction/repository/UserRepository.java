package com.traderev.auction.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.traderev.auction.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}

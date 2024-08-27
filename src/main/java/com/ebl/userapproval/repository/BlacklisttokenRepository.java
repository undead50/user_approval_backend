package com.ebl.userapproval.repository;


import com.ebl.userapproval.model.Blacklisttoken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlacklisttokenRepository extends CrudRepository<Blacklisttoken,Long> {

    @Query(value = "SELECT COUNT(b) FROM blacklisted_token b WHERE b.token = :token and created_at = (select current_date);", nativeQuery = true)
    int findBlacklisttokenByToken(@Param("token") String token);

}

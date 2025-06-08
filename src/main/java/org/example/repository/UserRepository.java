package org.example.repository;

import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    @Modifying
    @Query(value = "UPDATE users SET status = :status WHERE name = :name", nativeQuery = true)
    int updateUserSetStatusForNameNativePostgres(@Param("status") String status, @Param("name") String name);
}
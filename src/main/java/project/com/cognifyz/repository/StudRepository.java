package project.com.cognifyz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.com.cognifyz.model.UserEntity;

@Repository
public interface StudRepository extends JpaRepository<UserEntity,Long> {

}

package whu.edu.assignment4.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whu.edu.assignment4.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
}

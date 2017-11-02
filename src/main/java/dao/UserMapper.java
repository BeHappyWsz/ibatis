package dao;

import domain.User;

import java.util.List;

public interface UserMapper {

	public User findById(Long id);

	public List<User> findAll();

	public int insertUser(User user);

	public void batchInsert(List<User> list);

	public  User findByName(String name);

}

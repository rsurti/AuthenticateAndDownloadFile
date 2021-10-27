package com.cbnits.authapp.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbnits.authapp.model.UserData;
import com.cbnits.authapp.repository.UserRepository;

/**
 * @author cbnits-154
 *
 */
@Service
public class UserService {

	@Autowired
	UserRepository userrepository;

	public UserData registerUser(String username) {
		UserData user = null;
		try {
			List<UserData> users = getUser(username);
			if (users != null && users.size() > 0) {
				System.out.println("assigning user:" + users.get(0));
				user = users.get(0);
			} else if (user == null || user.getUsername() == "") {
				String key = UUID.randomUUID().toString();
				user = new UserData(username, key);
				user = userrepository.save(user);
			}
		} catch (Exception ee) {
			System.out.println("{registerUser} :" + ee.getMessage());
		}
		return user;
	}

	public List<UserData> getUser(String username) {
		System.out.println("finding user with name:" + username);
		try {
			return userrepository.findByUsername(username);
		} catch (Exception ee) {
			System.out.println("{getUser} :" + ee.getMessage());
		}
		return null;
	}

	public boolean matchKey(String key, String username) {
		try {
			UserData user = getUser(username).get(0);
			if (user.getKey().equals(key)) {
				return true;
			}
		} catch (Exception ee) {
			System.out.println("{matchKey} :" + ee.getMessage());
		}
		return false;

	}

}

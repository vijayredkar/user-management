package com.journaldev.bootifulmongodb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.journaldev.bootifulmongodb.dal.UserDAL;
import com.journaldev.bootifulmongodb.dal.UserDataRepository;
import com.journaldev.bootifulmongodb.dal.UserRepository;
import com.journaldev.bootifulmongodb.model.Attributes;
import com.journaldev.bootifulmongodb.model.User;
import com.journaldev.bootifulmongodb.model.UserData;

@RestController
@RequestMapping(value = "/")
public class UserController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final UserRepository userRepository;
	private final UserDataRepository userDataRepository;
	

	private final UserDAL userDAL;

	public UserController(UserRepository userRepository, UserDAL userDAL, UserDataRepository userDataRepository) {
		this.userRepository = userRepository;
		this.userDAL = userDAL;
		this.userDataRepository = userDataRepository;
	}

	/*
	 * @RequestMapping(value = "/create", method = RequestMethod.POST) public User
	 * addNewUsers(@RequestBody User user) {
	 * LOG.info("------------------Saving user."); return userRepository.save(user);
	 * }
	 */
	
	
	/*
	 * @RequestMapping(value = "/createUserData", method = RequestMethod.POST)
	 * public UserData addNewUsers(@RequestBody UserData userData) {
	 * LOG.info("------------------Create userData.");
	 * 
	 * UUID uuid = UUID.randomUUID();
	 * 
	 * LOG.info("------------------Create userData addNewUsers   uuid  {}", uuid);
	 * userData.setUserId(uuid);
	 * 
	 * 
	 * return userDataRepository.save(userData); }
	 */
	
	
	@RequestMapping(value = "/v1/users/user", method = RequestMethod.POST)
	public UserData createUser(@RequestBody UserData userData) {
		LOG.info("------------------Create userData addNewUsers1.");
		
		String randomId  = "id_"+System.currentTimeMillis();
		LOG.info("------------------Create userData addNewUsers1   id  {}", randomId);
		userData.setId(randomId);
		
		UUID uuid = UUID.randomUUID();
		LOG.info("------------------Create userData addNewUsers1   uuid  {}", uuid);
		userData.setUserId(uuid);		
		return userDataRepository.save(userData);
	}
	
	@PutMapping(value = "/v1/users/user/{id}")
	public UserData updateUser(@RequestBody UserData userData, @PathVariable String id) {
		LOG.info("------------------Update userData id {} ", id);
		
		UserData user = userDataRepository.findOne(id);
		
		if(Objects.isNull(user))
		{
			LOG.info("------------------No Data Found for user with id {} ", id);
			return new UserData();			
		}
		
		LOG.info("------------------Fetched userData for id  {}", id);
		
		Attributes attributes = new Attributes();
		attributes.setAddress(userData.getAttributes().getAddress());
		attributes.setCountry(userData.getAttributes().getCountry());
		attributes.setDocument(userData.getAttributes().getDocument());
		attributes.setEmail(userData.getAttributes().getEmail());
		attributes.setIdentityNumber(userData.getAttributes().getIdentityNumber());
		attributes.setMobilePhone(userData.getAttributes().getMobilePhone());
		attributes.setPostCode(userData.getAttributes().getPostCode());
		attributes.setState(userData.getAttributes().getState());
		
		user.setAttributes(attributes);		
		user.setType(user.getType());
		
		LOG.info("------------------Updated userData for id  {}", id);	
		
		return userDataRepository.save(user);
	}
	
	/*
	 * @DeleteMapping(value = "/{userId}") public void deleteUser(@PathVariable
	 * String userId) {
	 * 
	 * LOG.info("------------------Delete User   userId  {}", userId);
	 * userDataRepository.delete(userId); }
	 * 
	 * @DeleteMapping(value = "/") public void deleteUsersAll() {
	 * 
	 * LOG.info("------------------Delete All Users");
	 * userDataRepository.deleteAll(); }
	 */
	
	
	@DeleteMapping(value = "/v1/users/user/{id}")
	public void deleteUser(@PathVariable String id) {
		
		LOG.info("------------------Delete User  id  {}", id);
		
		UserData user = userDataRepository.findOne(id);
		
		if(Objects.isNull(user))
		{
			LOG.info("------------------No Data Found for user with id {} ", id);
			return;			
		}
		
		LOG.info("------------------Fetched userData for id  {}", id);
		userDataRepository.delete(id);
		LOG.info("------------------Deleted userData for id  {}", id);
	}
	
	@DeleteMapping(value = "/v1/users/user")
	public void deleteUsersAll() {
		
		LOG.info("------------------Delete All Users");		
		userDataRepository.deleteAll();
	}
	
	
	
	/*
	 * @RequestMapping(value = "", method = RequestMethod.GET) public List<UserData>
	 * getAllUsers() { LOG.info("------------------Getting all users."); return
	 * userDataRepository.findAll(); }
	 * 
	 * @RequestMapping(value = "/{userId}", method = RequestMethod.GET) public
	 * UserData getUser(@PathVariable String userId) {
	 * LOG.info("------------------Getting user with ID: {}.", userId); return
	 * userDataRepository.findOne(userId); }
	 */
	
	
	@RequestMapping(value = "/v1/users/user", method = RequestMethod.GET)
	public List<UserData> getAllUsers() {
		LOG.info("------------------Getting all users.");
		return userDataRepository.findAll();
	}

	@RequestMapping(value = "/v1/users/user/{id}", method = RequestMethod.GET)
	public UserData getUser(@PathVariable String id) {
		LOG.info("------------------Getting user with id: {}.", id);
		return userDataRepository.findOne(id);
	}
	
	@GetMapping(value = "/v1/users/category/{catgname}")
	public List<UserData> getUserByCategory(@PathVariable String catgname) {
		LOG.info("------------------Getting users with category : {}.", catgname);
		
		 List<UserData> userData = new ArrayList<UserData>();
		 
		 userData = userDataRepository.findByType(catgname);
		
		
		/*
		 * PageRequest request = new PageRequest(0, 10, new Sort(Direction.DESC,
		 * "type")); List<UserData> userData =
		 * userDataRepository.findByType(request).getContent();
		 */ 
		
		 
		 List<UserData> userDataFinal = userData.stream().limit(100).collect(Collectors.toList());
		 
		 
		LOG.info("------------------Getting users with category - limit to 100 records : {}", userData);
		return userDataFinal;
	}
	
	
	
	
	
	
	

	/*
	 * @RequestMapping(value = "", method = RequestMethod.GET) public List<User>
	 * getAllUsers() { LOG.info("------------------Getting all users."); return
	 * userRepository.findAll(); }
	 * 
	 * @RequestMapping(value = "/{userId}", method = RequestMethod.GET) public User
	 * getUser(@PathVariable String userId) {
	 * LOG.info("------------------Getting user with ID: {}.", userId); return
	 * userRepository.findOne(userId); }
	 */

	// @RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
	// public Object getAllUserSettings(@PathVariable String userId) {
	// User user = userRepository.findOne(userId);
	// if (user != null) {
	// return user.getUserSettings();
	// } else {
	// return "User not found.";
	// }
	// }

	@RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
	public Object getAllUserSettings(@PathVariable String userId) {
		User user = userRepository.findOne(userId);
		if (user != null) {
			return userDAL.getAllUserSettings(userId);
		} else {
			return "User not found.";
		}
	}

	// @RequestMapping(value = "/settings/{userId}/{key}", method =
	// RequestMethod.GET)
	// public String getUserSetting(@PathVariable String userId, @PathVariable
	// String key) {
	// //User user = userRepository.findOne(userId);
	// String setting = userDAL.getUserSetting(userId, key);
	// LOG.info("------------------Setting = "+setting);
	// if (setting != null) {
	// return setting;
	// } else {
	// return "Setting not found.";
	// }
	// }

	@RequestMapping(value = "/settings/{userId}/{key}", method = RequestMethod.GET)
	public String getUserSetting(@PathVariable String userId, @PathVariable String key) {
		return userDAL.getUserSetting(userId, key);
	}

	@RequestMapping(value = "/settings/{userId}/{key}/{value}", method = RequestMethod.GET)
	public String addUserSetting(@PathVariable String userId, @PathVariable String key, @PathVariable String value) {
		User user = userRepository.findOne(userId);
		if (user != null) {
			user.getUserSettings().put(key, value);
			userRepository.save(user);
			return "Key added";
		} else {
			return "User not found.";
		}
	}
}
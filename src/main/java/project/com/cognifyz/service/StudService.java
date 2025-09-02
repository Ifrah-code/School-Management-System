package project.com.cognifyz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import project.com.cognifyz.model.UserEntity;
import project.com.cognifyz.queuetask.BackgroundProcessor;

import project.com.cognifyz.repository.StudRepository;

@Service
public class StudService {
	
	@Autowired
	private StudRepository studRepository;
	
	
	 @Autowired
	    private BackgroundProcessor backgroundJobService;
	
	//getting all the userssss
	 @Cacheable("students")
	public List<UserEntity> getAllUsers()
	{
		 System.out.println("Fetching data from DB...");
		return studRepository.findAll();
	}
	
	
	public Optional<UserEntity> getUserById(Long id){
		
		return studRepository.findById(id);
	}
	
	public UserEntity saveUser(UserEntity user) {
	    UserEntity savedUser = studRepository.save(user); // ✅ Save first
	   backgroundJobService.enqueueUser(savedUser);  
	    return savedUser;
	}

	
	public UserEntity updateUser(Long id, UserEntity userDetails) {
		UserEntity user = studRepository.findById(id).orElseThrow();
		user.setFirstname(userDetails.getFirstname());
		user.setLastname(userDetails.getLastname());
		user.setEmail(userDetails.getEmail());
		user.setAge(userDetails.getAge());
		user.setGender(userDetails.getGender());
		return studRepository.save(user);
	}
	
	 public void deleteUser(Long id) {
	        studRepository.deleteById(id);
	    }
	
	

}

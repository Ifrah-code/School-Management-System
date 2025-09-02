package project.com.cognifyz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import project.com.cognifyz.model.UserEntity;
import project.com.cognifyz.queuetask.BackgroundProcessor;
import project.com.cognifyz.service.StudService;

@Controller
@RequestMapping("/api/users")
public class StudController {
	
	    @Autowired
	    private StudService studService;
	    
	    @Autowired
	    private BackgroundProcessor backgroundJobService;
	    

	    @GetMapping("/")
      	public String homeRedirect() {
      	    return "redirect:/Home.html";
      	}
          
         
	    @PostMapping("/save")
	    public String saveUser(@ModelAttribute UserEntity user) {
	    	studService.saveUser(user);
            backgroundJobService.runAsyncJob(user.getFirstname());
            backgroundJobService.runAsyncJob(user.getLastname());
              return "redirect:/success.html";
          }

	    @GetMapping
	    @ResponseBody
	    public List<UserEntity> getUsers() {
	        return studService.getAllUsers();
	    }

	    @PutMapping("/{id}")
	    @ResponseBody
	    public UserEntity updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
	        return studService.updateUser(id, user);
	    }

	    @DeleteMapping("/{id}")
	    @ResponseBody
	    public void deleteUser(@PathVariable Long id) {
	        studService.deleteUser(id);
	    }
	}




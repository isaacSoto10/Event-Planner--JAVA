package controllers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.text.DateFormat;  
import java.util.Date; 
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Random;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@SpringBootApplication
@Controller 
public class HomeController {
	 @RequestMapping(value="/registration", method=RequestMethod.POST)
	    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
	    	userValidator.validate(user, result);
	    	if(result.hasErrors()) {
	    		return "index.jsp";
	    	}
	    	User u = userService.registerUser(user);
	    	session.setAttribute("userId", u.getId());
	    	return "redirect:/home";
	    }
	    
	    @RequestMapping(value="/login", method=RequestMethod.POST)
	    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
	    	boolean isAuthenticated = userService.authenticateUser(email, password);
	    	if(isAuthenticated) {
	    		User u = userService.findByEmail(email);
	    		session.setAttribute("userId", u.getId());
	    		return "redirect:/home";
	    	}else {
	    		model.addAttribute("user", new User());
	    		model.addAttribute("error", "Invalid Credentials. Please try again.");
	    		return "index.jsp";
	    	}
	    }
}

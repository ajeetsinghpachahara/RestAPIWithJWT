package com.ajeet.jwtcontroller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ajeet.jwtmodel.Employee;
import com.ajeet.jwtmodel.JwtUserDetails;

@RestController
@RequestMapping("/rest")
public class HelloController {

    @RequestMapping( value = { "/hello" }, method = RequestMethod.GET)
    public String hello(HttpServletRequest request) {
    	 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         JwtUserDetails userDetails = (JwtUserDetails)auth.getPrincipal(); 
        return "Hello "+userDetails.getUserName() ;
    }
    
    @RequestMapping(value= "/findByName/{name}", method = RequestMethod.GET)
   	@ResponseBody
   	public Employee findByName(@PathVariable("name") final String name){
   		return new Employee("E1", name);
   	}
}

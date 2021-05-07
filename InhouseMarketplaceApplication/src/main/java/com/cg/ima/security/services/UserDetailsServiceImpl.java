package com.cg.ima.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cg.ima.entities.Employee;
import com.cg.ima.repository.IEmployeeRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IEmployeeRepository employeeRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Employee employee = employeeRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Employee Not Found with username: " + username));

		return UserDetailsImpl.build(employee);

	}

}

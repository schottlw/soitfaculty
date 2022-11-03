package com.soit.soitfaculty.service;

import com.soit.soitfaculty.entity.Faculty;
import java.util.List;

public interface FacultyService {
	
	public List<Faculty> findAll();
	
	public Faculty findById(int theId);
	
	public void save(Faculty theFaculty);
	
	public void deleteById(int theId);
	
}

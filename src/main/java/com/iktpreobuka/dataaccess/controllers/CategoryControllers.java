package com.iktpreobuka.dataaccess.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.dataaccess.entities.CategoryEntity;
import com.iktpreobuka.dataaccess.repositories.CategoryRepository;

@RestController
@RequestMapping("/project/categories")
public class CategoryControllers {
	@Autowired
	CategoryRepository categoryRepository;

	@RequestMapping(method = RequestMethod.POST)
	public CategoryEntity createCategory(@RequestBody CategoryEntity newCat) {
		return categoryRepository.save(newCat);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<CategoryEntity> getAllCat() {
		return (List<CategoryEntity>) categoryRepository.findAll();

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{categoryId}")
	public CategoryEntity changeCat(@RequestBody CategoryEntity cat, @PathVariable Integer categoryId) {
		if (!categoryRepository.existsById(categoryId))
			return null;
		CategoryEntity catFound = categoryRepository.findById(categoryId).get();
		if (cat.getCategoryName() != null) {
			catFound.setCategoryName(cat.getCategoryName());
		}
		if (cat.getCategoryDescription() != null) {
			catFound.setCategoryDescription(cat.getCategoryDescription());
		}

		return categoryRepository.save(catFound);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{categoryId}")
	public CategoryEntity deleteCat(@PathVariable Integer categoryId) {
		CategoryEntity delCat= categoryRepository.findById(categoryId).get();
		categoryRepository.delete(delCat);
		return delCat;
	}
	@RequestMapping(method=RequestMethod.GET,value="/by-id/{categoryId}")
	public CategoryEntity getOneCat(@PathVariable Integer categoryId ) {
		return categoryRepository.findById(categoryId).get();
	}
}

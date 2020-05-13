package com.cg.hcs.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hcs.dao.TestManagementDao;
import com.cg.hcs.entity.TestEntity;
import com.cg.hcs.exception.TestManagementException;
import com.cg.hcs.model.TestModel;



@Service
public class TestServiceImpl implements TestManagementService {
	
	@Autowired
	private  TestManagementDao testrepo;

	
	
	private TestEntity of(TestModel source) {
		TestEntity result =null;
		if(source != null)
		{
			result=new TestEntity();
//		result.setTestAttributes(source.getTestAttributes());
			result.setTestId(source.getTestId());
			result.setTestName(source.getTestName());
			
		}
		return result;
	}
	
	private TestModel of(TestEntity source) {
		TestModel result =null;
		if(source != null)
		{
			result=new TestModel();
//			result.setTestAttributes(source.getTestAttributes());
			result.setTestId(source.getTestId());
			result.setTestName(source.getTestName());
			
		}
		return result;
	}
	
	
	
	@Override
	public TestModel add(TestModel test) throws TestManagementException {
	
			test=of(testrepo.save(of(test)));
			return test;
		}
	

		
		
	@Override
	public TestModel findById(Long testId) {
		return of(testrepo.findById(testId).orElse(null));
	}

	
	
	
	@Override
	public void deleteById(Long testId) throws TestManagementException {
				
			testrepo.deleteById(testId);	
		}

	@Override
	public List<TestModel> findAll() {
		
		
		 List<TestEntity> list= (List<TestEntity>) testrepo.findAll();
		 return list.stream().map(entity ->of(entity)).collect(Collectors.toList());
		  
	}
	

	}

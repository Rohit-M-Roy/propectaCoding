package com.example.propectaCoding.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.propectaCoding.Exception.DataException;
import com.example.propectaCoding.model.Data;
import com.example.propectaCoding.model.Entry;
import com.example.propectaCoding.model.ResultDTO;
import com.example.propectaCoding.repository.EntryRepository;

@Service
public class EntryServiceImpl {
	
	@Autowired
	EntryRepository erepo;
	
	
	public List<ResultDTO> showentries(Data data,String category)throws DataException{
		
		if(data == null) {
			throw new DataException("No Data Found");
		}
		
		List<ResultDTO> entryList = new ArrayList<>();
		
		for(Entry e : data.getEntries()) {
			
		 String categories = e.getCategory().toLowerCase();
		 String[] splitCategory = categories.split(" ");
		 String checker = splitCategory[0];
		 
		 if(checker.equals(category.toLowerCase())) {
			 
			 ResultDTO resultEntry = new ResultDTO();
			 resultEntry.setTitle(e.getApi());
			 resultEntry.setDescription(e.getDescription());
			 entryList.add(resultEntry);
		 }
			
		}
		
		return entryList;
		
	}
	
	public List<Entry> saveEntries(Data data)throws DataException{
		
		if(data == null) {
			throw new DataException("No Data Found");
		}
		
		for(Entry e : data.getEntries()) {
			
			erepo.save(e);
			
		}
	
		
		return data.getEntries();
		
	}

}

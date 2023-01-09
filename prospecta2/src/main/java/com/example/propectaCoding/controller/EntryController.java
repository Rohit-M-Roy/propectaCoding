package com.example.propectaCoding.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.propectaCoding.Exception.DataException;
import com.example.propectaCoding.model.CSVData;
import com.example.propectaCoding.model.Data;
import com.example.propectaCoding.model.Entry;
import com.example.propectaCoding.model.ResultDTO;
import com.example.propectaCoding.service.DataService;
import com.example.propectaCoding.service.EntryServiceImpl;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class EntryController {
	
	@Autowired
	EntryServiceImpl eserv;
	
	@Autowired
	RestTemplate template;
	
	@Autowired
	DataService dserv;
	
	
	
	
	@GetMapping("/entries/{category}")
	public ResponseEntity<List<ResultDTO>> listCustomEntry(@PathVariable("category") String category) throws DataException{
		
		Data data = template.getForObject("https://api.publicapis.org/entries", Data.class);
		List<ResultDTO> result = eserv.showentries(data,category);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@PostMapping("/entries")
	public ResponseEntity<List<Entry>> saveEntry()throws DataException{
		
		Data data = template.getForObject("https://api.publicapis.org/entries", Data.class);
		List<Entry> result = eserv.saveEntries(data);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@PostMapping("/csvConsume")
	public void getCsvData() throws IOException{
		
		List<CSVData> csvData = new ArrayList<>();
		String line = "";
		
		try(BufferedReader br = new BufferedReader(new FileReader("src/main/resources/SampleCSV.csv"))){
			
			List<CSVData> list = new ArrayList<>();
			while((line=br.readLine())!=null) {
				
				String[] data = line.split(",");
				CSVData model = new CSVData();
				model.setA(Integer.parseInt(data[0]));
				model.setB(Integer.parseInt(data[1]));
				model.setC(Integer.parseInt(data[2]));
				list.add(model);
			}
			
			dserv.saveCSVData(list);
			
		}
		catch(IOException ioe) {
			System.out.println("File Not Found");
		}
		
	}
	
	@GetMapping("/exportcsv")
	  public void exportIntoCSV(HttpServletResponse response) throws IOException {
	    response.setContentType("text/csv");
	    response.addHeader("Content-Disposition", "attachment; filename=\"student.csv\"");
	    dserv.putdataIntoCSV(response.getWriter());
	  }
	

}

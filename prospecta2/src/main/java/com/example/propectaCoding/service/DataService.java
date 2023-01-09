package com.example.propectaCoding.service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.propectaCoding.model.CSVData;
import com.example.propectaCoding.repository.CSVDataRepo;

@Service
public class DataService {
	
	@Autowired
	CSVDataRepo crepo;
	
	public void saveCSVData(List<CSVData> data) {
		
		for(CSVData e : data) {
			crepo.save(e);
		}
		
	}
	
	public void putdataIntoCSV(Writer writer) {
		
		List<CSVData> list = crepo.findAll();
		
		try {
			CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
		
			for (CSVData e : list) {
					printer.printRecord(e.getA(),e.getB(),e.getC());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

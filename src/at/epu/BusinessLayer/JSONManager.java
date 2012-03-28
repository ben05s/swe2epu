package at.epu.BusinessLayer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.*;

public class JSONManager {
	class TimeTable {
		String projectName;
		ArrayList<Integer> times;
		
		TimeTable() {
			times = new ArrayList<Integer>();
		}
		
		public int getTotal() {
			Integer total = 0;
			
			for(Integer time : times) {
				total += time;
			}
			
			return total;
		}
	}
	
	public void writeRandomTimeTableToFile(String filepath) {
		TimeTable table = new TimeTable();
		
		table.projectName = "Gutes Projekt";
		
		for(int i = 0; i < 10; i++) {
			table.times.add((int)(Math.random()*1000));
		}
		
		String output = getGson().toJson(table);
		
		FileWriter writer = null;
		try {
			writer = new FileWriter(filepath);
			writer.write(output);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getTimeTotalFromFile(String filepath) {
		StringBuilder builder = new StringBuilder();
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filepath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			String line = null;
			
			while((line = reader.readLine()) != null) {
				builder.append( line );
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		TimeTable table = getGson().fromJson(builder.toString(), TimeTable.class);
		
		return table.getTotal();
	}
	
	private Gson getGson() {
		return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
	}
}

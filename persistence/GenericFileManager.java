/*
* 
*
* this file replaces the multiple file managers with one generic class that can handle any object
* implementing CsvPersistable. It has load objects from CSV files, save objects to CSV files.
*
*/
package persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import clientPackage.Client;
import interfaces.CsvPersistable;
import travelPackage;

public class GenericFileManager {

  //save a list of objects into a csv file
  public static <T extends CsvPersistable> void save(List<T> items, String filepath) throws IOException {

    PrintWriter pw = new PrintWriter(new FileWriter(filepath));

    //write each object into csv file
    for (T item : items){
      pw.println(item.toCsvRow());
    }
    pw.close();
  }

  //load objects from csv file and returns a list of objects
  public static <T extends CsvPersistable> List<T> load(String filepath, Class<T> clazz) throws IOException {

    List<T> items = new ArrayList<>();
    BufferedReader br = new BufferedReader(new FileReader(filepath));

    String line;

    while ((line = br.readLine()) != null){

      try {
        //convert csv line into object
        Object obj = parseLine(line, clazz)
       //cast object to the correct type
        if (clazz.isInstance(obj)){
          items.add(clazz.cast(obj));
        }
        
      } catch (IOException e){
        //log invalid lines
        ErrorLogger.log("Invalid line: "+ line + "|" + e.getMessage()); 
      }
    }
    br.close();
    return items;
  }
//this determines which specific class of fromCsvRow() method to call based on the class type passed to load()
  //it returns the object created from CSV line
  private static Object parseLine(String line, Class<?> clazz) throws Exception{

    //client
    if (clazz == Client.class){
      return Client.fromCsvRow(line);
    }
    //accommodation types
    else if (clazz == Hotel.class){
      return Hotel.fromCsvRow(line);
    }
    else if (clazz == Hostel.class){
      return Hostel.fromCsvRow(line);
    }

    //transportation types
    else if (clazz == Flight.class){
      return Flight.fromCsvRow(line);
    }
    else if (clazz == Train.class){
      return Train.fromCsvRow(line);
    }
    else if (clazz == Bus.class){
      return Bus.fromCsvRow(line);
    }
    
    return null;
  }

  
}


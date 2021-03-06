package DataLayer;

import StratElements.Strategy;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class JSONManager extends TextFileManager{
    /**
     * default constructor
     * @param folderPath - the path to the folder that contains the saved strats
     */
    public JSONManager(String folderPath){
        this.folderPath = folderPath;
        fileSuffix = ".json";
    }

    /**
     * gets the list of JSON file names in the given directory (with file extensions stripped)
     * @return - a list containing all the names of JSON files in the given directory
     */
    public List<String> getFileList(){
        File baseDirectory = new File(folderPath);
        File[] matches = baseDirectory.listFiles(new FilenameFilter(){
            public boolean accept(File dir, String name){
                return name.endsWith(fileSuffix);
            }
        });
        List<String> stratNames = new ArrayList<String>();
        for(int i = 0; i < matches.length; i++){
            stratNames.add(matches[i].getName());
        }
        //strip off the end of the filename to make them look nicer
        stratNames = stratNames.stream().map(s -> {
            return s.replace(fileSuffix, "");
        }).collect(Collectors.toList());

        return stratNames;
    }

    /**
     * gets a JSONObject built from the file in the base directory that matches the given stratName
     * @param stratName - the name of the JSON file to build the JSONObject from (sans file extension)
     * @return - the JSONObject built from the chosen file
     */
    public JSONObject getStrat(String stratName){
        return new JSONObject(getFileContents(stratName));
    }

    /**
     * saves the given strategy under the given name
     * @param strat - the Strategy object to be serialized and saved
     * @param name - the name to give to the file containing the saved strategy
     */
    public void saveStrat(Strategy strat, String name){
        strat.serialize();
        saveFile(name, strat.getRoot().toString(2));
    }
}

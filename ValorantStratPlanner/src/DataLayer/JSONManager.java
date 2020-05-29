package DataLayer;

import StratElements.Strategy;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JSONManager {
    private String folderPath;
    private final String fileSuffix = ".json";

    /**
     * default constructor
     * @param folderPath - the path to the folder that contains the saved strats
     */
    public JSONManager(String folderPath){
        this.folderPath = folderPath;
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
        ArrayList<String> stratNames = new ArrayList<String>();
        for(int i = 0; i < matches.length; i++){
            stratNames.add(matches[i].getName());
        }
        //strip off the end of the filename to make them look nicer
        stratNames.stream().map(s -> {
            return s.replace(fileSuffix, "");
        });

        return stratNames;
    }

    /**
     * gets a JSONObject built from the file in the base directory that matches the given stratName
     * @param stratName - the name of the JSON file to build the JSONObject from (sans file extension)
     * @return - the JSONObject built from the chosen file
     */
    public JSONObject getStrat(String stratName){
        try {
            Scanner scan = new Scanner(new File(getFilePath(stratName)));
            StringBuilder sb = new StringBuilder();
            while(scan.hasNext()){
                sb.append(scan.nextLine());
            }

            return new JSONObject(sb.toString());
        } catch(FileNotFoundException e){
            System.out.println("Unable to load saved strategy with name " + stratName);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * saves the given strategy under the given name
     * @param strat - the Strategy object to be serialized and saved
     * @param name - the name to give to the file containing the saved strategy
     */
    public void saveStrat(Strategy strat, String name){
        try{
            FileWriter fw = new FileWriter(getFilePath(name));
            strat.serialize();
            fw.write(strat.getRoot().toString(2));
            fw.close();
        } catch(IOException e){
            System.out.println("Unable to save strategy with name " + name);
            e.printStackTrace();
        }
    }

    /**
     * helper method that returns a sanitized file paths to avoid doubling up on path separators or file extensions
     * @param stratName - the name of the strategy to generate the path for
     * @return - the sanitized file path
     */
    private String getFilePath(String stratName){
        return folderPath + (folderPath.endsWith("/") ? "" : "/") + stratName + (stratName.endsWith(fileSuffix) ? "" : fileSuffix);
    }
}

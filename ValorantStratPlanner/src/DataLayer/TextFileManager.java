package DataLayer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public abstract class TextFileManager {
    protected String folderPath;
    protected String fileSuffix = ".txt";

    /**
     * gets the contents of a file in the set directory
     * @param fileName - the name of the file to get the contents of (sans file extension)
     * @return - String containing the contents of the file
     */
    public String getFileContents(String fileName){
        try{
            Scanner scan = new Scanner(new File(getFilePath(fileName)));
            StringBuilder sb = new StringBuilder();
            while(scan.hasNext()){
                sb.append(scan.nextLine());
            }

            return sb.toString();
        } catch(IOException e){
            System.out.println("Unable to load text file " + fileName + fileSuffix);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * saves a text file in the set directory with the name fileName that contains the string contents
     * @param fileName - the name to save the file with (without the file extension)
     * @param contents - the contents to save to the file, as a String
     */
    public void saveFile(String fileName, String contents){
        try{
            FileWriter fw = new FileWriter(getFilePath(fileName));
            fw.write(contents);
            fw.close();
        } catch(IOException e){
            System.out.println("Unable to save file with name " + fileName);
            e.printStackTrace();
        }
    }

    /**
     * helper method that returns a sanitized file paths to avoid doubling up on path separators or file extensions
     * @param fileName - the name of the file to generate the path for
     * @return - the sanitized file path
     */
    protected String getFilePath(String fileName){
        return folderPath + (folderPath.endsWith("/") ? "" : "/") + fileName + (fileName.endsWith(fileSuffix) ? "" : fileSuffix);
    }
}

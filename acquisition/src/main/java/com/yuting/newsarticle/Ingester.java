package com.yuting.newsarticle;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import java.io.*;
import java.util.Scanner;
/**
 * Created by Ting on 4/27/17.
 */

public class Ingester {
    @Value(value="file:/src/main/resources/json/nyt1601.json")
//    /Users/Ting/dev/GraphVis/acquisition/src/main/resources/json/nyt1601.json
    private Resource data =
    FileReader fileReader = new FileReader("file:/WEB-INF/content/somecontent.txt");
    public String getData(){
        try {
            File file = data.getFile();
            String jsonData = this.jsonRead(file);
            return jsonData;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    private String jsonRead(File file) {
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try{
            scanner = new Scanner(file, "utf-8");
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine());
            }

        }catch (Exception e){
            System.out.println(e);
        }finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        return buffer.toString();
    }
    @Test
    public void test_ingester(){
        System.out.println("@Test - test_ingester");
        Ingester ig = new Ingester();
        String output = ig.getData();
        System.out.println(output);

    }
}

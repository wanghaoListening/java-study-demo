package com.haothink.file;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wanghao on 2020-03-17
 * mail:hiwanghao
 *
 **/
public class DuplicateLineFilter {


    private static List<String> container = new LinkedList<>();

    public static void main(String[] args) {


        try {
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(new FileInputStream("/Users/didi/Desktop/bjtu/知识图谱相关/kg-data/data/stock.csv")));
            BufferedWriter bufferedWriter =
                    new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/Users/didi/Desktop/bjtu/知识图谱相关/kg-data/data/stock_1.csv")));

            String line = bufferedReader.readLine();
            while (line != null){
                String[] lines = line.split(",");
                if(container.contains(lines[0])){
                    line = bufferedReader.readLine();
                    continue;
                }
                bufferedWriter.append(line);
                bufferedWriter.append(System.getProperty("line.separator"));
                line = bufferedReader.readLine();
                container.add(lines[0]);
            }
        }catch (Exception e){

            e.printStackTrace();
        }

    }
}

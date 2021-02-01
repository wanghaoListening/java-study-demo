package com.haothink.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Created by wanghao on 2020-05-16
 * mail:hiwanghao
 **/
public class MyFSDataInputStream extends FSDataInputStream {

    public MyFSDataInputStream(InputStream in) {

        super(in);
    }
    public String readline(Configuration conf, String remoteFilePath) throws IOException {

        if(null == remoteFilePath || remoteFilePath.length() == 0){

            throw new RuntimeException("the remoteFilePath must be not null");
        }
        FileSystem fs = null;
        Path remotePath = null;
        FSDataInputStream in = null;
        BufferedReader bufferedReader = null;
        String line = null;
        try {
            fs = FileSystem.get(conf);
            remotePath = new Path(remoteFilePath);
            in = fs.open(remotePath);
            bufferedReader = new BufferedReader(new InputStreamReader(in));
            line = bufferedReader.readLine();
            return line;
        }catch (Exception e){
            System.out.println("数据读取异常");
            return line;
        }finally {
            if(null != bufferedReader) {
                bufferedReader.close();
            }
            if(null != in) {
                in.close();
            }
            if(null != fs) {
                fs.close();
            }
        }

    }
   
}

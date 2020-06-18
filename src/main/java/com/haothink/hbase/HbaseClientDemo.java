package com.haothink.hbase;


import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.TableName;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by wanghao on 2020-06-18
 * mail:hiwanghao@didiglobal.com
 **/
public class HbaseClientDemo {



    private static Admin hBaseAdmin = null;
    private static Configuration conf = null;
    private static Connection connection = null;

    static {

        try {
            conf = HBaseConfiguration.create();
            conf.set("hbase.rootdir", "hdfs://master:9000/hbase");
            conf.set("hbase.zookeeper.quorum", "master,slave1,slave2");
            connection = ConnectionFactory.createConnection(conf);
            hBaseAdmin = connection.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createTable(String tableName, String[] fields) throws IOException {

        TableName tableNameObj = TableName.valueOf(tableName);

        if (hBaseAdmin.tableExists(tableNameObj)){

            //表已存在
            hBaseAdmin.disableTable(tableNameObj);
            hBaseAdmin.deleteTable(tableNameObj);
        }else {
            //表不存在，创建表

            TableDescriptorBuilder tableDescBuilder = TableDescriptorBuilder.newBuilder(tableNameObj);

            for(String field: fields) {
                ColumnFamilyDescriptorBuilder columnDescBuilder = ColumnFamilyDescriptorBuilder
                        .newBuilder(Bytes.toBytes(field));
                tableDescBuilder.setColumnFamily(columnDescBuilder.build());
            }

            tableDescBuilder.build();

        }
    }


    private static void addRecord(String tableName, String row, String[] fields, String[] values) throws IOException {

        TableName tableNameObj = TableName.valueOf(tableName);

        Table table = connection.getTable(tableNameObj);

        for (int i = 0; i < fields.length; i++) {

            Put put = new Put(row.getBytes());

            String[] cols = fields[i].split(":");

            if (cols.length == 1) {


                put.addColumn(cols[0].getBytes(), "".getBytes(), values[i].getBytes());

            } else {

                put.addColumn(cols[0].getBytes(), cols[1].getBytes(), values[i].getBytes());

            }

            table.put(put);
        }
        table.close();

    }

    private static void scanColumn(String tableName, String column) throws IOException {

        Table table = connection.getTable(TableName.valueOf(tableName));

        Scan scan = new Scan();

        ResultScanner scanner = table.getScanner(scan);

        for(Result result:scanner) {

            System.out.println(result.toString());

        }
        table.close();
    }

    private static void deleteRow(String tableName, String row) throws IOException {

        TableName tableNameObj = TableName.valueOf(tableName);

        Table table = connection.getTable(tableNameObj);

        Delete delete = new Delete(row.getBytes());

        table.delete(delete);

        table.close();
    }
}

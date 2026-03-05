package com.novaretail.etl.transform;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import static org.apache.spark.sql.functions.col;

public class Cleaner {
    public Dataset<Row> removeNullCountry(Dataset<Row> df) {
        return df.filter(col("country").isNotNull());
    }
}

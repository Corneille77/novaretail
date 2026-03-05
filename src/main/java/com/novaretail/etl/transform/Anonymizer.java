package com.novaretail.etl.transform;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public class Anonymizer {
    public Dataset<Row> dropEmail(Dataset<Row> df) {
        return df.drop("customer_email");
    }
}

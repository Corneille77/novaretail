package com.novaretail.etl.extract;

import com.novaretail.etl.PipelineConfig;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class JdbcExtractor {
    private final PipelineConfig cfg;

    public JdbcExtractor(PipelineConfig cfg) {
        this.cfg = cfg;
    }

    public Dataset<Row> extract(SparkSession spark) {
        return spark.read()
                .format("jdbc")
                .option("url", cfg.getDbUrl())
                .option("dbtable", cfg.getDbTable())
                .option("user", cfg.getDbUser())
                .option("password", cfg.getDbPass())
                .option("driver", "com.mysql.cj.jdbc.Driver")
                .load();
    }
}

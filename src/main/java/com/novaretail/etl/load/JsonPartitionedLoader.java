package com.novaretail.etl.load;

import com.novaretail.etl.PipelineConfig;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;

public class JsonPartitionedLoader {
    private final PipelineConfig cfg;

    public JsonPartitionedLoader(PipelineConfig cfg) {
        this.cfg = cfg;
    }

    public void write(Dataset<Row> df) {
        df.write()
          .mode(SaveMode.Overwrite)
          .partitionBy("country")
          .json(cfg.getOutputPath());
    }
}

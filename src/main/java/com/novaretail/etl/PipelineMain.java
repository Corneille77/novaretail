package com.novaretail.etl;

import com.novaretail.etl.extract.JdbcExtractor;
import com.novaretail.etl.load.JsonPartitionedLoader;
import com.novaretail.etl.transform.Anonymizer;
import com.novaretail.etl.transform.Cleaner;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.col;

public class PipelineMain {

    public static void main(String[] args) {
        PipelineConfig cfg = PipelineConfig.fromEnv();

        SparkSession spark = SparkSession.builder()
                .appName("NovaRetail-ETL")
                .master("local[*]") // local dev
                .config("spark.sql.shuffle.partitions", "1")
                .getOrCreate();

        // 1) EXTRACT (JDBC)
        Dataset<Row> raw = new JdbcExtractor(cfg).extract(spark);

        // 2) CLEAN (Map/Filter) => remove NULL country
        Dataset<Row> cleaned = new Cleaner().removeNullCountry(raw);

        // 3) ANONYMIZE => drop email
        Dataset<Row> anonymized = new Anonymizer().dropEmail(cleaned);

        // 4) SORT (Reduce) => by country then purchase_amount desc (within each country)
        Dataset<Row> sorted = anonymized
                .repartition(col("country")) // ensures data is grouped by country
                .sortWithinPartitions(col("country").asc(), col("purchase_amount").desc());

        // 5) LOAD => JSON overwrite + partitionBy(country)
        new JsonPartitionedLoader(cfg).write(sorted);

        spark.stop();
    }
}

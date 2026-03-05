package com.novaretail.etl;

public final class PipelineConfig {

    private final String dbUrl;
    private final String dbUser;
    private final String dbPass;
    private final String dbTable;
    private final String outputPath;

    private PipelineConfig(String dbUrl, String dbUser, String dbPass, String dbTable, String outputPath) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
        this.dbTable = dbTable;
        this.outputPath = outputPath;
    }

    public static PipelineConfig fromEnv() {
        String url = envOr("DB_URL", "jdbc:mysql://localhost:3306/novaretail_legacy?useSSL=false&serverTimezone=UTC");
        String user = envOr("DB_USER", "root");
        String pass = envOr("DB_PASS", "");
        String table = envOr("DB_TABLE", "customer_transactions");
        String out = envOr("OUTPUT_PATH", "./output/datalake/customer_transactions_json");
        return new PipelineConfig(url, user, pass, table, out);
    }

    private static String envOr(String key, String defaultValue) {
        String v = System.getenv(key);
        return (v == null || v.isBlank()) ? defaultValue : v;
    }

    public String getDbUrl() { return dbUrl; }
    public String getDbUser() { return dbUser; }
    public String getDbPass() { return dbPass; }
    public String getDbTable() { return dbTable; }
    public String getOutputPath() { return outputPath; }
}

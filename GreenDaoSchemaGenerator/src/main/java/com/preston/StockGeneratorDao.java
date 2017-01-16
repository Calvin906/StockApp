package main.java.com.preston;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Schema;

import main.java.com.preston.StockEntities.StockEntity;

public class StockGeneratorDao {

    private static final int CORE_DB_VERSION = 1;
    private static final int STOCK_DB_VERSION = 1;

    private static final String DEFAULT_JAVA_PACKAGE = "com.preston.data.repo.greendao";
    private static final String DEFAULT_FILE_LOCATION = "../app/src-gen-dao";

    private static String genPackage(String suffix) {
        return String.format("%s.%s", DEFAULT_JAVA_PACKAGE, suffix);
    }

    public static void main(String args[]) throws Exception {
        DaoGenerator daoGenerator = new DaoGenerator();

        Schema coreSchema = new Schema(CORE_DB_VERSION, DEFAULT_JAVA_PACKAGE);
        attachCoreEntities(coreSchema);
        daoGenerator.generateAll(coreSchema, DEFAULT_FILE_LOCATION);

        Schema stockSchema = new Schema(STOCK_DB_VERSION, genPackage("stock"));
        attachStockEntities(stockSchema);
        daoGenerator.generateAll(stockSchema, DEFAULT_FILE_LOCATION);

    }

    public static Schema attachCoreEntities(Schema schema) {
        return schema;
    }

    public static Schema attachStockEntities(Schema schema) {
        new StockEntity("Stock").attachTo(schema);
        return schema;
    }
}

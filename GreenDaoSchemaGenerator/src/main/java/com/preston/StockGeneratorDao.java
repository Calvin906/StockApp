package main.java.com.preston;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

import main.java.com.preston.StockEntities.StockEntity;

public class StockGeneratorDao {

    private static final int CORE_DB_VERSION = 2;

    private static final String DEFAULT_JAVA_PACKAGE = "com.preston.data.repo.greendao";
    private static final String DEFAULT_FILE_LOCATION = "../app/src-gen-dao";

    private static String genPackage(String suffix) {
        return String.format("%s.%s", DEFAULT_JAVA_PACKAGE, suffix);
    }

    public static void main(String args[]) throws Exception {
        DaoGenerator daoGenerator = new DaoGenerator();

        Schema schema = new Schema(CORE_DB_VERSION, DEFAULT_JAVA_PACKAGE);


        StockEntity stockEntity = new StockEntity("Stock");
        Entity user = new UserEntity("User").attachTo(schema);
        Entity stock = stockEntity.attachTo(schema);

        user.addToMany(stock, stockEntity.getIdProperty());

        daoGenerator.generateAll(schema, DEFAULT_FILE_LOCATION);

    }


}

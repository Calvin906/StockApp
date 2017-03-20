package main.java.com.preston;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;
import org.greenrobot.greendao.generator.ToMany;

import main.java.com.preston.StockEntities.StockEntity;

public class StockGeneratorDao {

    private static final int CORE_DB_VERSION = 9;

    private static final String DEFAULT_JAVA_PACKAGE = "com.preston.data.repo.greendao";
    private static final String DEFAULT_FILE_LOCATION = "../app/src-gen-dao";

    private static String genPackage(String suffix) {
        return String.format("%s.%s", DEFAULT_JAVA_PACKAGE, suffix);
    }

    public static void main(String args[]) throws Exception {
        DaoGenerator daoGenerator = new DaoGenerator();

        Schema schema = new Schema(CORE_DB_VERSION, DEFAULT_JAVA_PACKAGE);


        StockEntity stockEntity = new StockEntity("Stock");
        UserEntity userEntity = new UserEntity("User");
        Entity user = userEntity.attachTo(schema);
        Entity stock = stockEntity.attachTo(schema);

        ToMany userToStocks = user.addToMany(stock, stockEntity.getEncodedId());
        userToStocks.setName("stocks");

        daoGenerator.generateAll(schema, DEFAULT_FILE_LOCATION);

    }


}

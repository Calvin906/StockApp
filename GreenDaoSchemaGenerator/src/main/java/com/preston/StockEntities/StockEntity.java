package main.java.com.preston.StockEntities;

import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;

import main.java.com.preston.BaseEntity;

/**
 * Created by Alex on 9/4/16.
 */
public class StockEntity extends BaseEntity {

    public StockEntity(String name) {
        super(name);
    }

    @Override
    protected Property makeIdProperty(Entity entity) {
        return entity.addStringProperty("ticker").index().unique().notNull().primaryKey().getProperty();
    }

    @Override
    protected void setupEntity(Entity entity) {
        entity.addLongProperty("dailyVolume");
        entity.addDoubleProperty("change");
        entity.addDoubleProperty("daysLow");
        entity.addDoubleProperty("daysHigh");
        entity.addDoubleProperty("yearsLow");
        entity.addDoubleProperty("yearsHigh");
        entity.addStringProperty("marketCapitalization");
        entity.addDoubleProperty("lastTradePrice");
        entity.addStringProperty("daysRange");
        entity.addStringProperty("name");
        entity.addLongProperty("volume");
        entity.addStringProperty("stockExchange");
        entity.addDoubleProperty("PricePurchased");
        entity.getInterfacesToImplement().add("android.os.Parcelable");
        entity.setHasKeepSections(true);
    }

    @Override
    public Property getIdProperty() {
        return super.getIdProperty();
    }
}

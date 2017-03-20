package main.java.com.preston.StockEntities;

import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;

import main.java.com.preston.BaseEntity;

/**
 * Created by Alex on 9/4/16.
 */
public class StockEntity extends BaseEntity {
    public Property getEncodedId() {
        return encodedId;
    }

    public void setEncodedId(Property encodedId) {
        this.encodedId = encodedId;
    }

    public Property encodedId;

    public StockEntity(String name) {
        super(name);
    }

    @Override
    protected Property makeIdProperty(Entity entity) {
        return entity.addStringProperty("ticker").unique().notNull().getProperty();
    }

    @Override
    protected void setupEntity(Entity entity) {
        encodedId = entity.addStringProperty("encodedId").index().notNull().getProperty();
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
        entity.addDoubleProperty("PricePurchased");
        entity.implementsSerializable();
        entity.setHasKeepSections(true);
    }

    @Override
    public Property getIdProperty() {
        return super.getIdProperty();
    }
}

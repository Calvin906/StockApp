package main.java.com.preston;

import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;

/**
 * Created by Alex Preston on 1/16/17.
 */

public class UserEntity extends BaseEntity{

    public UserEntity(String name) {
        super(name);
    }

    @Override
    protected Property makeIdProperty(Entity entity) {
        return entity.addStringProperty("encodedId").index().unique().notNull().primaryKey().getProperty();
    }

    @Override
    protected void setupEntity(Entity entity) {
        entity.addStringProperty("email");
        entity.addStringProperty("username");
        entity.addStringProperty("password");
        entity.getInterfacesToImplement().add("java.io.Serializable");

    }

    @Override
    public Property getIdProperty() {
        return super.getIdProperty();
    }
}

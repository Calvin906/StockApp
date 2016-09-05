package main.java.com.preston;

import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;

/**
 * Created by jpreston on 9/4/16.
 */
public abstract class BaseEntity {
    private String name;
    private Property idProperty;
    public BaseEntity(String name) {
        this.name = name;
    }

    public Entity attachTo(Schema schema) {
        Entity entity = schema.addEntity(name);
        idProperty = makeIdProperty(entity);
        setupEntity(entity);
        return entity;
    }

    protected abstract Property makeIdProperty(Entity entity);

    protected abstract void setupEntity(Entity entity);

    public Property getIdProperty() {
        return idProperty;
    }

}
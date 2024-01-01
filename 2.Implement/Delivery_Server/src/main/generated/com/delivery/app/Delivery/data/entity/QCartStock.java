package com.delivery.app.Delivery.data.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCartStock is a Querydsl query type for CartStock
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCartStock extends EntityPathBase<CartStock> {

    private static final long serialVersionUID = -118604913L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCartStock cartStock = new QCartStock("cartStock");

    public final QCart cartId;

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final QStock stockId;

    public QCartStock(String variable) {
        this(CartStock.class, forVariable(variable), INITS);
    }

    public QCartStock(Path<? extends CartStock> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCartStock(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCartStock(PathMetadata metadata, PathInits inits) {
        this(CartStock.class, metadata, inits);
    }

    public QCartStock(Class<? extends CartStock> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cartId = inits.isInitialized("cartId") ? new QCart(forProperty("cartId"), inits.get("cartId")) : null;
        this.stockId = inits.isInitialized("stockId") ? new QStock(forProperty("stockId")) : null;
    }

}


package com.delivery.app.Delivery.data.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCart is a Querydsl query type for Cart
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCart extends EntityPathBase<Cart> {

    private static final long serialVersionUID = -1378906169L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCart cart = new QCart("cart");

    public final NumberPath<Integer> cost = createNumber("cost", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMarket market;

    public final NumberPath<Long> marketId = createNumber("marketId", Long.class);

    public final StringPath marketName = createString("marketName");

    public final QUser user;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QCart(String variable) {
        this(Cart.class, forVariable(variable), INITS);
    }

    public QCart(Path<? extends Cart> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCart(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCart(PathMetadata metadata, PathInits inits) {
        this(Cart.class, metadata, inits);
    }

    public QCart(Class<? extends Cart> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.market = inits.isInitialized("market") ? new QMarket(forProperty("market")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}


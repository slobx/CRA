package com.slobx.cra.infrastructure.common.entity;

import java.io.Serializable;


public interface IEntity<I extends Serializable> extends Serializable {

    I getId();

    String getKey();
}

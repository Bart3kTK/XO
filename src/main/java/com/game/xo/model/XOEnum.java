package com.game.xo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum XOEnum {
    X(1),
    O(2);

    private Integer value;
}

package com.xmut.osm.exception;

/**
 * @author 阮胜
 * @date 2018/7/2 19:39
 */
public class TargetEntityNotFound extends Exception {
    private static final String TARGET_ENTITY_NOT_FOUND = "找不到目标实体";
    public TargetEntityNotFound() {
        super(TARGET_ENTITY_NOT_FOUND);
    }

}


package com.chufinfo.sorting.utils;

import java.util.HashMap;

/**
 * Map工具类
 */
public class MapUtils extends HashMap<String, Object> {

    private static final long serialVersionUID = 5718585512283667568L;

    @Override
    public MapUtils put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}

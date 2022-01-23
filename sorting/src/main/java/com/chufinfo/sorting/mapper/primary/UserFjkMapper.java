package com.chufinfo.sorting.mapper.primary;

import com.chufinfo.sorting.model.UserFjk;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author litk
 * @since 2022-01-18
 */
public interface UserFjkMapper extends BaseMapper<UserFjk> {
    Boolean insertBatchUserFjk(@Param("userFjkList")List<UserFjk> userFjkList);

}

package com.chufinfo.sorting.service;

import com.chufinfo.sorting.model.Sjxx;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiejun
 * @since 2022-01-07
 */
public interface ISjxxService extends IService<Sjxx> {
    /**
     * 试管架上架
     * @param sgjBh
     * @return
     */
    boolean putSjxx(String sgjBh);

    /**
     * 试管架下架
     * @param sgjBh
     * @return
     */
    boolean offSjxx(String sgjBh);

    /**
     * 下架所有试管架
     * @return
     */
    boolean offAllSjxx();
}

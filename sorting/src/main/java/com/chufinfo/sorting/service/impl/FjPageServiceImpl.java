package com.chufinfo.sorting.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chufinfo.sorting.entity.param.*;
import com.chufinfo.sorting.enums.SystemEnum;
import com.chufinfo.sorting.exception.RRException;
import com.chufinfo.sorting.mapper.primary.*;
import com.chufinfo.sorting.mapper.second.LimsSortingMapper;
import com.chufinfo.sorting.model.*;
import com.chufinfo.sorting.service.IFjPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author litk
 * @since 2022-01-06
 */
@Service
@Slf4j
public class FjPageServiceImpl extends ServiceImpl<FjPageMapper, FjSgResultParam> implements IFjPageService {
    @Autowired
    private BbmxMapper bbmxMapper;
    @Autowired
    private FjkMapper fjkMapper;
    @Autowired
    private JymdbmMapper jymdbmMapper;
    @Autowired
    private SgjMapper sgjMapper;
    @Autowired
    private SjxxMapper sjxxMapper;
    @Autowired
    private LimsSortingMapper limsSortingMapper;
    /**
     * 分拣页面列表
     * @param user
     * @return
     */
    @Override
    public List<FjSgResultParam> getFjPageFjSgList(User user) {
        List<FjSgResultParam> fjSgResultParamList=new ArrayList<>();
        //分拣框
        List<Fjk> fjkList=getFjkList();
        if(fjkList==null||fjkList.size()==0){
            return  fjSgResultParamList;
        }
        //每个用户显示所有分拣框
        fjkList.forEach(fjkVo -> {
            FjSgResultParam fjSgVo=new FjSgResultParam();
            fjSgVo.setColor(fjkVo.getColor());//颜色
            fjSgVo.setFjkMc(fjkVo.getName());//名称
            fjSgVo.setFjkId(fjkVo.getId());//分拣框id
            fjSgResultParamList.add(fjSgVo);
        });
        //只有用户开始分拣才能试管架相关
        if(user.getKsTime()!=null&&Objects.equals(SystemEnum.gzStatus.START.getCode(), user.getGzStatus())){
            Long userId=user.getId();
            //分拣框ids
            List<Long> fjkIds=fjkList.stream().map(Fjk::getId).collect(Collectors.toList());
            //分拣框使用试管架数量（上架/下架）
            List<Sjxx> allSjxxList=getSjxxByFjkIds(fjkIds,userId,user.getKsTime());
            if(allSjxxList==null||allSjxxList.size()==0){
                return  fjSgResultParamList;
            }
            //按分拣框分组统计试管架数量
            Map<Long,List<Sjxx>> sjxxGroup=allSjxxList.stream().filter(item->item.getYbsl()>0).collect(Collectors.groupingBy(Sjxx::getFjkId));
            fjSgResultParamList.forEach(fjkVo->{
                List<Sjxx> sjxxFjk=sjxxGroup.get(fjkVo.getFjkId());
                if(sjxxFjk!=null&&sjxxFjk.size()>0){
                    fjkVo.setSgjSL(sjxxFjk.size());//已用试管架数量
                }
            });
            //正在上架的试管架
            List<Sjxx> sjxxBeing=allSjxxList.stream().filter(item->item.getStatus().equals(SystemEnum.sgjStatus.SJSGJ.getCode()))
                            .collect(Collectors.toList());
            if(sjxxBeing.size() == 0){
                return  fjSgResultParamList;
            }
            //试管架基本信息
            List<Long> sgjIds=sjxxBeing.stream().map(Sjxx::getSgjId).collect(Collectors.toList());
            List<Sgj> sgjBeing=getSgjListByIds(fjkIds,sgjIds);
            fjSgResultParamList.forEach(fjkVo->{
                Long fjkId=fjkVo.getFjkId();
                Sjxx sjxxSgj=sjxxBeing.stream().filter(item->item.getFjkId().equals(fjkId)).findFirst().orElse(null);
                if(sjxxSgj!=null){
                    Long sgjId=sjxxSgj.getSgjId();
                    Sgj sgj=sgjBeing.stream().filter(item->item.getId().equals(sgjId)).findFirst().orElse(null);
                    if(sgj!=null){
                        fjkVo.setSgjBh(sgj.getSgjBh());//试管架编号
                        fjkVo.setSgjRl(sgj.getSgjRl());//试管架容量
                        fjkVo.setSgjId(sgj.getId());//试管架id
                    }
                    fjkVo.setYbSL(sjxxSgj.getYbsl());//已用样本试管数量
                }
            });
        }

        return fjSgResultParamList;
    }

    /**
     * 文本框扫码事件方法
     * @param txm
     * @param user
     * @return
     */
    @Override
    public FjSgResultParam handlerYbxx(String txm,User user) {
        if(existBbmxByTxt(txm)){
            throw new RRException("样本已经被分拣");
        }
        List<YBXXResultParam> ybxxResultParamList=limsSortingMapper.getYBXXByTxm(txm);
        if(ybxxResultParamList==null||ybxxResultParamList.size()==0){
            throw new RRException(String.format("根据条码%s查询无样本信息",txm));
        }
        YBXXResultParam param=ybxxResultParamList.get(0);
        FjSgResultParam fjSgVo=new FjSgResultParam();
        Jymdbm jymdbm=getJymdbmByMdbm(param.getJymdbm());
        if(jymdbm==null||jymdbm.getFjkId()==null){
            throw new RRException(String.format("目的编码%s还没有分配分拣框",param.getJymdbm()));
        }
        //根据标本目的编码查找分拣框id
        Long fjkId=jymdbm.getFjkId();
        Long userId=user.getId();
        Sjxx sjxx=getSjxxByFjkId(fjkId,userId);
        if(sjxx==null||sjxx.getSgjId()==null){
            throw new RRException(String.format("分拣框%s没有上架中的试管架",fjkId));
        }
        Long sgjId=sjxx.getSgjId();
        Fjk fjk=fjkMapper.selectById(fjkId);
        Sgj sgj=sgjMapper.selectById(sgjId);
        Integer num=sjxx.getYbsl();
        if(num>=sgj.getSgjRl()){
            throw new RRException(String.format("试管架%s容量已经用完！",sgj.getSgjBh()));
        }
        num++;
        //保存样本明细
        Bbmx bbmx=new Bbmx();
        bbmx.setSmUserId(userId);
        bbmx.setSmTime(new Date());
        bbmx.setSjId(sjxx.getId());
        bbmx.setFjkId(fjkId);
        bbmx.setFjkMc(fjk.getName());
        bbmx.setSgjId(sgjId);
        bbmx.setSgjBh(sgj.getSgjBh());
        bbmx.setSgLsh(num);
        bbmx.setTxm(param.getTxm());
        bbmx.setHzxm(param.getHzxm());
        bbmx.setJymdbm(param.getJymdbm());
        bbmx.setJymdmc(param.getJymdmc());
        bbmx.setJgdm(param.getJgdm());//送检机构编码
        bbmx.setJigmc(param.getJigmc());//送检机构名称
        bbmx.setDelFlag(SystemEnum.delFlag.OK.getCode());
        bbmx.setStatus(SystemEnum.bbStatus.WJJ.getCode());
        Integer cnt= bbmxMapper.insert(bbmx);
        sjxx.setYbsl(num);
        sjxxMapper.updateById(sjxx);
        fjSgVo.setColor(fjk.getColor());//颜色
        fjSgVo.setFjkMc(fjk.getName());//名称
        fjSgVo.setFjkId(fjk.getId());//分拣框id
        fjSgVo.setSgjSL(getSjSgjCount(user.getKsTime(),userId));//已用试管架数量
        fjSgVo.setSgjBh(sgj.getSgjBh());//试管架编号
        fjSgVo.setSgjRl(sgj.getSgjRl());//试管架容量
        fjSgVo.setSgjId(sgjId);//试管架id
        fjSgVo.setYbSL(num);//样本试管数量
        return fjSgVo;
    }
    /**
     * 样本条码是否被分拣
     * @param txm
     * @return
     */
    public Boolean existBbmxByTxt(String txm){
        EntityWrapper<Bbmx> param=new EntityWrapper<>();
        param.eq("txm",txm);
        param.eq("del_flag",SystemEnum.delFlag.OK.getCode());
        return bbmxMapper.selectCount(param)>0;
    }

    /**
     * 分拣框基本信息
     * @return
     */
    public List<Fjk> getFjkList(){
        EntityWrapper<Fjk> param=new EntityWrapper<>();
        param.eq("status",SystemEnum.usingStatus.YES.getCode());
        param.eq("del_flag",SystemEnum.delFlag.OK.getCode());
        return fjkMapper.selectList(param);
    }

    /**
     * 试管架基本信息
     * @param fjkIds
     * @param sgjIds
     * @return
     */
    public List<Sgj> getSgjListByIds(List<Long> fjkIds,List<Long> sgjIds){
        EntityWrapper<Sgj> param=new EntityWrapper<>();
        param.in("fjk_id",fjkIds.toArray());
        param.in("id",sgjIds.toArray());
        param.eq("del_flag",SystemEnum.delFlag.OK.getCode());
        return sgjMapper.selectList(param);
    }

    /**
     * 根据条码查询分拣框id
     * @param mdbm
     * @return
     */
    public Jymdbm getJymdbmByMdbm(String mdbm){
        EntityWrapper<Jymdbm> param=new EntityWrapper<>();
        param.eq("del_flag",SystemEnum.delFlag.OK.getCode());
        param.in("mdbm",mdbm.split(","));
        List<Jymdbm> jymdbmList=jymdbmMapper.selectList(param);
        if(jymdbmList==null||jymdbmList.size()==0)return null;
        if(jymdbmList.size()==1)return jymdbmList.get(0);
        Map<Long,List<Jymdbm>> jymdGroup=jymdbmList.stream().collect(Collectors.groupingBy(Jymdbm::getFjkId));
        return jymdGroup.size()==1?jymdbmList.get(0):null;
    }

    /**
     * 查询分拣框上架信息
     * @param fjkId
     * @param userId
     * @return
     */
    public Sjxx getSjxxByFjkId(Long fjkId,Long userId){
        Sjxx param=new Sjxx();
        param.setFjkId(fjkId);
        param.setUserId(userId);
        param.setStatus(SystemEnum.sgjStatus.SJSGJ.getCode());
        return sjxxMapper.selectOne(param);
    }

    /**
     * 查询所有分拣框上架信息
     * @param fjkIds
     * @param userId
     * @param ksTime
     * @return
     */
    public List<Sjxx> getSjxxByFjkIds(List<Long> fjkIds,Long userId,Date ksTime){
        EntityWrapper<Sjxx> param=new EntityWrapper<>();
        param.eq("user_id",userId);
        param.in("fjk_id",fjkIds.toArray());
        param.gt("create_time",ksTime);
        return sjxxMapper.selectList(param);
    }

    /**
     * 分拣框试管架数量
     * @param ksTime
     * @param userId
     * @return
     */
    public Integer getSjSgjCount(Date ksTime, Long userId){
        EntityWrapper<Sjxx> param=new EntityWrapper<>();
        param.eq("user_id",userId);
        param.gt("ybsl",0);
        param.gt("create_time",ksTime);
        return sjxxMapper.selectCount(param);
    }

    /**
     * 试管架样本数量
     * @param sjId
     * @param userId
     * @param fjkId
     * @param sgjId
     * @return
     */
    public Integer getSgjBbmxCount(Long sjId, Long userId,Long fjkId,Long sgjId){
        EntityWrapper<Bbmx> param=new EntityWrapper<>();
        param.eq("sm_user_id",userId);
        param.eq("sj_id",sjId);
        param.eq("fjk_id",fjkId);
        param.eq("sgj_id",sgjId);
        param.eq("status",SystemEnum.bbStatus.WJJ.getCode());
        param.eq("del_flag",SystemEnum.delFlag.OK.getCode());
        return bbmxMapper.selectCount(param);
    }
}

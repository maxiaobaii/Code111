package com.baizhi.service.impl;

import com.baizhi.dao.LogDao;
import com.baizhi.entity.Log;
import com.baizhi.service.LogService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassNmae: LogServiceImpl
 * @Author: yddm
 * @DateTime: 2020/8/31 21:49
 * @Description: TODO
 */

@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Resource
    LogDao logDao;

    @Override
    public HashMap<String, Object> ShowLog(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        //当前页size 每页展示条数
        //当前页
        map.put("page", page);
        //总条数
        Example example = new Example(Log.class);
        int records = logDao.selectCountByExample(example);
        map.put("records", records);
        //总页数
        Integer total = records / rows == 0 ? records / rows : records / rows + 1;
        map.put("total", total);
        //数据
        Example example1 = new Example(Log.class);
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Log> feedBacks = logDao.selectByExampleAndRowBounds(example1, rowBounds);
        map.put("rows", feedBacks);
        return map;
    }

    @Override
    public void addLog(Log log) {
        logDao.insertSelective(log);
    }

    @Override
    public void delete(Log log) {
        logDao.deleteByPrimaryKey(log);
    }
}

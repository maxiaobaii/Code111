package com.baizhi.service.impl;

import com.baizhi.dao.FeedBackDao;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.FeedBack;
import com.baizhi.service.FeedBackService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @ClassNmae: FeedBackServiceImpl
 * @Author: yddm
 * @DateTime: 2020/8/28 15:50
 * @Description: TODO
 */

@Service
@Transactional
public class FeedBackServiceImpl implements FeedBackService {
    @Resource
    FeedBackDao feedBackDao;
    @Resource
    UserDao userDao;

//    @AddLog(value = "查看反馈")
    @Override
    public HashMap<String, Object> ShowFeed(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        //当前页size 每页展示条数
        //当前页
        map.put("page", page);
        //总条数
        Example example = new Example(FeedBack.class);
        int records = feedBackDao.selectCountByExample(example);
        map.put("records", records);
        //总页数
        Integer total = records / rows == 0 ? records / rows : records / rows + 1;
        map.put("total", total);
        //数据
        Example example1 = new Example(FeedBack.class);
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<FeedBack> feedBacks = feedBackDao.selectByExampleAndRowBounds(example1, rowBounds);
        map.put("rows", feedBacks);
        return map;
    }

    //    @AddLog(value = "添加反馈")
    @Override
    public void add(FeedBack feedBack) {
        feedBack.setId(UUID.randomUUID().toString());
        feedBack.setSaveDate(new Date());
        feedBackDao.insert(feedBack);
    }

    //    @AddLog(value = "更新反馈")
    @Override
    public void update(FeedBack feedBack) {
        feedBackDao.updateByPrimaryKey(feedBack);
    }

    //    @AddLog(value = "删除反馈")
    @Override
    public void delete(FeedBack feedBack) {
        feedBackDao.deleteByPrimaryKey(feedBack);
    }
}

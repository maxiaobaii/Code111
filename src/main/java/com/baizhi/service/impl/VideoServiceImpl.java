package com.baizhi.service.impl;

import com.baizhi.dao.VideoDao;
import com.baizhi.entity.Video;
import com.baizhi.po.AddVideoPo;
import com.baizhi.po.SecondVideoPo;
import com.baizhi.po.VideoDetailPo;
import com.baizhi.po.VideoPo;
import com.baizhi.repository.VideoRepository;
import com.baizhi.service.VideoService;
import com.baizhi.util.AliyunOssUtil;
import com.baizhi.vo.AddVideoVo;
import com.baizhi.vo.SecondVideoVo;
import com.baizhi.vo.VideoDetailVo;
import com.baizhi.vo.VideoVo;
import lombok.SneakyThrows;
import org.apache.ibatis.session.RowBounds;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassNmae: VideoServiceImpl
 * @Author: yddm
 * @DateTime: 2020/8/28 13:22
 * @Description: TODO
 */

@Service
@Transactional
public class VideoServiceImpl implements VideoService {
    @Resource
    VideoDao videoDao;
    @Resource
    VideoRepository videoRepository;
    @Resource
    ElasticsearchTemplate elasticsearchTemplate;

//    @AddLog(value = "查询所有视频")
    @Override
    public HashMap<String, Object> queryPageVideo(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        Video video = new Video();
        //分页
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        //页面数据
        List<Video> videos = videoDao.selectByRowBounds(video, rowBounds);
        //视频数量
        Integer count = videoDao.selectCount(video);
        //总页数
        map.put("total", (count % rows == 0) ? (count / rows) : (count / rows + 1));
        map.put("records", count);
        map.put("page", page);
        map.put("rows", videos);

        return map;
    }

    //    @AddLog(value = "添加视频")
    @Override
    public String add(Video video) {
        String id = UUID.randomUUID().toString();
        video.setId(id);
        video.setPublishDate(new Date());
        video.setUserId("1");
        video.setGroupId("1");
        video.setCategoryId("1");
        videoDao.insertSelective(video);
        return id;
    }

    //    @AddLog(value = "修改视频信息")
    @Override
    public void edit(Video video) {
        videoDao.updateByPrimaryKeySelective(video);
    }

    //    @AddLog(value = "删除视频")
    @Override
    public void del(Video video) {
        videoDao.deleteByPrimaryKey(video);
    }


    //    @AddLog(value = "删除视频")
    @Override
    public HashMap<String, Object> delete(Video video) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            //根据传过来的视频id查询该视频
            Video video1 = videoDao.selectByPrimaryKey(video);
            //拿到视频的地址
            String path = video1.getPath();
            //拿到视频的封面地址
            String cover = video1.getCover();
            String[] split = path.split("/");
            String[] split1 = cover.split("/");
            String videoPath = split[split.length - 2] + "/" + split[split.length - 1];
            String coverPath = split1[split1.length - 2] + "/" + split1[split1.length - 1];
            AliyunOssUtil.TestDelete(videoPath);
            AliyunOssUtil.TestDelete(coverPath);
            videoDao.deleteByPrimaryKey(video);
            //删除Es中的索引
            videoRepository.delete(video1);
            map.put("message", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "删除失败");
        }
        return map;
    }

    @Override
    public AddVideoVo addVideo(AddVideoPo addVideoPo) {
        AddVideoPo avp = new AddVideoPo();
        AddVideoVo avv = new AddVideoVo(avp.getId(), avp.getVideoTitle(), avp.getCover(), avp.getPath(),
                avp.getUploadTime(), avp.getDescription(), avp.getCateName(), avp.getCategoryId(), UUID.randomUUID().toString());
        return avv;
    }

    @Override
    public List<VideoVo> queryFirst() {
        ArrayList<VideoVo> videoVos = new ArrayList<>();
        try {
            List<VideoPo> videoPos = videoDao.queryFirst();
            //格式转换
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (VideoPo videoPo : videoPos) {
                String format = simpleDateFormat.format(videoPo.getPublishDate());
                VideoVo videoVo = new VideoVo(videoPo.getId(), videoPo.getTitle(), videoPo.getCover(), videoPo.getPath(),
                        format, videoPo.getBrief(), videoPo.getCateName(), 0, videoPo.getHeadImg());
                videoVos.add(videoVo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return videoVos;
    }

    @Override
    public List<SecondVideoVo> querySecondVideo(String cateId) {
        ArrayList<SecondVideoVo> secondVideoVos = new ArrayList<>();
        try {
            List<SecondVideoPo> svp = videoDao.querySecondVideo(cateId);
            for (SecondVideoPo secondVideoPo : svp) {
                SecondVideoVo secondVideoVo = new SecondVideoVo(secondVideoPo.getId(), secondVideoPo.getVideoTitle(),
                        secondVideoPo.getCover(), secondVideoPo.getPath(), secondVideoPo.getUploadTime(),
                        secondVideoPo.getDescription(), secondVideoPo.getCateName(), secondVideoPo.getCategoryId(),
                        secondVideoPo.getUserId(), secondVideoPo.getUserName());
                secondVideoVos.add(secondVideoVo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return secondVideoVos;
    }

    @Override
    public VideoDetailVo queryByVideoDetail(String videoId, String cateId, String userId) {
        VideoDetailPo vdp = videoDao.queryByVideoDetail(videoId, cateId, userId);
        VideoDetailVo vdv = new VideoDetailVo(vdp.getId(), vdp.getVideoTitle(), vdp.getCover(),
                vdp.getPath(), vdp.getUploadTime(), vdp.getDescription(), 2,
                vdp.getCateName(), vdp.getCategoryId(), vdp.getUserId(), vdp.getUserPicImg(),
                vdp.getUserName(), 10, true, vdp.getVideoList());
        return vdv;
    }

    @Override
    public Video queryOneVideo(Video video) {
        return videoDao.selectOne(video);
    }

    @Override
    public List<Video> queryEs(String content) {
        //构建查询条件对象
        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withIndices("yingxvideo")
                .withTypes("video")
                .withQuery(QueryBuilders.queryStringQuery(content).field("title").field("brief"))
                .build();
        List<Video> videos = elasticsearchTemplate.queryForList(build, Video.class);
        return videos;
    }

    //进行高亮查询
    @Override
    public List<Video> querySearch1(String content) {
        //构建处理高亮对象
        HighlightBuilder.Field field = new HighlightBuilder.Field("*");
        field.preTags("<span style='color:red'>");//前缀
        field.postTags("</span>");
        //创建查询条件对象
        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withIndices("yingxvideo")//指定索引名
                .withTypes("video")//指定类型
                .withQuery(QueryBuilders.queryStringQuery(content).field("title").field("brief"))//指定查询的条件
                .withHighlightFields(field)//查理高亮
                .build();
        //高亮查询
        AggregatedPage<Video> videos = elasticsearchTemplate.queryForPage(build, Video.class, new SearchResultMapper() {
            //处理高亮结果
            @SneakyThrows
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                ArrayList<Video> list = new ArrayList<>();
                SearchHit[] hits = searchResponse.getHits().getHits();
                for (SearchHit hit : hits) {
//                    System.out.println("hit.原始数据 = " + hit.getSourceAsString());//原始数据
//                    System.out.println("hit.高亮数据： = " + hit.getHighlightFields());//高亮数据

                    //原始数据
                    Map<String, Object> map = hit.getSourceAsMap();
                    String id = map.get("id").toString();
                    String title = map.get("title").toString();
                    String brief = map.get("brief").toString();
                    String path = map.get("path").toString();
                    String publishDate = map.get("publishDate").toString();
                    //处理日期格式
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date newPublishDate = sdf.parse(publishDate);
                    Video video = new Video(id, title, brief, path, null, newPublishDate, null, null, null);
                    //处理高亮数据
                    Map<String, HighlightField> high = hit.getHighlightFields();
                    String title1 = high.get("title").getFragments()[0].toString();
                    String brief1 = high.get("brief").getFragments()[0].toString();
                    video.setTitle(title1);
                    video.setTitle(brief1);
                    list.add(video);
                }
                return new AggregatedPageImpl<T>((List<T>) list);
            }
        });
        List<Video> content1 = videos.getContent();
        return content1;
    }
}

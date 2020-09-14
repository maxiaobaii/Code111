package com.baizhi.repository;

import com.baizhi.entity.Video;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @ClassNmae: VideoRepository
 * @Author: yddm
 * @DateTime: 2020/9/9 21:06
 * @Description: TODO
 */

public interface VideoRepository extends ElasticsearchRepository<Video, String> {
}

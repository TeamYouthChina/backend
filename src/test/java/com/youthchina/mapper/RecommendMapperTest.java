
package com.youthchina.mapper;

import com.youthchina.dao.jinhao.RecommendMapper;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.recommendation.RecommendService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RecommendMapperTest {
    @Resource
    RecommendMapper recommendMapper;

    @Resource
    RecommendService recommendService;//.andExpect(content().json("{\"content\":{\"offset\":0,\"limit\":2147483646,\"data\":[{\"id\":1,\"body\":{\"braftEditorRaw\":\"{\\\"braftEditorRaw\\\":{\\\"blocks\\\":[{\\\"key\\\":\\\"dtj4a\\\",\\\"text\\\":\\\"<有感于腾讯公司的发家史，总觉得腾讯背后有某种强大的力量，能靠微创新（也可称山寨）能发展到现如今的体量也算是世界奇观。靠模仿icq完成了资本的原始积累并实现了滚雪球，可以这么说腾讯的今天是一切都建立在oicq（qq）之上的，从qq堂，qq飞车，qq劲舞，腾讯的发家史就是一个复制粘贴史。。。并且发展到如今规模，企业文化还是坚强的延续下来，复制粘贴的企业文化从高层到底层，已深入骨髓，从领子烂到里子。。对创新型企业来说，腾讯如一颗毒瘤存在，注定不会得到大家尊重。>\\\",\\\"type\\\":\\\"unstyled\\\",\\\"depth\\\":0,\\\"inlineStyleRanges\\\":[],\\\"entityRanges\\\":[],\\\"data\\\":{}}],\\\"entityMap\\\":{}},\\\"previewText\\\":\\\"<在此填入你的文字>\\\",\\\"resourceIdList\\\":[]}\",\"previewText\":\"有感于腾讯公司的发家史，总觉得腾讯背后有某种强大的力量，能靠微创新（也可称山寨）能发展到现如今的体量也算是世界奇观。靠模仿icq完成了资本的原始积累并实现了滚雪球，可以这么说腾讯的今天是一切都建立在oicq（qq）之上的，从qq堂，qq飞车，qq劲舞，腾讯的发家史就是一个复制粘贴史。。。并且发展到如今规模，企业文化还是坚强的延续下来，复制粘贴的企业文化从高层到底层，已深入骨髓，从领子烂到里子。。对创新型企业来说，腾讯如一颗毒瘤存在，注定不会得到大家尊重。\",\"compiletype\":1},\"comments\":[{\"id\":1,\"creator\":{\"id\":2,\"email\":\"Admin2\",\"register_date\":1546300800000,\"date_of_birth\":0,\"first_name\":\"Admin2\",\"last_name\":\"Admin2\",\"gender\":\"FEMALE\",\"avatar_url\":\"---\",\"role\":[\"ROOT\"],\"phone_number\":\"23456\"},\"body\":\"短评评论1\",\"create_at\":1517616000000,\"is_anonymous\":false,\"modified_at\":1517616000000,\"upvoteCount\":0,\"downvoteCount\":0,\"evaluateStatus\":3}],\"author\":{\"id\":1,\"email\":\"Admin1\",\"register_date\":1546300800000,\"date_of_birth\":0,\"first_name\":\"Admin1\",\"last_name\":\"Admin1\",\"gender\":\"MALE\",\"avatar_url\":\"---\",\"role\":[\"ROOT\"],\"phone_number\":\"12345\"},\"upvoteCount\":0,\"downvoteCount\":2,\"attentionCount\":4,\"evaluateStatus\":3,\"modified_at\":1546300800000,\"attention\":false}],\"page_count\":0,\"item_count\":1,\"page_index\":0,\"is_first\":true,\"is_last\":false},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));

    @Test
    public void tag() {
        recommendMapper.addTag("92", 23, 43);
        Assert.assertNotNull(recommendMapper.isTagExist("92", 23, 43));
        recommendMapper.deleteTag("92", 23, 43);
        Assert.assertNull(recommendMapper.isTagExist("92", 23, 43));
    }

    @Test
    public void getUserLabel() {
        recommendMapper.addTag("1", 100, 2);
        recommendMapper.addTag("2", 100, 2);
        recommendMapper.addTag("46", 100, 2);
        List<String> labels = recommendMapper.getUserLabel(2);
        Assert.assertEquals(8, labels.size());
    }

    @Test
    public void getRecomQuestion() {
        List<String> labels = new ArrayList<>();
        labels.add("66");
        labels.add("77");
        recommendMapper.addTag("66", 1, 2);
        recommendMapper.addTag("66", 1, 5);
        recommendMapper.addTag("77", 1, 3);
        recommendMapper.addTag("88", 1, 9);
        List<Integer> quesIds = recommendMapper.getRecommendQuestion(labels);
        Assert.assertEquals(3, quesIds.size());
        for (Integer i : quesIds) {
            if (i != 2 && i != 5 && i != 3) {
                Assert.fail();
            }
        }
    }

    @Test
    public void getRecomEassy() {
        List<String> labels = new ArrayList<>();
        labels.add("66");
        labels.add("77");
        recommendMapper.addTag("66", 1, 2);
        recommendMapper.addTag("66", 1, 5);
        recommendMapper.addTag("77", 1, 3);
        recommendMapper.addTag("88", 1, 9);
        List<Integer> ids = recommendMapper.getRecommendQuestion(labels);
        Assert.assertEquals(3, ids.size());
        for (Integer i : ids) {
            if (i != 2 && i != 5 && i != 3) {
                Assert.fail();
            }
        }
    }

    @Test
    public void getRecomBr() {
        List<String> labels = new ArrayList<>();
        labels.add("66");
        labels.add("77");
        recommendMapper.addTag("66", 1, 2);
        recommendMapper.addTag("66", 1, 5);
        recommendMapper.addTag("77", 1, 3);
        recommendMapper.addTag("88", 1, 9);
        List<Integer> ids = recommendMapper.getRecommendQuestion(labels);
        Assert.assertEquals(3, ids.size());
        for (Integer i : ids) {
            if (i != 2 && i != 5 && i != 3) {
                Assert.fail();
            }
        }
    }

    @Test
    public void getRecomJob() {
        List<String> labels = new ArrayList<>();
        labels.add("66");
        labels.add("77");
        recommendMapper.addTag("66", 1, 2);
        recommendMapper.addTag("66", 1, 5);
        recommendMapper.addTag("77", 1, 3);
        recommendMapper.addTag("88", 1, 9);
        List<Integer> ids = recommendMapper.getRecommendQuestion(labels);
        Assert.assertEquals(3, ids.size());
        for (Integer i : ids) {
            if (i != 2 && i != 5 && i != 3) {
                Assert.fail();
            }
        }
    }

    @Test
    public void getRecomCompany() {
        List<String> labels = new ArrayList<>();
        labels.add("66");
        labels.add("77");
        recommendMapper.addTag("66", 1, 2);
        recommendMapper.addTag("66", 1, 5);
        recommendMapper.addTag("77", 1, 3);
        recommendMapper.addTag("88", 1, 9);
        List<Integer> ids = recommendMapper.getRecommendQuestion(labels);
        Assert.assertEquals(3, ids.size());
        for (Integer i : ids) {
            if (i != 2 && i != 5 && i != 3) {
                Assert.fail();
            }
        }
    }

    @Test
    public void getRecomUser() {
        List<String> labels = new ArrayList<>();
        labels.add("66");
        labels.add("77");
        recommendMapper.addTag("66", 1, 2);
        recommendMapper.addTag("66", 1, 5);
        recommendMapper.addTag("77", 1, 3);
        recommendMapper.addTag("88", 1, 9);
        List<Integer> ids = recommendMapper.getRecommendQuestion(labels);
        Assert.assertEquals(3, ids.size());
        for (Integer i : ids) {
            if (i != 2 && i != 5 && i != 3) {
                Assert.fail();
            }
        }

    }

    @Test
    public void getTag() throws NotFoundException {
        recommendService.addTag("1", 100, 1);
        recommendService.addTag("2", 100, 1);
        recommendService.addTag("3", 100, 1);
        recommendService.addTag("3", 100, 2);
        recommendService.addTag("3", 100, 3);
        recommendService.addTag("3", 100, 4);
        List<User> labels = recommendService.getRecommendUser(1);
        Assert.assertEquals(7, labels.size());
        for (User user : labels) {
            System.out.println(user.getId());
        }
    }
}


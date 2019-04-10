package com.youthchina.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dto.community.answer.SimpleAnswerRequestDTO;
import com.youthchina.dto.community.question.QuestionRequestDTO;
import com.youthchina.dto.util.RichTextRequestDTO;
import com.youthchina.util.AuthGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Created by hongshengzhang on 2/10/19.
 */



@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:test.xml"})
@WebAppConfiguration
public class QuestionControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${web.url.prefix}")
    private String urlPrefix;

    private AuthGenerator authGenerator = new AuthGenerator();

    MockMvc mvc;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    @Test
    public void getAnswersTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/questions/2/answers")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"offset\":0,\"limit\":2147483646,\"data\":[{\"id\":2,\"body\":{\"braftEditorRaw\":\"{\\\"braftEditorRaw\\\":{\\\"blocks\\\":[{\\\"key\\\":\\\"dtj4a\\\",\\\"text\\\":\\\"<问这个问题的，典型的不了解互联网公司，以为百度就是搜索，阿里就是淘宝，腾讯就是微信QQ。问个问题，楼主认为百度在全球有多少员工？这些人大致都在百度分别干哪些方面的工作？这个事情楼主是否有个概念，如果有概念，那问题的答案就不言自明了。实际上，这些第一代中国互联网公司，不仅仅做了他们的主打产品，还深度参与了中国互联网基础设施的建设，形成和管理培养一支庞大的互联网团队，合作构建了早期生态。举个例子，你看见的手机上的APP中，有多少app内有百度的代码？估计这个比例至少在5成以上。百度在移动领域，提供了很多sdk加速了移动开发，也从中分享了相关用户数据，也贡献了部分开源代码。说到数据这一点，单是百度掌握和存储的中国用户数据，就无法忽视，春节出行数据统计，百度能做，有哪些市场主体能做的比百度更全面翔实？恐怕为数不多。而且这样的数据体量，也不是哪个公司都能存的了的，可以了解下百度阳泉云计算中心。百度这个公司，且不说智能驾驶、人工智能方面的投入和积累，单说人才、美股市值和财报收入，就无法忽视。你可以说百度的搜索产品不行，可以说百度企业文化不好，也可以说百度掉出互联网第一梯队。但质问百度的存在意义，有点太苛刻了吧！中国的互联网公司再不行，也比有些国企效率高吧，也是无数互联网人辛苦加班干出来的。百度作为互联网公司，至少他提供的产品是透明的可选择的，比用特权赚钱或者用骗术赚钱干净多了。你不喜欢百度，搜索360必应随你用；谷歌不进入中国市场，是谷歌自己的决定，dragonfly这个项目在谷歌内部都pass不了。>\\\",\\\"type\\\":\\\"unstyled\\\",\\\"depth\\\":0,\\\"inlineStyleRanges\\\":[],\\\"entityRanges\\\":[],\\\"data\\\":{}}],\\\"entityMap\\\":{}},\\\"previewText\\\":\\\"<在此填入你的文字>\\\",\\\"resourceIdList\\\":[]}\",\"previewText\":\"问这个问题的，典型的不了解互联网公司，以为百度就是搜索，阿里就是淘宝，腾讯就是微信QQ。问个问题，楼主认为百度在全球有多少员工？这些人大致都在百度分别干哪些方面的工作？这个事情楼主是否有个概念，如果有概念，那问题的答案就不言自明了。实际上，这些第一代中国互联网公司，不仅仅做了他们的主打产品，还深度参与了中国互联网基础设施的建设，形成和管理培养一支庞大的互联网团队，合作构建了早期生态。举个例子，你看见的手机上的APP中，有多少app内有百度的代码？估计这个比例至少在5成以上。百度在移动领域，提供了很多sdk加速了移动开发，也从中分享了相关用户数据，也贡献了部分开源代码。说到数据这一点，单是百度掌握和存储的中国用户数据，就无法忽视，春节出行数据统计，百度能做，有哪些市场主体能做的比百度更全面翔实？恐怕为数不多。而且这样的数据体量，也不是哪个公司都能存的了的，可以了解下百度阳泉云计算中心。百度这个公司，且不说智能驾驶、人工智能方面的投入和积累，单说人才、美股市值和财报收入，就无法忽视。你可以说百度的搜索产品不行，可以说百度企业文化不好，也可以说百度掉出互联网第一梯队。但质问百度的存在意义，有点太苛刻了吧！中国的互联网公司再不行，也比有些国企效率高吧，也是无数互联网人辛苦加班干出来的。百度作为互联网公司，至少他提供的产品是透明的可选择的，比用特权赚钱或者用骗术赚钱干净多了。你不喜欢百度，搜索360必应随你用；谷歌不进入中国市场，是谷歌自己的决定，dragonfly这个项目在谷歌内部都pass不了。\",\"compiletype\":1},\"is_anonymous\":false,\"creator\":{\"id\":5,\"username\":\"DEF\",\"email\":\"123456@456.com\",\"phonenumber\":\"9876543210123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"DDD\",\"last_name\":\"DDDEEEFFF\",\"gender\":\"Female\",\"nation\":\"USA\",\"avatar_url\":\"---\",\"role\":[\"ADMIN\"],\"age\":28},\"modified_at\":\"2014-05-08 00:00:00.0\",\"create_at\":\"2014-05-08 00:00:00.0\"}],\"page_count\":0,\"item_count\":1,\"page_index\":0,\"is_first\":true,\"is_last\":false},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void searchQuestionsTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/questions").param("Company", "百度").param("Job", "")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"offset\":0,\"limit\":2147483646,\"data\":[{\"id\":5,\"creator\":{\"id\":1,\"username\":\"Admin\",\"email\":\"123456@123.com\",\"phonenumber\":\"1234657890123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"Admin\",\"last_name\":\"Admin\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatar_url\":\"---\",\"role\":[\"ROOT\"],\"age\":25},\"title\":\"如何看待 百度春晚营销\",\"is_anonymous\":false,\"create_at\":\"2019-01-01T00:00:00.000+0000\",\"modified_at\":\"2019-01-01T00:00:00.000+0000\",\"answers\":[{\"id\":5,\"body\":{\"braftEditorRaw\":\"{\\\"braftEditorRaw\\\":{\\\"blocks\\\":[{\\\"key\\\":\\\"dtj4a\\\",\\\"text\\\":\\\"<北京时间2月20日消息，百度及其去年分拆出来的流媒体视频网站爱奇艺将在周四盘后公布第四季度业绩。对于爱奇艺四季度的财报，分析师预计，该公司本季度营收将为9.68亿美元，这一预测位于公司此前给出的9.435亿至9.828亿美元的营收目标区间的高端，较去年四季度增长约43%-49%，增长速度远快于百度。值得一提的是，近日有媒体报道，美国证券交易委员会（SEC）的最新数据显示，高瓴资本于去年四季度重仓了爱奇艺，持有爱奇艺240,191,174股A类普通股，占爱奇艺A类股比例为11%。截至2018年底，高瓴资本在美的前五大重仓股分别是：百济神州、爱奇艺、京东、恩斯塔、阿里巴巴。其中，爱奇艺为高瓴资本第二大重仓股。此外，在业绩方面，爱奇艺会员规模不断扩大，截止到2018年9月，爱奇艺的会员数量达到8070万，同比增长89%，单季度增长1350万。作为控股股东，爱奇艺的亮眼数据也为百度带来了荣光。然而，明亮背后的阴霾才最触目惊心，纵观百度近年来在资本市场的布局，不惜下重本“买买买”背后，收获的更多的却是迷茫与尴尬。事实上，自上市以来，百度收购的大部分公司，不幸则被玩死，幸则半死不活。投中网就从中选取了十二家具有代表性的案例，从表面上的“苟活”或“死亡”入手，透析“百度爸爸”内心的跌宕起伏。http://Hao123.com2004年8月，百度宣称以5000万元收购国内最大网址站http://Hao123.com，实际收购金额仅为1000万元。此次收购后，“导航网站之父”李兴平，一夜暴富。而这场交易在当时饱受争议，矛盾点无非在于“百度为什么要巨资买下hao123，而不是复制一个hao123？”。知乎网友解释颇为合理，“如果当时百度的网址导航正面交锋hao123，作为强龙的百度未必压的了hao123这条地头蛇。就像百度拿现在360的导航没辙一样，天下武功为快不破，各自撑下去对双方都没好处。不管是谁输了，被人记得的总是第一，很少有人了解谁是第二的。最重要的是百度不差钱，能花钱就把风险规避掉，何乐而不为呢，无非是价格问题罢了。”然而，百度的资源并没能夯实住http://Hao123.com的“第一”江湖地位。后来的故事大家都知道了：360、腾讯、搜狗纷纷杀入导航网站，http://Hao123.com的先发优势早已消耗殆尽。天空软件2006年5月，百度以3000万收购天空软件，创下了当时的公司最高价收购记录。据悉，天空软件网站由张鹤创建于2001年，为当时国内最知名的软件下载网站之一，在ALEXA全球网站排名中名列351名，每日页面流量超过数百万。而这次收购，也代表着资本市场风向标的转变。“现在是互联网加速整合的一个高峰。”业内人士评价说，“所不同的是，以前大家收购的是有像Hao123这样有巨大流量的个人网站，而现在，大家抢的是热门软件，以及天空软件这样的软件通道。”但伴随互联网原住民崛起，近年来软件下载市场一直不温不火。遗憾的是，百度也未能为天空软件找到合适的转型方案，任由其慢慢被用户淡忘。天天静听2006年7月，百度收购千千静听。作为红极一时的音乐播放软件鼻祖，千千静听也难逃“百度劫难”。自从被百度收编后，变身百度音乐，后来又被百度甩掉，扔给了太合麦田。点讯输入法2009年10月，百度又将“魔爪”伸向了输入法领域。在收购了点讯输入法的开发公司后，将更名为“百度输入法”，界面也主打其标志性的“百度蓝”。然而最终，故事的结局与http://Hao123.com类似：腾讯、搜狗、360甚至讯飞陆续加入混战，百度输入法的地位之尴尬，恐怕早已无需多言。耀点1002010年5月，百度联合达芙妮投资耀点100。据悉，耀点100是中国第一家真正的网络百货商城，也是百度在B2C电商领域迈出的关键一步。然而，投中网发现，耀点100已于2017年7月28日停止运营，CEO等高管已回台湾，官网已经打不开。“把中国台湾的电商B2C模式直接移植过来用，这不光行不通，也是他们倒闭的直接原因”，业内人士一针见血地指出耀点100的失败，“只可惜苦了上千名供应商，欠款没地方讨。”乐酷天2010年10月，百度B2C心不死，与日本乐天合计出资5000万美元，推出网上商城“乐酷天”（乐天占股51%，百度占股49%）。然而，此站已于2016年4月20日宣布关闭，至此，百度B2C布局正式宣告失败。究其“死因”，可归结为“用户”和“供应链”两个方面。首先，电子的核心是流量和用户，百度在搜索流量向购物流量的转化方面几乎没有建树，同时既要养自己的孩子又要挣其他电商的钱也让百度比较纠结。其次，商务的核心是前台的运营和后台的供应链，乐天在本地化方面做的并不出色，无论从命名到前台表现再到对商家的服务都显得诚意不足。百伯网2011年1月，百度在原百度人才频道的基础之上，投资1亿元，将原“百度人才频道”产品独立成一家公司进行运营，并将其正式改名为“百伯网”。其主要产品是针对于招聘网站进行职位信息和公司招聘信息检索的垂直搜索引擎。无独有偶，百伯网再一次在互联网招聘的“激烈打斗”中败下阵来。随着智联招聘、中华英才网、58招聘等品牌的兴起，百伯网早已失去原有客群，地位鸡肋。番薯网2011年8月，百度收购番薯网40%股份，与中搜在线并列成为最大机构股东。作为当时全球最大的数字图书门户，番薯网可以为百度文库提供更优质的版权资源和更成熟的电子商务平台支持。说到底，李彦宏还是在打“文库变现”的算盘。但天不遂人愿，百度文库的盈利能力却并未因为这场收购而获得显著提升。据业内人士分析，“作为大部分网民寻找资源的首选站点，百度文库一直定位不清晰。加上后续的收费模式，更是流失了很多用户，且内容质量一直上不去。”>\\\",\\\"type\\\":\\\"unstyled\\\",\\\"depth\\\":0,\\\"inlineStyleRanges\\\":[],\\\"entityRanges\\\":[],\\\"data\\\":{}}],\\\"entityMap\\\":{}},\\\"previewText\\\":\\\"<在此填入你的文字>\\\",\\\"resourceIdList\\\":[]}\",\"previewText\":\"北京时间2月20日消息，百度及其去年分拆出来的流媒体视频网站爱奇艺将在周四盘后公布第四季度业绩。对于爱奇艺四季度的财报，分析师预计，该公司本季度营收将为9.68亿美元，这一预测位于公司此前给出的9.435亿至9.828亿美元的营收目标区间的高端，较去年四季度增长约43%-49%，增长速度远快于百度。值得一提的是，近日有媒体报道，美国证券交易委员会（SEC）的最新数据显示，高瓴资本于去年四季度重仓了爱奇艺，持有爱奇艺240,191,174股A类普通股，占爱奇艺A类股比例为11%。截至2018年底，高瓴资本在美的前五大重仓股分别是：百济神州、爱奇艺、京东、恩斯塔、阿里巴巴。其中，爱奇艺为高瓴资本第二大重仓股。此外，在业绩方面，爱奇艺会员规模不断扩大，截止到2018年9月，爱奇艺的会员数量达到8070万，同比增长89%，单季度增长1350万。作为控股股东，爱奇艺的亮眼数据也为百度带来了荣光。然而，明亮背后的阴霾才最触目惊心，纵观百度近年来在资本市场的布局，不惜下重本“买买买”背后，收获的更多的却是迷茫与尴尬。事实上，自上市以来，百度收购的大部分公司，不幸则被玩死，幸则半死不活。投中网就从中选取了十二家具有代表性的案例，从表面上的“苟活”或“死亡”入手，透析“百度爸爸”内心的跌宕起伏。http://Hao123.com2004年8月，百度宣称以5000万元收购国内最大网址站http://Hao123.com，实际收购金额仅为1000万元。此次收购后，“导航网站之父”李兴平，一夜暴富。而这场交易在当时饱受争议，矛盾点无非在于“百度为什么要巨资买下hao123，而不是复制一个hao123？”。知乎网友解释颇为合理，“如果当时百度的网址导航正面交锋hao123，作为强龙的百度未必压的了hao123这条地头蛇。就像百度拿现在360的导航没辙一样，天下武功为快不破，各自撑下去对双方都没好处。不管是谁输了，被人记得的总是第一，很少有人了解谁是第二的。最重要的是百度不差钱，能花钱就把风险规避掉，何乐而不为呢，无非是价格问题罢了。”然而，百度的资源并没能夯实住http://Hao123.com的“第一”江湖地位。后来的故事大家都知道了：360、腾讯、搜狗纷纷杀入导航网站，http://Hao123.com的先发优势早已消耗殆尽。天空软件2006年5月，百度以3000万收购天空软件，创下了当时的公司最高价收购记录。据悉，天空软件网站由张鹤创建于2001年，为当时国内最知名的软件下载网站之一，在ALEXA全球网站排名中名列351名，每日页面流量超过数百万。而这次收购，也代表着资本市场风向标的转变。“现在是互联网加速整合的一个高峰。”业内人士评价说，“所不同的是，以前大家收购的是有像Hao123这样有巨大流量的个人网站，而现在，大家抢的是热门软件，以及天空软件这样的软件通道。”但伴随互联网原住民崛起，近年来软件下载市场一直不温不火。遗憾的是，百度也未能为天空软件找到合适的转型方案，任由其慢慢被用户淡忘。天天静听2006年7月，百度收购千千静听。作为红极一时的音乐播放软件鼻祖，千千静听也难逃“百度劫难”。自从被百度收编后，变身百度音乐，后来又被百度甩掉，扔给了太合麦田。点讯输入法2009年10月，百度又将“魔爪”伸向了输入法领域。在收购了点讯输入法的开发公司后，将更名为“百度输入法”，界面也主打其标志性的“百度蓝”。然而最终，故事的结局与http://Hao123.com类似：腾讯、搜狗、360甚至讯飞陆续加入混战，百度输入法的地位之尴尬，恐怕早已无需多言。耀点1002010年5月，百度联合达芙妮投资耀点100。据悉，耀点100是中国第一家真正的网络百货商城，也是百度在B2C电商领域迈出的关键一步。然而，投中网发现，耀点100已于2017年7月28日停止运营，CEO等高管已回台湾，官网已经打不开。“把中国台湾的电商B2C模式直接移植过来用，这不光行不通，也是他们倒闭的直接原因”，业内人士一针见血地指出耀点100的失败，“只可惜苦了上千名供应商，欠款没地方讨。”乐酷天2010年10月，百度B2C心不死，与日本乐天合计出资5000万美元，推出网上商城“乐酷天”（乐天占股51%，百度占股49%）。然而，此站已于2016年4月20日宣布关闭，至此，百度B2C布局正式宣告失败。究其“死因”，可归结为“用户”和“供应链”两个方面。首先，电子的核心是流量和用户，百度在搜索流量向购物流量的转化方面几乎没有建树，同时既要养自己的孩子又要挣其他电商的钱也让百度比较纠结。其次，商务的核心是前台的运营和后台的供应链，乐天在本地化方面做的并不出色，无论从命名到前台表现再到对商家的服务都显得诚意不足。百伯网2011年1月，百度在原百度人才频道的基础之上，投资1亿元，将原“百度人才频道”产品独立成一家公司进行运营，并将其正式改名为“百伯网”。其主要产品是针对于招聘网站进行职位信息和公司招聘信息检索的垂直搜索引擎。无独有偶，百伯网再一次在互联网招聘的“激烈打斗”中败下阵来。随着智联招聘、中华英才网、58招聘等品牌的兴起，百伯网早已失去原有客群，地位鸡肋。番薯网2011年8月，百度收购番薯网40%股份，与中搜在线并列成为最大机构股东。作为当时全球最大的数字图书门户，番薯网可以为百度文库提供更优质的版权资源和更成熟的电子商务平台支持。说到底，李彦宏还是在打“文库变现”的算盘。但天不遂人愿，百度文库的盈利能力却并未因为这场收购而获得显著提升。据业内人士分析，“作为大部分网民寻找资源的首选站点，百度文库一直定位不清晰。加上后续的收费模式，更是流失了很多用户，且内容质量一直上不去。”\",\"compiletype\":1},\"is_anonymous\":false,\"creator\":{\"id\":7,\"username\":\"ABC\",\"email\":\"123456@123.com\",\"phonenumber\":\"1234657890123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"AAA\",\"last_name\":\"AAABBBCCC\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatar_url\":\"---\",\"role\":[\"APPLICANT\"],\"age\":25},\"modified_at\":\"2019-02-13 00:00:00.0\",\"create_at\":\"2019-02-13 00:00:00.0\"}],\"rela_type\":1,\"rela_id\":38,\"body\":{\"braftEditorRaw\":\"{\\\"braftEditorRaw\\\":{\\\"blocks\\\":[{\\\"key\\\":\\\"dtj4a\\\",\\\"text\\\":\\\"<如何看待 2019 年央视春晚的百度红包，不到一块钱还需要下载 App 才能领取？>\\\",\\\"type\\\":\\\"unstyled\\\",\\\"depth\\\":0,\\\"inlineStyleRanges\\\":[],\\\"entityRanges\\\":[],\\\"data\\\":{}}],\\\"entityMap\\\":{}},\\\"previewText\\\":\\\"<在此填入你的文字>\\\",\\\"resourceIdList\\\":[]}\",\"previewText\":\"如何看待 2019 年央视春晚的百度红包，不到一块钱还需要下载 App 才能领取？\",\"compiletype\":1},\"attention\":false},{\"id\":21,\"creator\":{\"id\":1,\"username\":\"Admin\",\"email\":\"123456@123.com\",\"phonenumber\":\"1234657890123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"Admin\",\"last_name\":\"Admin\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatar_url\":\"---\",\"role\":[\"ROOT\"],\"age\":25},\"title\":\"如何看待《搜索引擎百度已死》\",\"is_anonymous\":true,\"create_at\":\"2019-01-01T00:00:00.000+0000\",\"modified_at\":\"2019-01-01T00:00:00.000+0000\",\"answers\":[{\"id\":21,\"body\":{\"braftEditorRaw\":\"{\\\"braftEditorRaw\\\":{\\\"blocks\\\":[{\\\"key\\\":\\\"dtj4a\\\",\\\"text\\\":\\\"<核心原因是：新媒体浪潮中，中文互联网的内容创作、分发和变现体系都变了。这些年各种“号”涌现，改变了网站-搜索引擎的单一内容体系，转而走向共享式创作者-个性化分发与传统内容体系结合的新体系。做内容的不再是建一个网站去做SEO获取流量，而是将内容发布到有读者的平台，再通过分成、电商、赞赏、IP等模式变现。这些“号”不只是百家号，还有微信公众号、头条号、企鹅号、网易号、搜狐号、一点号、凤凰号、大鱼号等等。现在在这些平台上做内容的人，相当一部分是传统媒体人，也有一些是传统网站站长。随着门槛的降低，也确实有很多新新人群涌入，形成了今天鱼龙混杂的局面。然而这在传统网站时代也是一样的，我丝毫不觉得传统网站的整体内容质量会比今天各个平台的好到哪里去。网站内容同样参差不齐，还缺乏原创保护、广告分成、内容赞赏这样的机制。内容平台将内容型的网站变为平台号，实现了共享式创作和个性化分发，也实现了阅读体验的相对统一，是进步，而不是退步。>\\\",\\\"type\\\":\\\"unstyled\\\",\\\"depth\\\":0,\\\"inlineStyleRanges\\\":[],\\\"entityRanges\\\":[],\\\"data\\\":{}}],\\\"entityMap\\\":{}},\\\"previewText\\\":\\\"<在此填入你的文字>\\\",\\\"resourceIdList\\\":[]}\",\"previewText\":\"核心原因是：新媒体浪潮中，中文互联网的内容创作、分发和变现体系都变了。这些年各种“号”涌现，改变了网站-搜索引擎的单一内容体系，转而走向共享式创作者-个性化分发与传统内容体系结合的新体系。做内容的不再是建一个网站去做SEO获取流量，而是将内容发布到有读者的平台，再通过分成、电商、赞赏、IP等模式变现。这些“号”不只是百家号，还有微信公众号、头条号、企鹅号、网易号、搜狐号、一点号、凤凰号、大鱼号等等。现在在这些平台上做内容的人，相当一部分是传统媒体人，也有一些是传统网站站长。随着门槛的降低，也确实有很多新新人群涌入，形成了今天鱼龙混杂的局面。然而这在传统网站时代也是一样的，我丝毫不觉得传统网站的整体内容质量会比今天各个平台的好到哪里去。网站内容同样参差不齐，还缺乏原创保护、广告分成、内容赞赏这样的机制。内容平台将内容型的网站变为平台号，实现了共享式创作和个性化分发，也实现了阅读体验的相对统一，是进步，而不是退步。\",\"compiletype\":1},\"is_anonymous\":false,\"creator\":{\"id\":6,\"username\":\"GHI\",\"email\":\"123456@789.com\",\"phonenumber\":\"1112223334445\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"GGG\",\"last_name\":\"GGGHHHIII\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatar_url\":\"---\",\"role\":[\"ADMIN\"],\"age\":26},\"modified_at\":\"2018-02-03 00:00:00.0\",\"create_at\":\"2018-02-03 00:00:00.0\"}],\"rela_type\":1,\"rela_id\":38,\"body\":{\"braftEditorRaw\":\"{\\\"braftEditorRaw\\\":{\\\"blocks\\\":[{\\\"key\\\":\\\"dtj4a\\\",\\\"text\\\":\\\"<如何看待《搜索引擎百度已死》一文？百度沦为百家号的引流工具这一描述是否准确？百度的「护城河」是什么？>\\\",\\\"type\\\":\\\"unstyled\\\",\\\"depth\\\":0,\\\"inlineStyleRanges\\\":[],\\\"entityRanges\\\":[],\\\"data\\\":{}}],\\\"entityMap\\\":{}},\\\"previewText\\\":\\\"<在此填入你的文字>\\\",\\\"resourceIdList\\\":[]}\",\"previewText\":\"如何看待《搜索引擎百度已死》一文？百度沦为百家号的引流工具这一描述是否准确？百度的「护城河」是什么？\",\"compiletype\":1},\"attention\":false},{\"id\":25,\"creator\":{\"id\":1,\"username\":\"Admin\",\"email\":\"123456@123.com\",\"phonenumber\":\"1234657890123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"Admin\",\"last_name\":\"Admin\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatar_url\":\"---\",\"role\":[\"ROOT\"],\"age\":25},\"title\":\"百度对百度网盘用户限速会不会毁掉自己?\",\"is_anonymous\":true,\"create_at\":\"2019-01-01T00:00:00.000+0000\",\"modified_at\":\"2019-01-01T00:00:00.000+0000\",\"answers\":[{\"id\":25,\"body\":{\"braftEditorRaw\":\"{\\\"braftEditorRaw\\\":{\\\"blocks\\\":[{\\\"key\\\":\\\"dtj4a\\\",\\\"text\\\":\\\"<首先要说的是在此之前，国内的购物网站我最信任苏宁，是有着接近10年的苏宁老会员，也是级别最高的V4会员。悲剧的起点：我是2016年8月30号在苏宁易购网上商城买的戴尔(Dell)笔记本电脑，具体型号为Vostro 14-3459R-2528BB 14英寸笔记本电脑(i5-6200U 4G 500G)，订单号为10035126172。这个电脑我买的时候查的是质保2年，整机质保1年，主要部件质保2年。然后这个电脑我在2017年带到了国外使用。因为不是工作本，平时用的也不多。 悲剧发生：在2018年5月20号左右，我在使用过程中，调整了一下屏幕的角度，电脑主板电源口附近冒烟了，然后就再也开不开机了。我当时查了下戴尔官网，写的保修从2016年3月30号开始，然后已经过保了。 然后因为当时在国外，就让我的家人帮我联系下苏宁的售后客服人员，然后告知对方我的微信号，让对方加我的微信来沟通。最终是一个苏宁天津叫马竞（隐藏当事人的名字）的客服人员加了我的微信，然后给我打的微信电话进行沟通。我跟她说了我的电脑主板烧了不能开机，她说她去问戴尔的售后人员。最终她给我的答复是整机质保1年，主要部件质保2年。她说我的电脑已经过保了。我当时有问她主要部件不是质保2年么，她给我的答复是主要部件是指的屏幕，只有屏幕质保2年。当时我觉得很奇怪，一个电脑主要部件是屏幕。但是因为她是苏宁的客服人员，我是很信任她的，也就没再继续追问。快到高潮喽：我于2018年8月回国，因为马竞告诉我这个电脑已经过保了，于是我就到本地的一家电脑城去修，预期是花个200-300把电脑修好。当时修电脑的师傅说因为屏线没有装好，屏线烧了，导致主板短路，主板也被烧了。然后他给我焊接了一下，然后发现还是点不亮，只能是换主板了。换主板比较贵，差不多要1200-1500。于是我又想起来当时马竞说的，只有屏幕才是主要部件，越想越觉得不靠谱，于是我就去google了下，主板是不是主要部件，在得到肯定的搜索结果后，我再次联系了苏宁易购的客服，然后还是马竞给我打的电话。这次马竞告诉我主板可以保2年，并且告诉了我戴尔售后的地址和电话。于是我就去了戴尔售后，但是戴尔售后的人说我的本被第三方修过，因此不能质保了。高潮以及高潮后的痛苦:在此以后，就开始无限与苏宁易购扯皮的经历。2018-08-04 到 2018-08-09，几乎每天都要找苏宁易购在线客服2-4次。投诉，打一堆字，然后换来的只是在线客服复制粘贴一般的回复。回复的主要内容是有人会给我打电话处理。不幸的是，马竞是处理我case的客服人员。因为这个事情的根源责任在马竞，因此她百般阻止我的维修投诉，通常是我投诉3-4次，马竞才给我打一个电话。马竞经常用的借口是给我打电话了，但是我这边占线。天地良心啊，说瞎话被雷劈，我这边绝对没有在她给我打电话的时候被占线了。每次与马竞的电话沟通，马竞都是在推卸责任，说责任在我这边不在她那边。因为我找第三方修主板导致不能质保。那么问题来了，如果当初马竞告我质保2年，还在保，我会花钱到第三方去修吗？任何一个精神正常的人都干不出来这种事情吧。 是马竞告诉已经出保了我才去外面修的，这个也是任何一个精神正常的人都该干的事情吧，难道不修了直接扔掉吗。问题的根源在于马竞的不专业，因为她错误的告诉我只有屏幕保2年而主板只保1年，我才去外面修的。然后马竞竟然把责任甩的一干二净，我不知道是苏宁的客服人员都这样，还是只有马竞如此精明。在经过7天的超过几十次的投诉与沟通之后，马竞和苏宁给出了最终的解决方案，由我自费换主板，然后苏宁给我补偿200。这个我是无法接受的，因为马竞作为一个客服人员，搞不清楚究竟是保1年还是保2年，并且将错误的信息告知了我，才导致了后续的一系列问题，因此我期望的解决方法是苏宁或者马竞出钱给我换新主板。苏宁太让人失望了，如此经历实在让人难忘。令人绝望的客服人员，令人绝望的客服经历，让我怎能不注销苏宁易购账号呢！ 再见苏宁，再见苏宁易购，再也不见！ 不知道12315是否有用，不知道在天涯等各大网站发帖是否有用，不过我会竭尽所能的去试一下，如果任何办法都行不通，那么就只能注销苏宁账号了。>\\\",\\\"type\\\":\\\"unstyled\\\",\\\"depth\\\":0,\\\"inlineStyleRanges\\\":[],\\\"entityRanges\\\":[],\\\"data\\\":{}}],\\\"entityMap\\\":{}},\\\"previewText\\\":\\\"<在此填入你的文字>\\\",\\\"resourceIdList\\\":[]}\",\"previewText\":\"首先要说的是在此之前，国内的购物网站我最信任苏宁，是有着接近10年的苏宁老会员，也是级别最高的V4会员。悲剧的起点：我是2016年8月30号在苏宁易购网上商城买的戴尔(Dell)笔记本电脑，具体型号为Vostro 14-3459R-2528BB 14英寸笔记本电脑(i5-6200U 4G 500G)，订单号为10035126172。这个电脑我买的时候查的是质保2年，整机质保1年，主要部件质保2年。然后这个电脑我在2017年带到了国外使用。因为不是工作本，平时用的也不多。 悲剧发生：在2018年5月20号左右，我在使用过程中，调整了一下屏幕的角度，电脑主板电源口附近冒烟了，然后就再也开不开机了。我当时查了下戴尔官网，写的保修从2016年3月30号开始，然后已经过保了。 然后因为当时在国外，就让我的家人帮我联系下苏宁的售后客服人员，然后告知对方我的微信号，让对方加我的微信来沟通。最终是一个苏宁天津叫马竞（隐藏当事人的名字）的客服人员加了我的微信，然后给我打的微信电话进行沟通。我跟她说了我的电脑主板烧了不能开机，她说她去问戴尔的售后人员。最终她给我的答复是整机质保1年，主要部件质保2年。她说我的电脑已经过保了。我当时有问她主要部件不是质保2年么，她给我的答复是主要部件是指的屏幕，只有屏幕质保2年。当时我觉得很奇怪，一个电脑主要部件是屏幕。但是因为她是苏宁的客服人员，我是很信任她的，也就没再继续追问。快到高潮喽：我于2018年8月回国，因为马竞告诉我这个电脑已经过保了，于是我就到本地的一家电脑城去修，预期是花个200-300把电脑修好。当时修电脑的师傅说因为屏线没有装好，屏线烧了，导致主板短路，主板也被烧了。然后他给我焊接了一下，然后发现还是点不亮，只能是换主板了。换主板比较贵，差不多要1200-1500。于是我又想起来当时马竞说的，只有屏幕才是主要部件，越想越觉得不靠谱，于是我就去google了下，主板是不是主要部件，在得到肯定的搜索结果后，我再次联系了苏宁易购的客服，然后还是马竞给我打的电话。这次马竞告诉我主板可以保2年，并且告诉了我戴尔售后的地址和电话。于是我就去了戴尔售后，但是戴尔售后的人说我的本被第三方修过，因此不能质保了。高潮以及高潮后的痛苦:在此以后，就开始无限与苏宁易购扯皮的经历。2018-08-04 到 2018-08-09，几乎每天都要找苏宁易购在线客服2-4次。投诉，打一堆字，然后换来的只是在线客服复制粘贴一般的回复。回复的主要内容是有人会给我打电话处理。不幸的是，马竞是处理我case的客服人员。因为这个事情的根源责任在马竞，因此她百般阻止我的维修投诉，通常是我投诉3-4次，马竞才给我打一个电话。马竞经常用的借口是给我打电话了，但是我这边占线。天地良心啊，说瞎话被雷劈，我这边绝对没有在她给我打电话的时候被占线了。每次与马竞的电话沟通，马竞都是在推卸责任，说责任在我这边不在她那边。因为我找第三方修主板导致不能质保。那么问题来了，如果当初马竞告我质保2年，还在保，我会花钱到第三方去修吗？任何一个精神正常的人都干不出来这种事情吧。 是马竞告诉已经出保了我才去外面修的，这个也是任何一个精神正常的人都该干的事情吧，难道不修了直接扔掉吗。问题的根源在于马竞的不专业，因为她错误的告诉我只有屏幕保2年而主板只保1年，我才去外面修的。然后马竞竟然把责任甩的一干二净，我不知道是苏宁的客服人员都这样，还是只有马竞如此精明。在经过7天的超过几十次的投诉与沟通之后，马竞和苏宁给出了最终的解决方案，由我自费换主板，然后苏宁给我补偿200。这个我是无法接受的，因为马竞作为一个客服人员，搞不清楚究竟是保1年还是保2年，并且将错误的信息告知了我，才导致了后续的一系列问题，因此我期望的解决方法是苏宁或者马竞出钱给我换新主板。苏宁太让人失望了，如此经历实在让人难忘。令人绝望的客服人员，令人绝望的客服经历，让我怎能不注销苏宁易购账号呢！ 再见苏宁，再见苏宁易购，再也不见！ 不知道12315是否有用，不知道在天涯等各大网站发帖是否有用，不过我会竭尽所能的去试一下，如果任何办法都行不通，那么就只能注销苏宁账号了。\",\"compiletype\":1},\"is_anonymous\":false,\"creator\":{\"id\":6,\"username\":\"GHI\",\"email\":\"123456@789.com\",\"phonenumber\":\"1112223334445\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"GGG\",\"last_name\":\"GGGHHHIII\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatar_url\":\"---\",\"role\":[\"ADMIN\"],\"age\":26},\"modified_at\":\"2019-02-13 00:00:00.0\",\"create_at\":\"2019-02-13 00:00:00.0\"}],\"rela_type\":1,\"rela_id\":38,\"body\":{\"braftEditorRaw\":\"{\\\"braftEditorRaw\\\":{\\\"blocks\\\":[{\\\"key\\\":\\\"dtj4a\\\",\\\"text\\\":\\\"<百度对百度网盘用户限速会不会毁掉自己? >\\\",\\\"type\\\":\\\"unstyled\\\",\\\"depth\\\":0,\\\"inlineStyleRanges\\\":[],\\\"entityRanges\\\":[],\\\"data\\\":{}}],\\\"entityMap\\\":{}},\\\"previewText\\\":\\\"<在此填入你的文字>\\\",\\\"resourceIdList\\\":[]}\",\"previewText\":\"百度对百度网盘用户限速会不会毁掉自己?\",\"compiletype\":1},\"attention\":false}],\"page_count\":0,\"item_count\":3,\"page_index\":0,\"is_first\":true,\"is_last\":false},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void getQuestionTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/questions/1")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"id\":1,\"creator\":{\"id\":1,\"username\":\"Admin\",\"email\":\"123456@123.com\",\"phonenumber\":\"1234657890123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"Admin\",\"last_name\":\"Admin\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatar_url\":\"---\",\"role\":[\"ROOT\"],\"age\":25},\"title\":\"腾讯好么？\",\"is_anonymous\":true,\"create_at\":\"2019-01-01T00:00:00.000+0000\",\"modified_at\":\"2019-01-01T00:00:00.000+0000\",\"answers\":[{\"id\":1,\"body\":{\"braftEditorRaw\":\"{\\\"braftEditorRaw\\\":{\\\"blocks\\\":[{\\\"key\\\":\\\"dtj4a\\\",\\\"text\\\":\\\"<在软件行业，操作系统平台就是那个八，其他的应用软件就是那个二。微软已经踩到了一次狗屎运，得到了软件行业80%的利润，现在，他所需要做的，就是保持住这个地位。但技术不是静止不动的，不断有新的技术生长出来，在成千上万种技术中，有一种会长成参天大树，利润无比丰厚，取代原来的技术平台，成为新的主流趋势。到了今天，微软在互联网时代江河日下，谷歌和facebook大肆收购，花上百亿美元去买下新兴的技术，为的是什么？就是在押宝呀。技术在不断向前升级，哪一个方向才是未来的主流趋势呢？没有人知道。对于腾讯来说，也是一样的。小马哥每天都在为这件事情而焦虑。截至目前，在国内，押中两次宝的就只有腾讯和阿里。阿里押中了淘宝和支付宝，腾讯押中了QQ和微信。在移动互联网时代，腾讯可以稍稍松一口气了，但是在下一个主流技术趋势到来的时候，还有这个好运气么？>\\\",\\\"type\\\":\\\"unstyled\\\",\\\"depth\\\":0,\\\"inlineStyleRanges\\\":[],\\\"entityRanges\\\":[],\\\"data\\\":{}}],\\\"entityMap\\\":{}},\\\"previewText\\\":\\\"<在此填入你的文字>\\\",\\\"resourceIdList\\\":[]}\",\"previewText\":\"在软件行业，操作系统平台就是那个八，其他的应用软件就是那个二。微软已经踩到了一次狗屎运，得到了软件行业80%的利润，现在，他所需要做的，就是保持住这个地位。但技术不是静止不动的，不断有新的技术生长出来，在成千上万种技术中，有一种会长成参天大树，利润无比丰厚，取代原来的技术平台，成为新的主流趋势。到了今天，微软在互联网时代江河日下，谷歌和facebook大肆收购，花上百亿美元去买下新兴的技术，为的是什么？就是在押宝呀。技术在不断向前升级，哪一个方向才是未来的主流趋势呢？没有人知道。对于腾讯来说，也是一样的。小马哥每天都在为这件事情而焦虑。截至目前，在国内，押中两次宝的就只有腾讯和阿里。阿里押中了淘宝和支付宝，腾讯押中了QQ和微信。在移动互联网时代，腾讯可以稍稍松一口气了，但是在下一个主流技术趋势到来的时候，还有这个好运气么？\",\"compiletype\":1},\"is_anonymous\":false,\"creator\":{\"id\":5,\"username\":\"DEF\",\"email\":\"123456@456.com\",\"phonenumber\":\"9876543210123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"DDD\",\"last_name\":\"DDDEEEFFF\",\"gender\":\"Female\",\"nation\":\"USA\",\"avatar_url\":\"---\",\"role\":[\"ADMIN\"],\"age\":28},\"modified_at\":\"2018-02-03 00:00:00.0\",\"create_at\":\"2018-02-03 00:00:00.0\"}],\"rela_type\":1,\"rela_id\":37,\"body\":{\"braftEditorRaw\":\"{\\\"braftEditorRaw\\\":{\\\"blocks\\\":[{\\\"key\\\":\\\"dtj4a\\\",\\\"text\\\":\\\"<asdasd>\\\",\\\"type\\\":\\\"unstyled\\\",\\\"depth\\\":0,\\\"inlineStyleRanges\\\":[],\\\"entityRanges\\\":[],\\\"data\\\":{}}],\\\"entityMap\\\":{}},\\\"previewText\\\":\\\"<在此填入你的文字>\\\",\\\"resourceIdList\\\":[]}\",\"previewText\":\"asdasd\",\"compiletype\":1},\"attention\":false},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void addQuestionTest() throws Exception {
        QuestionRequestDTO questionRequestDTO = new QuestionRequestDTO();
        questionRequestDTO.setTitle("Question No.100");
        String json = "";
        String pre = "pre";
        RichTextRequestDTO richTextDTO = new RichTextRequestDTO();
        richTextDTO.setBraftEditorRaw(json);
        richTextDTO.setPreviewText(pre);
        richTextDTO.setCompiletype(1);
        questionRequestDTO.setBody(richTextDTO);
        //questionDTO.setAbbreviation("Abbreviation of the question No.100");
        questionRequestDTO.setRela_type(2);
        questionRequestDTO.setRela_id(2);
        questionRequestDTO.setIs_anonymous(true);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(questionRequestDTO);
        this.mvc.perform(
                post(this.urlPrefix + "/questions").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)
                        .with(authGenerator.authentication())
        )
                .andDo(print());
    }

    @Test
    public void updateQuestionTest() throws Exception {
        QuestionRequestDTO questionRequestDTO = new QuestionRequestDTO();
        questionRequestDTO.setTitle("How to learn JAVA");
        String json = "";
        String pre = "pre";
        RichTextRequestDTO richTextDTO = new RichTextRequestDTO();
        richTextDTO.setBraftEditorRaw(json);
        richTextDTO.setPreviewText(pre);
        richTextDTO.setCompiletype(1);
        questionRequestDTO.setBody(richTextDTO);
        questionRequestDTO.setIs_anonymous(true);
        //questionDTO.setAbbreviation("Abbreviation of the question No.100");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(questionRequestDTO);

        this.mvc.perform(
                put(this.urlPrefix + "/questions/2").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)
                        .with(authGenerator.authentication())
        )
                .andDo(print());
//                .andExpect(content().json("{\"content\":{\"searchResult\":[{\"jobId\":1,\"jobName\":\"front\",\"jobProfCode\":\"A\",\"jobStartTime\":\"2019-01-01\",\"jobEndTime\":\"2020-01-01\",\"jobType\":1,\"jobDescription\":\"996\",\"jobDuty\":\"front\",\"jobHighlight\":\"50K\",\"jobSalaryFloor\":5000,\"jobSalaryCap\":6000,\"jobLink\":\"job.com\",\"cvReceiMail\":\"youth@china\",\"cvNameRule\":\"nameRule\",\"jobActive\":1,\"jobLocationList\":[{\"region_num\":1,\"region_chn\":\"北京\",\"region_eng\":\"Beijing\",\"region_level\":1,\"region_parent_num\":1,\"start_time\":\"2019-01-01T11:11:22.000+0000\",\"is_delete\":null,\"is_delete_time\":null,\"jobId\":1}],\"jobReqList\":[{\"degreeNum\":1,\"degreeChn\":\"本科\",\"degreeEng\":\"Bachelor\",\"startDate\":\"2019-01-01T11:11:22.000+0000\",\"jobId\":1},{\"degreeNum\":2,\"degreeChn\":\"硕士\",\"degreeEng\":\"Master\",\"startDate\":\"2019-01-02T11:11:22.000+0000\",\"jobId\":1}],\"industries\":[{\"indNum\":1,\"indCode\":\"A\",\"indChn\":\"工\",\"indEng\":\"eng\",\"indLevel\":2,\"indParentCode\":\"A3\",\"startTime\":\"2018-10-11T11:11:22.000+0000\",\"isDelete\":null,\"isDeleteTime\":null,\"companyId\":null,\"jobId\":1},{\"indNum\":2,\"indCode\":\"B\",\"indChn\":\"农\",\"indEng\":\"eng\",\"indLevel\":2,\"indParentCode\":\"B3\",\"startTime\":\"2018-10-11T11:11:22.000+0000\",\"isDelete\":null,\"isDeleteTime\":null,\"companyId\":null,\"jobId\":1}],\"profession\":{\"profNum\":1,\"profCode\":\"A\",\"profParentCode\":\"A\",\"profChn\":\"前端\",\"profEng\":\"frontEnd\",\"startTime\":\"2019-01-01T11:11:22.000+0000\"},\"isDelete\":null,\"isDeleteTime\":null,\"company\":{\"companyId\":1,\"companyName\":\"大疆\",\"companyCode\":\"2\",\"companyIntroduc\":\"无人机\",\"companyNature\":{\"natureNum\":1,\"natureChn\":\"国企\",\"natureEng\":\"public\",\"natureDetail\":\"good\",\"startTime\":\"2019-01-01T11:11:22.000+0000\"},\"companyScale\":{\"scaleNum\":1,\"scaleChn\":\"大\",\"scaleEng\":\"big\",\"startTime\":\"2019-01-01T11:11:22.000+0000\"},\"location\":{\"region_num\":1,\"region_chn\":\"北京\",\"region_eng\":\"Beijing\",\"region_level\":1,\"region_parent_num\":1,\"start_time\":\"2019-01-01T11:11:22.000+0000\",\"is_delete\":null,\"is_delete_time\":null,\"jobId\":null},\"country\":null,\"companyMail\":\"dji@com\",\"companyWebsite\":\"dji.com\",\"companyStartDate\":\"2005-11-20\",\"companyLogo\":\"1\",\"companyVerify\":1,\"userId\":null,\"isDelete\":null,\"isDeleteTime\":null,\"jobs\":null,\"indList\":[{\"indNum\":1,\"indCode\":\"A\",\"indChn\":\"工\",\"indEng\":\"eng\",\"indLevel\":2,\"indParentCode\":\"A3\",\"startTime\":null,\"isDelete\":null,\"isDeleteTime\":null,\"companyId\":1,\"jobId\":null},{\"indNum\":2,\"indCode\":\"B\",\"indChn\":\"农\",\"indEng\":\"eng\",\"indLevel\":2,\"indParentCode\":\"B3\",\"startTime\":null,\"isDelete\":null,\"isDeleteTime\":null,\"companyId\":1,\"jobId\":null}],\"verificationList\":[]},\"hr\":{\"hrId\":1,\"companyId\":1,\"hrOnJob\":1,\"userId\":null,\"isDelete\":null,\"isDeleteTime\":null},\"id\":1}],\"status\":null},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
        this.mvc.perform(
                get(this.urlPrefix + "/questions/2").param("Id", "2")
                        .with(authGenerator.authentication())

        )
                .andDo(print());
        //.andExpect(content().json("{\"content\":{\"id\":2,\"creator\":{\"id\":1,\"username\":\"yihao guo\",\"email\":null,\"phonenumber\":\"18463722634\",\"registerDate\":null,\"realName\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatarUrl\":null,\"role\":null,\"age\":21},\"title\":\"How to learn JAVA\",\"body\":\"I don't know\",\"createAt\":\"2018-12-05T13:32:40.000+0000\",\"editAt\":\"2019-02-14T16:50:27.000+0000\",\"answers\":null,\"invitation\":null,\"labelIds\":null,\"rela_type\":3,\"rela_id\":null,\"abbreviation\":\"Abbreviation of the question No.100\",\"anonymous\":null},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));

    }

//    @Test
//    public void invitesAnswerTest() throws Exception {
//        List<Integer> userlist = new ArrayList<>();
//        userlist.add(1);
//        userlist.add(2);
//        ObjectMapper mapper = new ObjectMapper();
//        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//        java.lang.String requestJson = ow.writeValueAsString(userlist);
//        System.out.println(requestJson);
//        this.mvc.perform(
//                put(this.urlPrefix + "/questions/2/invite").contentType(MediaType.APPLICATION_JSON_UTF8)
//                        .content(requestJson)
//                        .with(authGenerator.authentication())
//        )
//                .andDo(print());
//    }
//
//    @Test
//    public void inviteAnswerTest() throws Exception {
//        this.mvc.perform(
//                put(this.urlPrefix + "/questions/2/invite/1")
//                        .with(authGenerator.authentication())
//        )
//                .andDo(print());
//    }
//
    @Test
    public void attentionTest() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/questions/2/attention")
                        .with(authGenerator.authentication())
        )
                .andDo(print());
    }

    @Test
    public void deleteQuestionTest() throws Exception {
        this.mvc.perform(
                delete(this.urlPrefix + "/questions/4")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":204,\"reason\":\"delete success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
        this.mvc.perform(
                get(this.urlPrefix + "/questions/4")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":null,\"status\":{\"code\":4040,\"reason\":\"没有找到这个问题\"}}", false));

    }

    @Test
    public void testAddAnswer() throws Exception {
        SimpleAnswerRequestDTO simpleAnswerDTO = new SimpleAnswerRequestDTO();
        simpleAnswerDTO.setIs_anonymous(true);
        String json = "";
        String pre = "pre";
        RichTextRequestDTO richTextDTO = new RichTextRequestDTO();
        richTextDTO.setBraftEditorRaw(json);
        richTextDTO.setPreviewText(pre);
        richTextDTO.setCompiletype(1);
        simpleAnswerDTO.setBody(richTextDTO);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String searchJson = ow.writeValueAsString(simpleAnswerDTO);
        this.mvc.perform(
                post(this.urlPrefix + "/questions/2/answers")
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(searchJson)
        )
                .andDo(print());
          //      .andExpect(content().json("{\"content\":{\"body\":{\"braftEditorRaw\":null,\"previewText\":\"pre\",\"compiletype\":1},\"is_anonymous\":true,\"creator\":{\"id\":1,\"username\":\"YihaoGuo\",\"email\":\"test@test.com\",\"phonenumber\":\"2022922222\",\"register_date\":null,\"first_name\":\"John\",\"last_name\":\"Doe\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":[\"APPLICANT\"],\"age\":0},\"modified_at\":\"2019-04-04 16:28:56.829\",\"create_at\":\"2019-04-04 16:28:56.829\",\"question\":{\"id\":2,\"creator\":{\"id\":1,\"username\":\"Admin\",\"email\":\"123456@123.com\",\"phonenumber\":\"1234657890123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"Admin\",\"last_name\":\"Admin\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatar_url\":\"---\",\"role\":null,\"age\":25},\"title\":\"腾讯的问题是什么？\",\"is_anonymous\":true,\"create_at\":\"2019-01-01T00:00:00.000+0000\",\"modified_at\":\"2019-01-01T00:00:00.000+0000\",\"rela_type\":1,\"rela_id\":37,\"body\":{\"braftEditorRaw\":{\"resourceIdList\":[],\"braftEditorRaw\":{\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"<在软件行业，操作系统平台就是那个八，其他的应用软件就是那个二。微软已经踩到了一次狗屎运，得到了软件行业80%的利润，现在，他所需要做的，就是保持住这个地位。但技术不是静止不动的，不断有新的技术生长出来，在成千上万种技术中，有一种会长成参天大树，利润无比丰厚，取代原来的技术平台，成为新的主流趋势。>\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{}},\"previewText\":\"<在此填入你的文字>\"},\"previewText\":\"在软件行业，操作系统平台就是那个八，其他的应用软件就是那个二。微软已经踩到了一次狗屎运，得到了软件行业80%的利润，现在，他所需要做的，就是保持住这个地位。但技术不是静止不动的，不断有新的技术生长出来，在成千上万种技术中，有一种会长成参天大树，利润无比丰厚，取代原来的技术平台，成为新的主流趋势。\",\"compiletype\":1}},\"id\":54},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }

//    @Test
//    public void testUserAttentions() throws Exception {
//        this.mvc.perform(
//                get
//                        (this.urlPrefix + "/users/1/attentions").param("type", "Question")
//
//                        .with(authGenerator.authentication())
//        )
//                .andDo(print())
//        ;
//    }
}

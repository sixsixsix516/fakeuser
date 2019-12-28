package com.sixsixsix516.fakeuser.spider;

import com.sixsixsix516.fakeuser.constant.SpiderConstant;
import com.sixsixsix516.fakeuser.pipeline.OicqPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Random;

/**
 * https://www.huiyi8.com/tx/ 爬虫
 *
 * @author sun 2019/12/28 18:36
 */
public class HuiyiSpider implements PageProcessor {


    private Site site = Site.me().setRetryTimes(3).setSleepTime(100)
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");


    @Override
    public void process(Page page) {
        Selectable url = page.getUrl();

        Html html = page.getHtml();
        // 1.处理首页
        if (SpiderConstant.HUIYIHOME.equals(url.get())) {
            List<String> all = html.xpath("//ul[@class='search-result-box clearfix']//img//@src").all();
            all.forEach(this::download);
        }
    }

    public void download(String urlString) {
        try {

            // 构造URL
            URL url = new URL(urlString);
            // 打开连接
            URLConnection con = url.openConnection();
            // 输入流
            InputStream is = con.getInputStream();
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            String filename = "D:\\server\\fakeuser\\fakeuser\\src\\main\\java\\com\\sixsixsix516\\fakeuser\\constant\\" + new Random().nextInt() + ".jpg";
            File file = new File(filename);
            FileOutputStream os = new FileOutputStream(file, true);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new HuiyiSpider())
                .addUrl(SpiderConstant.HUIYIHOME)
                .thread(1)
                .run();
    }
}

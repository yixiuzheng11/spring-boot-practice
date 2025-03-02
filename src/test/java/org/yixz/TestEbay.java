package org.yixz;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author raoshihong
 * @date 2025/2/9 1:44 PM
 */
public class TestEbay {

    @Test
    public void testebay(){
        // 创建一个模拟 Chrome 浏览器的 WebClient 实例
        final WebClient webClient = new WebClient(BrowserVersion.CHROME);

        // 配置 WebClient
        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常, 这里选择不需要
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常, 这里选择不需要
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);//是否启用CSS, 因为不需要展现页面, 所以不需要启用
        webClient.getOptions().setJavaScriptEnabled(true); //很重要，启用JS
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX

        HtmlPage page = null;

        Set<String> sellerInfo = Sets.newHashSet();
        for (int i=1;i<2;i++){
            try {
                // 访问百度主页
                page = webClient.getPage("https://www.ebay.com/sch/i.html?_nkw=DIY%E9%A6%96%E9%A5%B0&_sacat=0&_from=R40&rt=nc&_pgn="+i);

                // 获取所有<a>标签
                List<HtmlAnchor> anchors = page.getAnchors(); // 获取所有<a>标签作为HtmlAnchor对象列表

                // 获取所有的详情页面
                List<String> detailUrls = Lists.newArrayList();
                for (HtmlAnchor anchor : anchors) {
                    //System.out.println("Anchor URL: " + anchor.getHrefAttribute()); // 获取链接的href属性值
                    String href = anchor.getHrefAttribute();
                    if (StrUtil.startWith(href,"https://www.ebay.com/itm/")) {
                        detailUrls.add(href);
                    }
                }

                //System.out.println("detailUrls:"+ JSON.toJSONString(detailUrls));
                detailUrls = detailUrls.stream().distinct().collect(Collectors.toList());
                for (int j=0;j<detailUrls.size();j++){
                    try{
                        HtmlPage detailPage = webClient.getPage(detailUrls.get(j));

                        // 通过CSS选择器定位到特定的div元素，例如class为'my-div-class'的div元素
                        List<HtmlDivision> myDivs = detailPage.getByXPath(".//div[@class='x-sellercard-atf__info__about-seller']");

                        for (HtmlDivision myDiv:myDivs){
                            if (myDiv != null) {
                                // 定位到该div下的所有span元素，例如class为'my-span-class'的span元素
                                List<HtmlSpan> mySpans = myDiv.getByXPath(".//span[@class='ux-textspans ux-textspans--BOLD']");
                                for (HtmlSpan span : mySpans) {
                                    //System.out.println("detailPage-span:"+span.getTextContent()); // 输出span的文本内容
                                    sellerInfo.add(span.getTextContent());
                                }
                            } else {
                                System.out.println("指定的div未找到");
                            }
                        }
                    }catch (Exception e){

                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                webClient.close();
            }
        }

        if(CollectionUtil.isNotEmpty(sellerInfo)){
            FileUtil.writeUtf8Lines(sellerInfo,"D:\\ebay.txt");
        }
    }

}

package com.cos.newssave.util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cos.newssave.domain.News;

@Component
public class NaverCraw {

	int aidNum = 1;
	
	public List<News> collect10(){
		RestTemplate rt = new RestTemplate();
		List<News> newsList = new ArrayList<>();
		
		for (int i = 1; i < 11; i++) {
			String aid = String.format("%010d", aidNum);
			String url = "https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=100&oid=003&aid="+aid;
			String html = rt.getForObject(url, String.class);

			Document doc = Jsoup.parse(html);

			Element titleElement = doc.selectFirst("#articleTitle");
			Element timeElement = doc.selectFirst(".t11");
			String title = titleElement.text();
			String time = timeElement.text();
			
			//System.out.println(title);
			//System.out.println(time);
			
			News news = News.builder()
					.title(title)
					.time(time)
					.build();
			
			newsList.add(news);
			
			aidNum ++;
		}
		return newsList;
	}
}

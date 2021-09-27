package com.cos.newssave.batch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

// https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=100&oid=003&aid=0000000001
public class NaverCrawTest {

	int aidNum = 1;
	
	// @Test
	public void test1() {
		// HttpUrlConnection (자바기본), OkHttp, RestTemplate, Retrofit2
		// requests
		RestTemplate rt = new RestTemplate();

		String url = "https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=100&oid=003&aid=0000000001";

		String html = rt.getForObject(url, String.class);
		// System.out.println(html); // jsoup로 id : articleTitle 를 파싱해야함.

		// 1. jsoup 라이브러리 빌드 (mvn)
		// Document doc = Jsoup.connect(url).get();
		Document doc = Jsoup.parse(html);

		// 2. jsoup 기본 함수 약간 학습
		Element titleElement = doc.selectFirst("#articleTitle");
		String title = titleElement.text();

		// 3. 콘솔에 제목 출력
		System.out.println(title);
	}

	// @Test
	public void test2() {
		for (int i = 1; i < 11; i++) {
			System.out.println(String.format("%010d", i));
		}
	}

	@Test
	public void test3() {

		RestTemplate rt = new RestTemplate();
		List<NewsTest> nts = new ArrayList<>();
		
		for (int i = 1; i < 11; i++) {
			String aid = String.format("%010d", aidNum);
			String url = "https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=100&oid=003&aid="+aid;
			String html = rt.getForObject(url, String.class);

			Document doc = Jsoup.parse(html);

			Element titleElement = doc.selectFirst("#articleTitle");
			Element timeElement = doc.selectFirst(".t11");
			String title = titleElement.text();
			String time = timeElement.text();
			
			System.out.println(title);
			System.out.println(time);
			
			NewsTest nt = NewsTest.builder()
					.title(title)
					.time(time)
					.build();
			
			nts.add(nt);
			
			aidNum ++;
		}
		
		assertTrue(nts.size() == 10);
	}
}

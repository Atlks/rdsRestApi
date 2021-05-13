package rdsPKg;

import java.util.Base64;

/*
 * */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.alibaba.fastjson.JSONObject;

@SpringBootApplication
public class ApplicationCli {

//  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//      return application.sources(Application.class);
//  }

	public static void main(String[] args) throws Exception {
		String json = args[0];
		json = new String(Base64.getDecoder().decode(json), "gbk");
		JSONObject jo = JSONObject.parseObject(json);

		ConfigurableApplicationContext caContext1 = new SpringApplicationBuilder(ApplicationCli.class)
				.web(WebApplicationType.NONE).run(args);

		String meth = jo.getString("$mthd");
		if (meth.equals("set")) {
			StringRedisTemplate srt = (StringRedisTemplate) caContext1.getBean(StringRedisTemplate.class);
			srt.opsForValue().set(jo.getString("p1"), jo.getString("p2"));
		}else if (meth.equals("get")) {
			StringRedisTemplate srt = (StringRedisTemplate) caContext1.getBean(StringRedisTemplate.class);
		
			System.out.println(	srt.opsForValue().get(jo.getString("p1") ));
			return;
		}

		// srt.opsForValue().append("msg","hello");

		// srt.ops
		
		System.out.println("ff");

		// also can use in cfg file
		// no include in pom

	}

	@Configuration

	@ComponentScan(lazyInit = true)
	static class LocalConfig {
	}
}

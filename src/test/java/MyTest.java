import com.elan.Starter;
import com.elan.service.RedisService;
import com.elan.service.WeatherDataService;
import com.elan.util.LogUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Starter.class)
public class MyTest {
    @Autowired
    private RedisService redisService;

    @Autowired
    private WeatherDataService weatherDataService;

    @Test
    public void redisTest(){
        redisService.set("a","a1");
        System.out.println(redisService.get("a"));
    }

    @Test
    public void testLog(){
        LogUtil.error("hah",this.getClass());
    }

    @Test
    public void testExecutor()
    {
        weatherDataService.doGetWeahterAsync();
    }


    @Test
    public void hashTest(){
        Map<String,String> map=new HashMap<>();
        map.put("a879","1");
        map.put("2131","23");
        redisService.setHash("ccc",map);
        Map hash=redisService.getHash("ccc");
        redisService.getHash("ccc");
        if (hash!=null){
            System.out.println(hash.toString());
        }
    }
}

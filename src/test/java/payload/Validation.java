package payload;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Validation {
    @Test
    public void sumOfSourses(){
        int sum =0;
        JsonPath js = new JsonPath(payload.CourseData());
        int count = js.getInt("courses.size()");
        System.out.println("Số Lượng object  " + count);
        for(int i =0; i<count; i++){
            int price= js.getInt("courses[" + i+"].price");
            int copies= js.getInt("courses[" + i+"].copies");
            int amoount = price * copies;
            System.out.println(amoount);
            sum += amoount;
        }
        System.out.println(" Tổng là cần test  "+sum);
        int purchase = js.get("dashboard.purchaseAmount");
        System.out.println("Tổng bill là  " + purchase);
        Assert.assertEquals(sum,purchase);

//        if(sum ==purchase){
//            System.out.println("test passed");
//        }
//        else {
//            System.out.println("paiiiiiiiiiiiii");
//        }
    }
}

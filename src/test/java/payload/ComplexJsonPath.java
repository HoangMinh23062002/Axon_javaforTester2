package payload;

import io.restassured.path.json.JsonPath;

public class ComplexJsonPath {
    public static void main(String[] args) {
        JsonPath js = new JsonPath(payload.CourseData());
       int count = js.getInt("courses.size()");
        System.out.println(count);

        int totalAmount = js.get("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        String title = js.get("courses[1].title");
        System.out.println(title);

      for(int i =0; i < count; i++){
        String allTitle =  js.get("courses[" + i + "].title");
          System.out.println(allTitle);
          System.out.println(js.get("courses[" +i+ "].price").toString());
      }
        System.out.println("----------------");
      for(int i = 0; i< count; i++){
          String courset = js.get("courses["+i+"].title");
          if(courset.equalsIgnoreCase("RPA")){
              int tra = js.get("courses["+ i +"].copies");
              System.out.println(tra);
              //break;
          }
      }

    }
}

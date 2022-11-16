package exceptions.basic;

public class TryCatchSample {
    public String readDataFrom(Resource resource) {
        try {
            resource.open();
            String data = resource.read();
            resource.close();
            return data;
        }catch (Exception e){
            resource.close();
            return "someDefaultValue";
        }
    }
}

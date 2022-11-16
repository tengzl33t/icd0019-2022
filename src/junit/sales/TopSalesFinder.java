package junit.sales;

public class TopSalesFinder {

    private SalesRecordsFormatted[] registeredSales = new SalesRecordsFormatted[0];

//    public static void main(String[] args) {
//        TopSalesFinder tsf = new TopSalesFinder();
//        tsf.registerSale(new SalesRecord("p1", 20, 1));
//        tsf.registerSale(new SalesRecord("p2", 20, 1));
//        tsf.registerSale(new SalesRecord("p2", 20, 1));
//        tsf.registerSale(new SalesRecord("p1", 10, 1));
//        tsf.registerSale(new SalesRecord("p3", 50, 1));
//
//        String debugString = "===== Registered sales =====\n";
//        for (SalesRecordsFormatted i : tsf.registeredSales){
//            debugString += i.getIndex() + " " + i.getProductId() + " " + i.getProfit() + " " + i + "\n";
//        }
//        debugString += "===============";
//        System.out.println(debugString);
//        System.out.println(Arrays.toString(tsf.findItemsSoldOver(1)));
//
//    }

    public void registerSale(SalesRecord record) {
        // store sales record for later analyses by findItemsSoldOver()

        SalesRecordsFormatting formattingClass = new SalesRecordsFormatting();
        formattingClass.setList(registeredSales);
        formattingClass.appendValueToList(record);

        registeredSales = formattingClass.getList();

    }

    private static String[] appendValueToList(String[] integers, String value){
        int ind = 0;
        int listNewLength = integers.length + 1;
        String[] result = new String[listNewLength];

        for (String i : integers){
            result[ind] = i;
            ind++;
        }

        result[ind] = value;
        return result;
    }

    public String[] findItemsSoldOver(int amount) {
        String[] result = new String[0];

        for (SalesRecordsFormatted s : registeredSales){
            if (s.getProfit() > amount){
                result = appendValueToList(result, s.getProductId());
            }
        }

        return result;

        // find ids of records that sold over specified amount.

    }

}



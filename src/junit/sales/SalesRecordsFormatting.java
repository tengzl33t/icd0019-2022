package junit.sales;

public class SalesRecordsFormatting {

    public SalesRecordsFormatted[] innerList; // storage array

    public void appendValueToList(SalesRecord input){
        int listNewLength = innerList.length + 1; // increase length
        int listNewIndex = innerList.length; // use length as new index
        int countProfit = input.getProductPrice() * input.getItemsSold(); // multiply items count and their cost

        SalesRecordsFormatted[] workList = new SalesRecordsFormatted[innerList.length]; // create intermediate array

        if (innerList.length > 0) {
            if (ifValueInList(input)) { // if value already existed in list
                for (SalesRecordsFormatted i : innerList){ // copy older data to newer array
                    workList[i.getIndex()] = i;
                }

                SalesRecordsFormatted oldRecord = getValueFromList(input.getProductId());
                int newProfit = countProfit + oldRecord.getProfit(); // add new profit not older one
                SalesRecordsFormatted updatedRecord = new SalesRecordsFormatted(input.getProductId(), newProfit);

                updatedRecord.setIndex(oldRecord.getIndex()); // get new index from older record
                workList[oldRecord.getIndex()] = updatedRecord; // update older record with new data

            }else{
                workList = new SalesRecordsFormatted[listNewLength]; // update list with newer length
                for (SalesRecordsFormatted i : innerList){ // copy older data to newer array
                    workList[i.getIndex()] = i;
                }

                SalesRecordsFormatted formattedSale = new SalesRecordsFormatted(input.getProductId(), countProfit);
                formattedSale.setIndex(listNewIndex);

                workList[listNewIndex] = formattedSale;
            }
        }else{
            workList = new SalesRecordsFormatted[listNewLength]; // update list with newer length
            SalesRecordsFormatted formattedSale = new SalesRecordsFormatted(input.getProductId(), countProfit);
            formattedSale.setIndex(0);

            workList[0] = formattedSale;
        }

        innerList = workList; // save changes

    }

    public SalesRecordsFormatted[] getList(){
        SalesRecordsFormatted[] result = innerList;
        return result;
    }

    public void setList(SalesRecordsFormatted[] oldList){
        innerList = oldList;
    }

    private SalesRecordsFormatted getValueFromList(String productId){
        for (SalesRecordsFormatted i : innerList) {
            if (productId.equals(i.getProductId())){
                return i;
            }
        }
        return null;
    }

    private boolean ifValueInList(SalesRecord input){
        for (SalesRecordsFormatted i : innerList){
            if (i.getProductId().equals(input.getProductId())){
                return true;
            }
        }
        return false;
    }

}

package inheritance.pager;

import java.util.ArrayList;
import java.util.List;

//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.contains;

public class FilteringPagerBackup {

    private int pageNumber = -1; // begin to read
    private final SimplePager dataSource;
    private final int pageSize;
    private int storage = 0;

    public FilteringPagerBackup(SimplePager dataSource, int pageSize) {
        this.dataSource = dataSource;
        this.pageSize = pageSize;
    }

//    private int getSourcePagesCount() {
//        int page = 0;
//        while (dataSource.hasPage(page)) {
//            page++;
//        }
//        return page;
//    }
//
//    private int getSourcePageSize(){
//        int result = 0;
//        if (dataSource.hasPage(0)) {
//            result = dataSource.getPage(0).size();
//        }
//        return result;
//    }
//
//    private int getSourcePageSizeNotNull(int page){
//        int result = 0;
//        for (Integer i : dataSource.getPage(page)) {
//            if (i != null) {
//                result++;
//            }
//        }
//        return result;
//    }

    private List<Integer> getDataFromSource() {
        if(!dataSource.hasPage(0)){
            throw new IllegalStateException();
        }

        List<Integer> storage = new ArrayList<>();

        int requiredValues = pageSize * (pageNumber + 1);


        System.out.println("required: " + requiredValues);

        for (int i = 0; dataSource.hasPage(i); i++) {


            for (Integer element : dataSource.getPage(i)) {
                if (element != null) {
                    storage.add(element);
                    requiredValues--;
                }
                if (requiredValues == 0){
                    return storage;
                }
            }

        }


        return storage;
    }

    private List<Integer> getPage() {
        List<Integer> fullData = getDataFromSource();
        System.out.println("fulldata: " + fullData);

        int startPos = pageNumber * pageSize;
        int endPos = Math.min(startPos + pageSize, fullData.size());

        System.out.println(fullData.subList(startPos, endPos));
        return fullData.subList(startPos, endPos);
    }

    public List<Integer> getNextPage() {
        System.out.println("access next page");
        pageNumber++;
        return getPage();
    }

    public List<Integer> getCurrentPage() {
        System.out.println("access current page");
        return getPage();
    }

    public List<Integer> getPreviousPage() {
        System.out.println("access previous page");
        pageNumber--;
        return getPage();
    }
}

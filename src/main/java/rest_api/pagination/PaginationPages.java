package rest_api.pagination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaginationPages {

    private int page;
    private int limit;


    public PaginationPages(int page, int limit) {
        super();
        this.page = page;
        this.limit = limit;
    }

    public Map getPaginationPagesData(List array) {
        page -= 1;

        if (page < 0) page = 0;
        if (limit <= 0) limit = 1;

        List result = new ArrayList();

        float maxPage = array.size() / limit;

        if (page > maxPage) {
            page = (int) maxPage;
        }

        int i = page * limit;

        int total = i + limit;

        if (page > maxPage) {
            i = (int) maxPage;
        }

        while (i < total && i < array.size()) {
            result.add(array.get(i));
            i++;
        }

        Map resultMap = new HashMap<>();

        resultMap.put("totalPages", (int) (maxPage + 1));
        resultMap.put("documents", result);

        return resultMap;


    }

    @Override
    public String toString() {
        return "PaginationRequestBody [page=" + page + ", limit=" + limit + "]";
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }


}

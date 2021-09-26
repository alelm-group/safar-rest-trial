package rest_api.pagination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaginationRange {
    public int start;
    public int end;

    public PaginationRange(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public Map getPaginationRangeData(List array) {
        Map resultMap = new HashMap<>();
        List result = new ArrayList();
        if (end >= array.size()) end = array.size();

        if (start < 0 || start > end) {
            result.clear();
        } else {
            for (int i = start; i < end; i++) {
                result.add(array.get(i));
            }
        }
        resultMap.put("totalItems", array.size());
        resultMap.put("documents", result);
        return resultMap;
    }

}

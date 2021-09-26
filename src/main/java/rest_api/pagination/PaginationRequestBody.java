package rest_api.pagination;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PaginationRequestBody {
    private PaginationRange paginationRange;
    private PaginationPages paginationPage;

    public Map getPaginationData(List array) {
        if (Objects.isNull(paginationRange) && !Objects.isNull(paginationPage)) {
            return paginationPage.getPaginationPagesData(array);
        } else if (!Objects.isNull(paginationRange) && Objects.isNull(paginationPage)) {
            return paginationRange.getPaginationRangeData(array);
        } else {
            Map result = new HashMap();
            result.put("error", "vous devez specifier soit paginationRange[start,end] ou paginationPage[page,limit]");

            return result;
        }
    }

    public PaginationRange getPaginationRange() {
        return paginationRange;
    }

    public void setPaginationRange(PaginationRange paginationRange) {
        this.paginationRange = paginationRange;
    }

    public PaginationPages getPaginationPage() {
        return paginationPage;
    }

    public void setPaginationPage(PaginationPages paginationPage) {
        this.paginationPage = paginationPage;
    }


}

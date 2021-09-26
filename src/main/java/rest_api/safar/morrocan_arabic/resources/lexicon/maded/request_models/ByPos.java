package rest_api.safar.morrocan_arabic.resources.lexicon.maded.request_models;

import rest_api.pagination.PaginationRequestBody;

public class ByPos {
    private String pos;
    private PaginationRequestBody pagination;

    public ByPos(String pos, PaginationRequestBody pagination) {
        super();
        this.pos = pos;
        this.pagination = pagination;
    }

    public PaginationRequestBody getPagination() {
        return pagination;
    }

    public void setPagination(PaginationRequestBody pagination) {
        this.pagination = pagination;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }
}

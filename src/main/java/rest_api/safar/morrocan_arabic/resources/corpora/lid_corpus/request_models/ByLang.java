package rest_api.safar.morrocan_arabic.resources.corpora.lid_corpus.request_models;

import rest_api.pagination.PaginationRequestBody;

public class ByLang {
    private String lang;
    private PaginationRequestBody pagination;


    public ByLang(String lang, PaginationRequestBody pagination) {
        this.lang = lang;
        this.pagination = pagination;
    }

    public PaginationRequestBody getPagination() {
        return pagination;
    }

    public void setPagination(PaginationRequestBody pagination) {
        this.pagination = pagination;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

}

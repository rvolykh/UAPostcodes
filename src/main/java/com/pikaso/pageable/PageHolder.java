package com.pikaso.pageable;

import java.util.List;

public class PageHolder<T> {
    private int start;
    private int page;
    private List<T> source;
    private int lastPage;
    
    public PageHolder(){
        
    }
    
    public PageHolder(List<T> source) {
        this.source = source;
    }
    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public List<T> getSource() {
        return source;
    }
    public void setSource(List<T> source) {
        this.source = source;
    }
    public int getLastPage() {
        return lastPage;
    }
    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }
    
    
}

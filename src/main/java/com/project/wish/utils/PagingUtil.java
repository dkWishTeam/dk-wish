package com.project.wish.utils;

import lombok.Getter;

@Getter
public class PagingUtil {

    private long totalElements;     //총 요소 수
    private int totalPages;         //총 페이지 수
    private int pageNumber;         //현재 페이지
    private int pageSize;           //페이지 크기(한 페이지 당 보여줄 컨텐츠 수)
    private int totalPageGroups;    //총 페이지 그룹 수(총 20페이지를 5개씩 끊어서 보여준다면 페이지 그룹 수는 4)
    private int pageGroupSize = 5;  //페이지 그룹 크기(페이지를 5씩 끊어서 보여준다면 페이지 그룹 크기는 5)
    private int pageGroup;          //현재 페이지 그룹
    private int startPage;          //시작 페이지
    private int endPage;            //끝 페이지
    private boolean existPrePageGroup;      //이전 페이지 그룹이 존재하는 지 여부
    private boolean existNextPageGroup;     //다음 페이지 그룹이 존재하는 지 여부

    public PagingUtil(long totalElements, int totalPages, int pageNumber, int pageSize) {
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.pageNumber = pageNumber + 1;
        this.pageSize = pageSize;
        this.totalPageGroups = setTotalPageGroups();
        this.pageGroup = setPageGroup();
        this.startPage = setStartPage();
        this.endPage = setEndPage();
        this.existPrePageGroup = setExistPrePageGroup();
        this.existNextPageGroup = setExistNextPageGroup();
    }

    private int setTotalPageGroups() {
        if (this.totalPages % this.pageGroupSize == 0) {
            return this.totalPages / this.pageGroupSize;
        }
        return this.totalPages / this.pageGroupSize + 1;
    }

    private int setPageGroup() {
        if (this.pageNumber % this.pageGroupSize == 0) {
            return this.pageNumber / this.pageGroupSize;
        }
        return this.pageNumber / this.pageGroupSize + 1;
    }

    private int setStartPage() {
        return (this.pageGroup - 1) * this.pageGroupSize + 1;
    }

    private int setEndPage() {
        int endPage = this.pageGroup * this.pageGroupSize;
        if (this.totalPages < endPage) {
            return this.totalPages;
        }
        return endPage;
    }

    public boolean setExistPrePageGroup() {
        if (this.pageGroup > 1) {
            return true;
        }
        return false;
    }

    public boolean setExistNextPageGroup() {
        if (this.pageGroup < this.totalPageGroups) {
            return true;
        }
        return false;
    }
}
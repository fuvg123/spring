package com.multi.product;

import java.util.ArrayList;

public interface IProductService {
	ArrayList<ProductVO> productList();  // 상품 정보 전체 조회 (select)
	void insertProduct(ProductVO prd);    // 상품 정보 등록 (insert)
	void updateProduct(ProductVO prd);  // 상품 정보 수정 (update)
	void deleteProduct(String prdNo);      // 상품 정보 삭제 (delete)
	ProductVO productDetailView(String prdNo);  // 상품 상세 정보 조회 	
}

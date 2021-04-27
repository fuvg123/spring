package com.multi.product;

import java.util.ArrayList;

public interface IProductDAO {
	public ArrayList<ProductVO> productList(); // 전체 상품 조회 (select)
	public void insertProduct(ProductVO prd);   // 상품 정보 등록 (insert)
	public void updateProduct(ProductVO prd);  // 상품 정보 수정 (update)
	public void deleteProduct(String prdNo);     //상품 정보 삭제 (delete)
	public ProductVO productDetailView(String prdNo); // 상품 상세 정보 조회

}

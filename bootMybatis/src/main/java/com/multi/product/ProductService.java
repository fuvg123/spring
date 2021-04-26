package com.multi.product;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
	// MyBatis를 사용하는 경우
	@Autowired
	@Qualifier("IProductDAO")	
	IProductDAO dao;

	@Override
	public ArrayList<ProductVO> productList() {
		// DAO에게 요청한 결과를 받아서 Controller에게 반환
		return dao.productList();
	}

	@Override
	public void insertProduct(ProductVO prd) {
		dao.insertProduct(prd);
	}

	@Override
	public void updateProduct(ProductVO prd) {
		dao.updateProduct(prd);
	}

	@Override
	public void deleteProduct(String prdNo) {
		dao.deleteProduct(prdNo);
	}

	@Override
	public ProductVO productDetailView(String prdNo) {
		return dao.productDetailView(prdNo);
	}

}

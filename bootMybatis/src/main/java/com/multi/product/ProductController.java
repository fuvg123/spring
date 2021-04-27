package com.multi.product;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
	@Autowired
	ProductService service;
	
	// 시작 시 index 페이지 열기
	@RequestMapping("/")
	public String viewIndex() {
		return "index";
	}
	
	// 전체 상품 조회
	@RequestMapping("/product/productListView")
	public String viewProductList(Model model) {
		ArrayList<ProductVO> prdList =  service.productList();
		model.addAttribute("prdList", prdList);
		return "product/productListView";  
		// 뷰페이지 이름 반환 (views 폴더 안의 product 폴더 안의 productListView.jsp
	}
	
	// 상품 등록 화면으로 이동
	@RequestMapping("/product/productNewForm")
	public String viewProductForm() {
		return "product/productNewForm"; //productNewForm.jsp 페이지 이름
	}
	
	// 상품 등록 : 입력 데이터를 DB에 저장 (insert 작업 처리)
	@RequestMapping("/product/insert")
	public String insertProduct(ProductVO prd) {
		service.insertProduct(prd);
		return "redirect:./productListView"; // productListView 포워딩
		//return "product/productListView";
	}
	
	// 상품 상제 정보 조회
	@RequestMapping("/product/productDetailView/{prdNo}")
	public String detailViewProduct(@PathVariable String prdNo, Model model) {
		ProductVO prd = service.productDetailView(prdNo);
		model.addAttribute("prd", prd);
		return "product/productDetailView"; //productDetailView.jsp 상품 상제 정보 페이지
	}
	
	// 상품 정보 수정 화면으로 이동 (데이터를 수정할 수 있도록 상세 정보를 화면에(입력란에) 출력
	@RequestMapping("/product/updateForm/{prdNo}")
	public String updateProductForm(@PathVariable String prdNo, Model model) {
		ProductVO prd = service.productDetailView(prdNo);
		model.addAttribute("prd", prd);
		return "product/productUpdateForm"; // productUpdateForm.jsp 상품 정보 수정 폼
	}
	
	// 상품 정보 수정 (데이터베이스 업데이트 작업)
	@RequestMapping("/product/update")
	public String updateProduct(ProductVO prd) {
		service.updateProduct(prd);
		return "redirect:./productListView"; // 업데이트 후에 전체 상품 조회 페이지로 포워딩
		//return "redirect:/product/productListView";
	}
	
	// 상품 정보 삭제
	@RequestMapping("/product/delete/{prdNo}")
	public String deleteProduct(@PathVariable String prdNo) {
		service.deleteProduct(prdNo);
		//return "redirect:./productListView"; // 상품 정보 삭제 후 상품 조회 페이지로 포워딩
		return "redirect:/product/productListView";
	}	
}














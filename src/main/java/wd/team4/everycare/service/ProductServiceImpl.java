package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.*;
import wd.team4.everycare.dto.ImageDTO;
import wd.team4.everycare.dto.MultipartFileDTO;
import wd.team4.everycare.dto.UploadFile;
import wd.team4.everycare.dto.product.*;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.*;
import wd.team4.everycare.repository.query.OrderProductQueryRepository;
import wd.team4.everycare.repository.query.ProductQueryRepository;
import wd.team4.everycare.service.interfaces.ProductService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final FileStoreService fileStoreService;
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProducImageRepository producImageRepository;
    private final WishListRepository wishListRepository;
    private final ProductQueryRepository productQueryRepository;
    private final StoreRepository storeRepository;
    private final OrderProductQueryRepository orderProductQueryRepository;

    @Override
    public List<MemberProductListViewDTO> webFindAll(Store store) {

        List<Product> products = productRepository.findAllByStore(store);
        List<MemberProductListViewDTO> MemberProductsViewDTOs = new ArrayList<>();

        if (products.isEmpty()) {
            return null;
        }

        products.stream().map(product -> product.toMemberProductsViewDTO()).forEach(MemberProductsViewDTOs::add);

        return MemberProductsViewDTOs;
    }

    @Override
    public ResponseEntity<MyResponse> save(@AuthenticationPrincipal PrincipalDetails principalDetails, ProductFormDTO productFormDTO) throws IOException {

        LocalDateTime time = LocalDateTime.now();
        UploadFile attachFile = fileStoreService.storeFile(productFormDTO.getAttachFile());
        List<UploadFile> attachFiles = fileStoreService.storeFiles(productFormDTO.getAttachFiles());
        Store store = principalDetails.getStore();
        Optional<ProductCategory> categoryEntity = productCategoryRepository.findById(productFormDTO.getProductCategory());
        ProductCategory category = categoryEntity.orElse(null);

        Product product = productFormDTO.toProduct();
        product.saveTime(time);
        product.saveStore(store);
        product.saveImage(attachFile);
        if (category != null) {
            product.saveProductCategory(category);
        } else {
            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.BAD_REQUEST)
                    .message("카테고리를 등록하세요")
                    .build();

            return new ResponseEntity<MyResponse>(body, HttpStatus.BAD_REQUEST);
        }
        productRepository.save(product);

        for (UploadFile file : attachFiles) {
            ProductImage productImage = ProductImage.builder()
                    .uploadFileName(file.getUploadFileName())
                    .storeFileName(file.getStoreFileName())
                    .product(product)
                    .build();
            producImageRepository.save(productImage);
        }

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공했슴다~")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> findAll() {

        List<Product> products = productRepository.findAllByIsSale(1);

        List<ProductListViewDTO> productListViewDTOs = new ArrayList<>();

        if (products.isEmpty()) {
            return null;
        }

        for (Product product : products) {
            ProductListViewDTO productListViewDTO = ProductListViewDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .storeFileName(product.getStoreFileName())
                    .createdAt(product.getCreatedAt())
                    .store(product.getStore().toNameDTO())
                    .productCategory(product.getProductCategory().toDTO())
                    .build();
            productListViewDTOs.add(productListViewDTO);
        }

        MyResponse<List<ProductListViewDTO>> body = MyResponse.<List<ProductListViewDTO>>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(productListViewDTOs)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<MyResponse> findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        Product productEntity = product.orElse(null);

        Long productEntityId = productEntity.getId();
        List<ProductImage> productImages = producImageRepository.findAllByProductId(productEntityId);

        List<ImageDTO> productImageDTOs = new ArrayList<>();
        for (ProductImage productImage : productImages) {
            ImageDTO imageDTO = ImageDTO.builder()
                    .id(productImage.getId())
                    .storeFileName(productImage.getStoreFileName())
                    .build();
            productImageDTOs.add(imageDTO);
        }

        ProductDetailViewDTO productDetaileViewDTO = ProductDetailViewDTO.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .storeFileName(productEntity.getStoreFileName())
                .comment(productEntity.getComment())
                .isSale(productEntity.getIsSale())
                .createdAt(productEntity.getCreatedAt())
                .store(productEntity.getStore().toNameDTO())
                .productCategory(productEntity.getProductCategory().toDTO())
                .imagesDTOs(productImageDTOs)
                .build();

        MyResponse<ProductDetailViewDTO> body = MyResponse.<ProductDetailViewDTO>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(productDetaileViewDTO)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> findAll(Store store) {
        List<Product> products = productRepository.findAllByStore(store);
        List<MemberProductListViewDTO> memberProductListViewDTOs = new ArrayList<>();

        if (products.isEmpty()) {
            return null;
        }

        for (Product product : products) {
            MemberProductListViewDTO memberProductListViewDTO = MemberProductListViewDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .inventoryQuantity(product.getInventoryQuantity())
                    .isSale(product.getIsSale())
                    .createdAt(product.getCreatedAt())
                    .uploadFileName(product.getUploadFileName())
                    .storeFileName(product.getStoreFileName())
                    .store(product.getStore().toNameDTO())
                    .productCategory(product.getProductCategory().toDTO())
                    .build();
            memberProductListViewDTOs.add(memberProductListViewDTO);
        }

        MyResponse<List<MemberProductListViewDTO>> body = MyResponse.<List<MemberProductListViewDTO>>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(memberProductListViewDTOs)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> productFindById(Long id) {

        Optional<Product> product = productRepository.findById(id);
        Product productEntity = product.orElse(null);

        Long productEntityId = productEntity.getId();
        List<ProductImage> productImages = producImageRepository.findAllByProductId(productEntityId);

        List<ImageDTO> productImageDTOs = new ArrayList<>();
        for (ProductImage productImage : productImages) {
            ImageDTO productImageDTO = ImageDTO.builder()
                    .id(productImage.getId())
                    .uploadFileName(productImage.getUploadFileName())
                    .storeFileName(productImage.getStoreFileName())
                    .build();
            productImageDTOs.add(productImageDTO);
        }

        ProductDetailViewDTO productDetaileViewDTO = ProductDetailViewDTO.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .inventoryQuantity(productEntity.getInventoryQuantity())
                .storeFileName(productEntity.getStoreFileName())
                .comment(productEntity.getComment())
                .isSale(productEntity.getIsSale())
                .createdAt(productEntity.getCreatedAt())
                .store(productEntity.getStore().toNameDTO())
                .productCategory(productEntity.getProductCategory().toDTO())
                .imagesDTOs(productImageDTOs)
                .build();

        MyResponse<ProductDetailViewDTO> body = MyResponse.<ProductDetailViewDTO>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(productDetaileViewDTO)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> updateProduct(Long id, ProductFormDTO productFormDTO) throws IOException {

        Optional<Product> product = productRepository.findById(id);
        Product productEntity = product.orElse(null);
        Long productCategoryId = productFormDTO.getProductCategory();
        Optional<ProductCategory> productCategory = productCategoryRepository.findById(productCategoryId);
        ProductCategory productCategoryEntity = productCategory.orElse(null);
        if (productCategory != null) {
            productEntity.saveProductCategory(productCategoryEntity);
        }
        productEntity.updateProduct(productFormDTO);

        if (productFormDTO.getAttachFile() != null) {
            UploadFile attachFile = fileStoreService.storeFile(productFormDTO.getAttachFile());
            productEntity.saveImage(attachFile);
        }

        if (productFormDTO.getAttachFiles() != null) {

            List<UploadFile> attachFiles = fileStoreService.storeFiles(productFormDTO.getAttachFiles());

            for (UploadFile file : attachFiles) {
                ProductImage productImage = ProductImage.builder()
                        .uploadFileName(file.getUploadFileName())
                        .storeFileName(file.getStoreFileName())
                        .product(productEntity)
                        .build();
                producImageRepository.save(productImage);
            }
        }

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> deleteProduct(Long id) {

        wishListRepository.deleteByProductId(id);
        producImageRepository.deleteByProductId(id);

        productRepository.deleteById(id);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> saveImage(Long id, MultipartFileDTO imageDTO) throws IOException {

        List<UploadFile> attachFiles = fileStoreService.storeFiles(imageDTO.getAttachFiles());

        Optional<Product> product = productRepository.findById(id);
        Product productEntity = product.orElse(null);

        for (UploadFile file : attachFiles) {

            ProductImage productImage = ProductImage.builder()
                    .uploadFileName(file.getUploadFileName())
                    .storeFileName(file.getStoreFileName())
                    .product(productEntity)
                    .build();

            producImageRepository.save(productImage);
        }

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> removeImage(Long id) {

        producImageRepository.deleteById(id);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> findAllByName(String name) {

        List<Product> products = productQueryRepository.findAllByName(name);

        List<ProductListViewDTO> productListViewDTOs = new ArrayList<>();

        if (products.isEmpty()) {
            return null;
        }

        for (Product product : products) {
            ProductListViewDTO productListViewDTO = ProductListViewDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .storeFileName(product.getStoreFileName())
                    .createdAt(product.getCreatedAt())
                    .store(product.getStore().toNameDTO())
                    .productCategory(product.getProductCategory().toDTO())
                    .build();
            productListViewDTOs.add(productListViewDTO);
        }

        MyResponse<List<ProductListViewDTO>> body = MyResponse.<List<ProductListViewDTO>>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(productListViewDTOs)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> findAllByCategory(Long categoryId) {
        List<Product> findByCategory = productQueryRepository.findAllByCategory(categoryId);

        if(findByCategory.isEmpty()){
            return null;
        }else{
            List<MemberProductListViewDTO> productListViewDTOs = new ArrayList<>();
            findByCategory.stream().map(product -> product.toMemberProductsViewDTO()).forEach(productListViewDTOs::add);

            MyResponse body = MyResponse.builder()
                    .body(productListViewDTOs)
                    .header(StatusEnum.OK)
                    .message("카테고리별 상품 조회")
                    .build();
            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<MyResponse> findBestProducts() {
        List<Product> products = productQueryRepository.findBestProducts();

        List<ProductListViewDTO> productListViewDTOs = new ArrayList<>();

        if (products.isEmpty()) {
            return null;
        }

        for (Product product : products) {
            ProductListViewDTO productListViewDTO = ProductListViewDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .storeFileName(product.getStoreFileName())
                    .createdAt(product.getCreatedAt())
                    .store(product.getStore().toNameDTO())
                    .productCategory(product.getProductCategory().toDTO())
                    .build();
            productListViewDTOs.add(productListViewDTO);
        }

        MyResponse<List<ProductListViewDTO>> body = MyResponse.<List<ProductListViewDTO>>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(productListViewDTOs)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }
}

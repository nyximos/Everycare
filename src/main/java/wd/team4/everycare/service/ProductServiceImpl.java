package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.Product;
import wd.team4.everycare.domain.ProductCategory;
import wd.team4.everycare.domain.ProductImage;
import wd.team4.everycare.domain.Store;
import wd.team4.everycare.dto.UploadFile;
import wd.team4.everycare.dto.product.MemberProductsViewDTO;
import wd.team4.everycare.dto.product.ProductDetaileViewDTO;
import wd.team4.everycare.dto.product.ProductFormDTO;
import wd.team4.everycare.dto.product.ProductListViewDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.dto.store.StoreFormDTO;
import wd.team4.everycare.repository.ProductCategoryRepository;
import wd.team4.everycare.repository.ProductRepository;
import wd.team4.everycare.service.interfaces.ProductService;

import java.io.IOException;
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

    @Override
    public List<MemberProductsViewDTO> webFindAll(Store store) {

        List<Product> products = productRepository.findAllByStore(store);
        List<MemberProductsViewDTO> MemberProductsViewDTOs = new ArrayList<>();

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
            ProductImage.builder()
                    .uploadFileName(file.getUploadFileName())
                    .storeFileName(file.getUploadFileName())
                    .product(product);
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
            System.out.println("product.getIsSale() = " + product.getIsSale());
            ProductListViewDTO productListViewDTO = ProductListViewDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .uploadFileName(product.getUploadFileName())
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
        ProductDetaileViewDTO productDetaileViewDTO = ProductDetaileViewDTO.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .storeFileName(productEntity.getStoreFileName())
                .comment(productEntity.getComment())
                .isSale(productEntity.getIsSale())
                .createdAt(productEntity.getCreatedAt())
                .store(productEntity.getStore().toNameDTO())
                .productCategory(productEntity.getProductCategory().toDTO())
                .build();

        MyResponse<ProductDetaileViewDTO> body = MyResponse.<ProductDetaileViewDTO>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(productDetaileViewDTO)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }
}

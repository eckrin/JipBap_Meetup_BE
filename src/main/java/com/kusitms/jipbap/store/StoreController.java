package com.kusitms.jipbap.store;

import com.kusitms.jipbap.common.response.CommonResponse;
import com.kusitms.jipbap.security.Auth;
import com.kusitms.jipbap.security.AuthInfo;
import com.kusitms.jipbap.store.dto.RegisterStoreRequestDto;
import com.kusitms.jipbap.store.dto.SearchStoreResponseDto;
import com.kusitms.jipbap.store.dto.StoreDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final int PAGESIZE = 10;
    private final StoreService storeService;

    @Operation(summary = "가게 등록하기")
    @PostMapping
    public CommonResponse<String> registerStore(@Auth AuthInfo authInfo, @Valid @RequestBody RegisterStoreRequestDto dto) {
        storeService.registerStore(authInfo.getEmail(), dto);
        return new CommonResponse<>("가게 등록 완료");
    }

    /**
     * 가게 검색 api - 페이지네이션 적용
     * @param keyword: 검색 키워드 (키워드가 포함한 가게를 검색한다)
     * @param sortField: 검색 기준 (추천-bookmark, 후기-review, 평점-rate, 가격-price, 최신-id)
     *                 (추천순: 가게 즐겨찾기 개수 순서, 후기: 가게에 속한 주문에 달린 리뷰 수)
     * @param sortDirection: 정렬 기준 (ASC, DESC)
     * @param lastId: 결과 리스트에서 포함되어도 되는 id의 최대값
     * @return Slice<?>: 슬라이스 단위
     */
    @Operation(summary = "가게 검색")
    @GetMapping
    public CommonResponse<Slice<SearchStoreResponseDto>> searchStore(
            @Auth AuthInfo authInfo,
            @RequestParam(required = false) String keyword,
            @RequestParam String sortField,
            @RequestParam String sortDirection,
            @RequestParam(required = false) Long lastId
    ) {
        Sort sort;
        if ("asc".equals(sortDirection) || "ASC".equals(sortDirection)) {
            sort = Sort.by(Sort.Direction.ASC, sortField);
        } else {
            sort = Sort.by(Sort.Direction.DESC, sortField);
        }
        Pageable pageable = PageRequest.of(0, PAGESIZE, sort);
        return new CommonResponse<>(storeService.searchStoreList(authInfo.getEmail(), pageable, keyword, sortField, sortDirection, lastId));
    }
}

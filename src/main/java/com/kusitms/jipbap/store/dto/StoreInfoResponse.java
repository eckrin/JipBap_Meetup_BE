package com.kusitms.jipbap.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreInfoResponse {
    private StoreInfoDto storeInfoDto;
    private Boolean isBookmarked;
    private Boolean isFoodChangeable;
}
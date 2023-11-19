package com.kusitms.jipbap.food;

import com.kusitms.jipbap.common.entity.DateEntity;
import com.kusitms.jipbap.store.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "tb_food")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Food extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id; //고유 pk

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "food_name")
    private String name;

    private Long dollarPrice;
    private Long canadaPrice;
    private String image;
    private String description;
    private String foodPackage; // 배달포장 모두 가능, 배달 가능, 포장 가능
    private String informationDescription; // 상품 정보 설명(이미지)
    private String ingredient; //구성 성분(텍스트)
    private Long recommendCount;

}
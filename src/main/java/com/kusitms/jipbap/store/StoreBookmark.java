package com.kusitms.jipbap.store;

import com.kusitms.jipbap.common.entity.DateEntity;
import com.kusitms.jipbap.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "tb_store_bookmark")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreBookmark extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="store_bookmark_id")
    private Long id; //고유 pk

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Store store;



}
package cn.com.kai.service;

import cn.com.kai.domain.Shop;

public interface ShopService {
    Shop selectById(Long id);

    boolean update(Shop shop);

}

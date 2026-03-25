package com.irvings.controller;

import com.irvings.menu.MenuItem;
import com.irvings.menu.CustomizationOption;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    // 获取完整菜单
    @GetMapping("/list")
    public List<MenuItem> getMenu() {
        return MenuItem.getStubMenu();
    }

    // 修改商品可用性
    @PutMapping("/{itemId}/availability")
    public void setAvailability(@PathVariable String itemId, @RequestParam boolean available) {
        MenuItem.getStubMenu().stream()
                .filter(item -> item.getItemId().equals(itemId))
                .findFirst()
                .ifPresent(item -> item.setAvailability(available));
    }

    // 获取商品定制选项
    @GetMapping("/{itemId}/customization")
    public List<CustomizationOption> getCustomization(@PathVariable String itemId) {
        return MenuItem.getStubMenu().stream()
                .filter(item -> item.getItemId().equals(itemId))
                .findFirst()
                .map(MenuItem::getCustomizationOptions)
                .orElseThrow(() -> new IllegalArgumentException("商品不存在：" + itemId));
    }
}

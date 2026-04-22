package com.irvings.controller;

import com.irvings.rewards.Reward;
import com.irvings.rewards.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    // 获取所有可用奖励
    @GetMapping("/list")
    public List<Reward> getAllRewards() {
        return rewardService.getAllRewards();
    }

    // 根据 ID 获取奖励详情
    @GetMapping("/{rewardId}")
    public Reward getRewardById(@PathVariable String rewardId) {
        Reward reward = rewardService.getRewardById(rewardId);
        if (reward == null) {
            throw new IllegalArgumentException("Reward not found: " + rewardId);
        }
        return reward;
    }

    // 兑换奖励
    @PostMapping("/{rewardId}/redeem")
    public boolean redeemReward(@PathVariable String rewardId, @RequestParam String userId) {
        return rewardService.redeemReward(rewardId, userId);
    }
}

package com.irvings.rewards;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardService {

    // 和 data.sql 里的初始化数据完全一致
    private final Map<String, Reward> rewardStore = new HashMap<>();

    public RewardService() {
        // 初始化默认奖励
        rewardStore.put("free-coffee",
                new Reward("free-coffee", "Free Hot or Iced Coffee", 50,
                        "Redeem for a brewed coffee or iced tea size of your choice.",
                        "REWARD-FREE-COFFEE"));

        rewardStore.put("free-bagel",
                new Reward("free-bagel", "Free Bagel", 75,
                        "Pick any classic Irving's bagel once you unlock it.",
                        "REWARD-FREE-BAGEL"));

        rewardStore.put("free-drink-or-bagel",
                new Reward("free-drink-or-bagel", "Free Drink or Bagel", 100,
                        "Use on a favorite drink or bagel at the full reward tier.",
                        "REWARD-FREE-DRINK-OR-BAGEL"));

        System.out.println("[RewardService] Default rewards loaded");
    }

    // 获取所有可用奖励
    public List<Reward> getAllRewards() {
        return rewardStore.values().stream().collect(Collectors.toList());
    }

    // 根据 ID 获取奖励
    public Reward getRewardById(String rewardId) {
        return rewardStore.get(rewardId);
    }

    // 兑换奖励（后续集成到订单模块时扩展）
    public boolean redeemReward(String rewardId, String userId) {
        Reward reward = rewardStore.get(rewardId);
        if (reward == null) return false;
        System.out.println("[RewardService] Redeemed reward: " + reward.getName() + " for user: " + userId);
        return true;
    }
}
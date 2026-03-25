package com.irvings.pickup;

import java.util.*;

public class PickupQueue {
    private final Map<String, PickupStatus> statusByOrder = new HashMap<>();
    private final Deque<String> queue = new ArrayDeque<>();

    public void enqueue(String orderId) {
        System.out.println("[PickupQueue] enqueue(" + orderId + ")");
        if (!statusByOrder.containsKey(orderId)) {
            queue.addLast(orderId);
            statusByOrder.put(orderId, PickupStatus.IN_QUEUE);
        }
    }

    public PickupStatus getStatus(String orderId) {
        System.out.println("[PickupQueue] getStatus(" + orderId + ")");
        return statusByOrder.get(orderId);
    }

    public void markReady(String orderId) {
        System.out.println("[PickupQueue] markReady(" + orderId + ")");
        if (statusByOrder.containsKey(orderId)) statusByOrder.put(orderId, PickupStatus.READY);
    }

    public void markPickedUp(String orderId) {
        System.out.println("[PickupQueue] markPickedUp(" + orderId + ")");
        if (statusByOrder.containsKey(orderId)) {
            statusByOrder.put(orderId, PickupStatus.PICKED_UP);
            queue.remove(orderId);
        }
    }

    public List<String> getQueueSnapshot() {
        return new ArrayList<>(queue);
    }
}


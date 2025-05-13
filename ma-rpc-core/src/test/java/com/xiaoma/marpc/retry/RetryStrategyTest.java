package com.xiaoma.marpc.retry;
import com.xiaoma.marpc.model.RpcResponse;
import org.junit.Test;


/**
 * Class Name: RetryStrategyTest
 * Description:重试策略测试
 * Created on: 2025/5/13
 * Author: xiaoma
 */
public class RetryStrategyTest {

    RetryStrategy retryStrategy = new FixedIntervalRetryStrategy();

    @Test
    public void doRetry() {
        try {
            RpcResponse rpcResponse = retryStrategy.doRetry(() -> {
                System.out.println("测试重试");
                throw new RuntimeException("模拟重试失败");
            });
            System.out.println(rpcResponse);
        } catch (Exception e) {
            System.out.println("重试多次失败");
            e.printStackTrace();
        }
    }
}
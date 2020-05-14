package com.study.distribute.lock.sample;

import java.util.concurrent.locks.Lock;

import com.study.distribute.lock.zk.ZKDistributeLock;

public class OrderServiceImplWithLock implements OrderService {

	private static OrderCodeGenerator ocg = new OrderCodeGenerator();

	private Lock lock = new ZKDistributeLock("/order_lock");

	// 创建订单接口
	@Override
	public void createOrder() {

		String orderCode = null;
		try {
			lock.lock();
			// 获取订单号
			orderCode = ocg.getOrderCode();

		} finally {
			lock.unlock();
		}

		System.out.println(Thread.currentThread().getName() + " =============>" + orderCode);

		// ……业务代码，此处省略100行代码

	}

}

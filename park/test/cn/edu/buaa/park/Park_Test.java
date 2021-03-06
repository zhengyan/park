package cn.edu.buaa.park;

import org.junit.Assert;
import org.junit.Test;

/**
 * 停车场测试类
 * @author 巩晓冬
 * @date 2012-12-9
 * @version 1.0
 */
public class Park_Test {
    /**
     * 停车场停一辆车时，空车位将减一
     */
    @Test
    public void num_should_be_sub_1_when_in_a_car() {
        final int num = 20;
        Park park = new Park(num);
        park.in(new Car());
        Assert.assertEquals(park.getEmptyNum(), num - 1);
    }

    /**
     * 停车场取出一辆车时，空车位将加一
     */
    @Test
    public void num_should_be_add_1_when_out_a_car() {
        final int num = 20;
        Park park = new Park(num);
        Car car = new Car();
        Ticket ticket = park.in(car);
        Assert.assertEquals(park.getEmptyNum(), num - 1);
        park.out(ticket);
        Assert.assertEquals(park.getEmptyNum(), num);
    }

    /**
     * 停车场已满时停车将引发异常
     */
    @Test(expected = ParkException.class)
    public void in_a_car_when_park_is_full() {
        final int num = 20;
        Park park = new Park(num);
        for(int i = 0; i <= num; i ++) {
            park.in(new Car(String.valueOf(i)));
        }
    }

    /**
     * 停车场为空时取车将引发异常
     */
    @Test(expected = ParkException.class)
    public void out_a_car_when_park_is_empty() {
        Park park = new Park(10);
        park.out(new Ticket());
    }

    /**
     * 使用正确的票据取车时，取出的车即是原来停的车
     */
    @Test
    public void out_by_right_ticket_when_in_a_car() {
        Park park = new Park(10);
        Car car = new Car();
        Ticket ticket = park.in(car);
        Assert.assertSame(car, park.out(ticket));
    }

    /**
     * 当用错误的票据取车时将引发异常
     */
    @Test(expected = ParkException.class)
    public void out_by_wrong_ticket_when_in_a_car() {
        Park park = new Park(10);
        park.in(new Car("京A123456"));
        park.out(new Ticket("京B123456"));
    }

    /**
     * 当用正确的票据取两次车时将引发异常
     */
    @Test(expected = ParkException.class)
    public void out_by_right_ticket_twice_when_in_a_car() {
        Park park = new Park(10);
        Car car = new Car();
        Ticket ticket = park.in(car);
        Assert.assertSame(car, park.out(ticket));
        park.out(ticket);
    }
    
}
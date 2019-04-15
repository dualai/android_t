package com.diabin.chain;

public class Boss extends Manager {

    public Boss() {
        super(Manager.BOSS_MANAGER);
    }

    @Override
    protected void response(Staff staff) {
        System.out.println("员工问老板");
        System.out.println(staff.getRequest());
        System.out.println("老板的回答是：没问题");
        System.out.println("=====================");
    }
}

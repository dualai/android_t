package com.diabin.chain;

public class OrdinaryManager extends Manager{
    public OrdinaryManager() {
        super(Manager.ORDINARY_MANAGER);
    }

    @Override
    protected void response(Staff staff) {
        System.out.println("员工问普通经理");
        System.out.println(staff.getRequest());
        System.out.println("普通经理的回答是：没问题");
        System.out.println("=====================");
    }
}

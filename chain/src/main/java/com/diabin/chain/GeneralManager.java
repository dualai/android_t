package com.diabin.chain;

public class GeneralManager extends Manager {
    public GeneralManager() {
        super(Manager.GENERAL_MANAGER);
    }

    @Override
    protected void response(Staff staff) {
        System.out.println("员工问总经理");
        System.out.println(staff.getRequest());
        System.out.println("总经理的回答是：没问题");
        System.out.println("=====================");
    }
}

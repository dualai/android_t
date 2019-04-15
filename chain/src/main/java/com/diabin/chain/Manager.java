package com.diabin.chain;

public abstract class Manager {
    public final static int BOSS_MANAGER = 1;
    public final static int GENERAL_MANAGER = 2;
    public final static int ORDINARY_MANAGER = 3;

    //能处理事件的级别
    private int level = 0;

    //下一个责任人是谁
    private Manager nextManager;

    public void setNextManager(Manager nextManager) {
        this.nextManager = nextManager;
    }

    public Manager(int level) {
        this.level = level;
    }

    //员工请求加薪
    public final void request(Staff staff) {
        if (staff.getLevel() == level) {
            this.response(staff);
        } else {
            if (nextManager != null) {
                nextManager.request(staff);
            } else {
                System.out.println("要求加薪太多,都不同意");
                System.out.println("========================");
            }
        }
    }

    //子类相应事件
    protected abstract void response(Staff staff);


}

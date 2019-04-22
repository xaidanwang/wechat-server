package com.github.aidan.netty.java.socket;

import java.util.List;

/**
 * @author wang yi fei
 * @date 2019/3/8 14:07
 */
public interface ICounter {

    void hit();
    StatData getStat();
}

class StatData{

    public int last10SencondAverage;

    public int last10MinuteAverage;

    public int last60MinuteAverage;

    public List<Integer> last60SensondAverage;
}

class Test implements ICounter{

    private static int count;

    private StatData statData;

    @Override
    public void  hit() {
        hit2();
    }

    @Override
    public StatData getStat() {
        return statData;
    }

    public synchronized void hit2(){
        count++;
    }

    public int getlast10SencondAverage(){

        return statData.last10SencondAverage;
    }

}

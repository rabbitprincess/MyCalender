package com.example.user.mycalender;

public class myDatabase {
    private static myDatabase data = new myDatabase();
    private myDatabase(){};
    static myDatabase getInstance(){ return data; }//싱글톤 생성.
    private String[][] Database = new String[13][32];
    private int tap = 0;
    public void initData(int month, int date, String data){
        this.Database[month][date] = data;
    }
    public String returnMonthData(int Month){
        int flag = 0;
        for(int i=0;i<31;i++){
            if(this.Database[Month][i] != null){ flag = 1; break;}
        }
        if(flag==0) return "이번 달은 일정이 없습니다.\n\n일정을 추가해보세요!"; //데이터가 어디에도 없다.
        else return "이번 달은 일정이 있습니다.\n\n일정을 확인해보세요!";//데이터가 존재한다.
    }
    public String returnDayData(int month,int date){
        if(this.Database[month][date] == null) return "오늘은 일정이 없습니다.";
        else return "오늘은 일정이 있습니다.\n\n"+this.Database[month][date];
    }
    public void setTap(int Tap){
        this.tap = Tap;
    }
    public int getTap(){
        return this.tap;
    }
}

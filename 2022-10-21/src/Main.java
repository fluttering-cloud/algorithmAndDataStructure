import java.util.*;

public class Main {

    /**
     * 货柜任务类
     * 【无需改动】
     */
    static class CounterTask {
        //货柜名称
        String counterName;
        //待做配送任务数
        Integer taskNum;

        public CounterTask() {
        }

        public Integer getTaskNum() {
            return taskNum;
        }

        public void setTaskNum(Integer taskNum) {
            this.taskNum = taskNum;
        }


        public String getCounterName() {
            return counterName;
        }

        public void setCounterName(String counterName) {
            this.counterName = counterName;
        }
    }

    /**
     * 将输入的字符串货柜任务转换为货柜任务类的对象
     * 【无需改动】
     *
     * @param counterTaskStr
     * @return
     */
    private static CounterTask convertToCounterTask(String counterTaskStr) {
        String cleanStep = counterTaskStr.substring(1, counterTaskStr.length() - 1);
        String[] vars = cleanStep.split(",");
        String[] counterNameStr = vars[0].split(":");
        String[] numStr = vars[1].split(":");
        CounterTask task = new CounterTask();
        String cn = counterNameStr[1];
        task.setCounterName(cn.substring(1, cn.length() - 1));
        task.setTaskNum(Integer.parseInt(numStr[1]));
        return task;
    }

    /**
     * ACM模式输入输出处理【无需改动】
     *
     * @param args
     */
    public static void main1(String[] args) {
        //ACM模式输入
        Scanner in = new Scanner(System.in);
        List<CounterTask> strArr = new ArrayList<>();
        int i = 0;
        String counterName = null;
        while (in.hasNext()) {
            if (i == 0) {
                counterName = in.nextLine();
            } else {
                strArr.add(convertToCounterTask(in.nextLine()));
            }
            i++;
        }
        //题目逻辑计算
        int res = counterTaskDone(strArr, counterName);
        //ACM模式输出
        System.out.println(res);
    }

    public static void main(String[] args) {
        Main main = new Main();
        String[] dates = {"1994 11","1994 25","2022 154"};
        String[] strings = main.dateArraysSort(dates);
        for (String string : strings) {
            System.out.println(string);
        }
    }

    /**
     * 货柜任务清零
     *
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param tasks       待做的货柜任务列表
     * @param counterName 要清零任务的货柜名称
     * @return 要清零该货柜上任务需要的天数
     */
    public static int counterTaskDone1(List<CounterTask> tasks, String counterName) {
        //todo 请实现此函数
        int count = 0;
        boolean flag = false;
        CounterTask task = null;
        for (int i = 0; i < tasks.size(); i++){
            if (tasks.get(i).counterName.equals(counterName)){
                task = tasks.get(i);
                flag = true;
            }
            if (flag == false){
                count = count + tasks.get(i).getTaskNum();
            } else {
                if (tasks.get(i).getTaskNum() < task.getTaskNum()){
                    count = count + tasks.get(i).getTaskNum();
                }else {
                    count = count + task.getTaskNum() - 1;
                }
            }
        }
        return count;
    }


    public static int counterTaskDone(List<CounterTask> tasks, String counterName) {
        //todo 请实现此函数
       int count = 0;
       int i = 0;
       while (!tasks.get(i).getCounterName().equals(counterName)){
           i++;
       }

        CounterTask task = tasks.get(i);
        for (int j = 0; j < tasks.size(); j++){
           if (j < i && tasks.get(j).getTaskNum() >= task.getTaskNum()){
               count = count + task.getTaskNum();
           } else  if (j < i && tasks.get(j).getTaskNum() < task.getTaskNum()){
               count = count + tasks.get(j).getTaskNum();
           } else if (j >= i && tasks.get(j).getTaskNum() >= task.getTaskNum()){
               count = count + task.getTaskNum() - 1;
           } else if (j >= i && tasks.get(j).getTaskNum() < task.getTaskNum()) {
               count = count + tasks.get(j).getTaskNum();
           }
       }
        return count;
    }

    public String[] dateArraysSort (String[] dates) {
        // write code here
        ArrayList<String> timeList = sort(dates);
        String[] day = {"1"};
        String[] moth = {"1,2,3,4,5,6,7,8,9,10,11,12"};
        int[] m = {30};
        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        Set<Integer> set2  = new HashSet<>();
        String[] t = new String[timeList.size()];
        int index = 0;

        for (int i = 0; i < timeList.size(); i++) {
            String[] s = timeList.get(i).split(" ");
            int year = Integer.valueOf(s[0]);
            int th = Integer.valueOf(s[1]);
            boolean flag = false;
            if ( (year % 4 == 0 && year  % 100 != 0) || (year % 400 == 0) ){
                flag = true;
            }
            int j = 0;
            for ( ; j <  m.length; j++){
                if (set1.contains(j) &&  th -31 > 0){
                    th = th - 31;
                }else if (set2.contains(j) && th - 30 > 0){
                    th = th - 30;
                }else if (j == 2){
                    if (flag && th - 29 > 0) th = th -29;
                    else if (th - 28 > 0) th = th -28;
                    else break;
                }else {
                    break;
                }
            }
            //String mt = moth[j];
            //String d = day[th];
            String d = th+ " ";
            String time = d + " " + j + " " + year;
            t[index++] = time;
        }
        return t;
    }

    public ArrayList<String> sort(String[] dates){
        for (int i = 0; i < dates.length - 1; i++) {
            for (int k = 0; k < dates.length - i - 1; k++){
                String s1 = dates[k];
                String s2 = dates[k + 1];;
                String[] n1 = s1.split(" ");
                String[] n2 = s2.split(" ");
                if (Integer.valueOf(n1[0]) - Integer.valueOf(n2[0]) < 0
                        || (Integer.valueOf(n1[0]) - Integer.valueOf(n2[0]) == 0 && Integer.valueOf(n1[1]) - Integer.valueOf(n2[1]) > 0)){
                    String temp = dates[k];
                    dates[k] = dates[k + 1];
                    dates[k + 1] = temp;
                }
            }
        }
        ArrayList<String> timeList = new ArrayList<>();
        timeList.add(dates[0]);
        for (int i = 1; i < dates.length; i++){
            timeList.add(dates[i]);
            if (i > 0 && timeList.get(i).equals(timeList.get(i - 1))){
                timeList.add(dates[i]);
            }
        }
        return timeList;
    }




}